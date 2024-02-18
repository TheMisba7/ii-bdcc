package org.LiteInject.core;

import org.LiteInject.Bean;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class AnnotationContext extends Context {
    private final String [] packages;

    public AnnotationContext(String... packages) {
        this.packages = packages;
        start();
    }

    @Override
    protected void start() {
        for (String pkg: packages) {
            try {
                var classes = getClasses(pkg);
                classes.stream()
                        .filter(c -> !c.isInterface())
                        .map(c -> {
                            Bean annotation = c.getDeclaredAnnotation(Bean.class);
                            return Pair.<Class<?>, Bean>of(c, annotation);
                        })
                        .filter(p -> p.getRight() != null)
                        .forEach(this::initInstance);
            } catch (URISyntaxException | IOException
                     | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void initInstance(Pair<Class<?>, Bean> classBeanPair) {
        var name = classBeanPair.getRight().name();
        try {
            Class<?> clazz = classBeanPair.getLeft();
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            Parameter[] parameters = declaredConstructor.getParameters();
            if (parameters.length > 0) {
                for (Parameter arg: parameters) {
                    System.out.println(arg.getClass().getName());
                }
            }
            Object instance = clazz.getDeclaredConstructor().newInstance();
            if (name.isBlank())
                name = instance.getClass().getName();
            addBean(name, instance);
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
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
                System.out.println(f.getName());
                if (f.isDirectory()) {
                    classes.addAll(getClasses(pkg + "." +f.getName()));
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
