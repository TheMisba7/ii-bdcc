package org.LiteInject.core;

import java.util.Map;

public interface IBeanFactory {
    /**
     * This method is invoked after an instance of {@link Context}
     * is created to whether scan the passed packages or xml document config
     * and invoke {@link IBeanFactory#registerType(String, Class)} to register the declared bean types
     * @param packages
     */
    void scan();
    void registerType(String key, Class<?> clazz);
    Map<String, Class<?>> getTypes();
    void create(String key, Class<?> clazz);
    void registerBean(String key, Object object);
    Object get(String key);
    <T> T get(Class<T> clazz);
    void remove(String key);
    void remove(Class<?> clazz);

}
