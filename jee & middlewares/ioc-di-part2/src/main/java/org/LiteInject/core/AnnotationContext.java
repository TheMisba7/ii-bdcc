package org.LiteInject.core;

import org.LiteInject.Bean;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
                        .filter(c -> {
                            Bean annotation = c.getDeclaredAnnotation(Bean.class);
                            return annotation != null;
                        })
                        .forEach(this::initInstance);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void initInstance(final Class<?> clazz) {
        var name = clazz.getName();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            addBean(name, instance);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
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
