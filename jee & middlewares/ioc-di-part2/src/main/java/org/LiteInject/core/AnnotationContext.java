package org.LiteInject.core;

import org.LiteInject.Bean;
import org.LiteInject.Inject;
import org.LiteInject.Strategy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AnnotationContext extends Context {
    private final String [] packages;

    public AnnotationContext(String... packages) {
        this.packages = packages;
        scan();
    }

    @Override
    public void scan() {
        // step 1: scan and register the declared beans types
        for (String pkg: packages) {
            try {
                var classes = getClasses(pkg);
                classes.stream()
                        .filter(c -> !c.isInterface())
                        .filter(clazz -> clazz.isAnnotationPresent(Bean.class))
                        .forEach(clazz -> {
                            Bean annotation = clazz.getDeclaredAnnotation(Bean.class);
                            String key = Objects.equals(annotation.name(), "") ? clazz.getName(): annotation.name();
                            super.registerType(key, clazz);
                        });
            } catch (URISyntaxException | IOException
                     | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        // step 2: instantiate the beans
        create();

    }

    public void create() {
        Map<String, Class<?>> types = getTypes();
        for (Map.Entry<String, Class<?>> entry: types.entrySet()) {
            create(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void create(final String key, Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length > 1)
            throw new RuntimeException("Expected only one constructor");
        Constructor<?> constructor = constructors[0];
        Parameter[] parameters = constructor.getParameters();
        Object instance;
        try {
            if (parameters.length > 0) {
                Class<?>[] classes = new Class[parameters.length];
                Object[] objects = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    classes[i] = parameters[i].getType();
                    if (parameters[i].isAnnotationPresent(Strategy.class)) {
                        Strategy strategy = parameters[i].getAnnotation(Strategy.class);
                        objects[i] = super.get(strategy.name());
                    } else {
                        objects[i] = super.get(parameters[i].getType());
                    }
                }
                instance = clazz.getDeclaredConstructor(classes).newInstance(objects);
            } else {
                instance = constructor.newInstance();
                scanSetters(instance);
            }
            super.registerBean(key, instance);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    private void scanSetters(Object obj) {
        boolean settersInvoked = false;
        for (Method method: obj.getClass().getMethods()) {
            if (method.getName().startsWith("set")
                    && method.isAnnotationPresent(Inject.class)) {
                Inject inject = method.getAnnotation(Inject.class);
                Parameter parameter = method.getParameters()[0];
                try {
                    if (inject.name().isEmpty()) {
                        method.invoke(super.get(parameter.getType()));
                    } else {
                        method.invoke(obj, super.get(inject.name()));
                    }
                    settersInvoked = true;
                } catch (IllegalAccessException
                         | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (!settersInvoked)
            scanFields(obj);
    }

    private void scanFields(final Object obj) {
        for (Field field: obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Inject inject = field.getAnnotation(Inject.class);
                field.setAccessible(true);
                try {
                    if (inject.name().isEmpty()) {
                        field.set(obj, super.get(field.getType()));
                    } else {
                        field.set(obj, super.get(inject.name()));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Set<Class<?>> getClasses(final String pkg) throws URISyntaxException, IOException, ClassNotFoundException {
        String pkgSlash = pkg.replace('.', '/');
        Path directories = Files.createDirectories(Path.of(ClassLoader.getSystemResource(pkgSlash).toURI()));
        File file = directories.toFile();
        Set<Class<?>> classes = new HashSet<>();
        File[] files =  null;
        if (file.isDirectory()) {
            files = file.listFiles();
            for (File f: files) {
                if (f.isDirectory()) {
                    classes.addAll(getClasses(pkg + "." + f.getName()));
                } else {
                    if (f.getName().endsWith(".class")) {
                        String classPath = pkg + "." + f.getName().replace(".class","");
                        classes.add(Class.forName(classPath));
                    }
                }
            }
        }
        return classes;
    }
}
