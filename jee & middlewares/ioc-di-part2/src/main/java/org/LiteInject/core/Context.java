package org.LiteInject.core;

import javax.xml.transform.sax.SAXResult;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Context implements IBeanFactory {
    private final Map<String, Object> beans = new ConcurrentHashMap<>();
    private final Map<String, Class<?>> registeredTypes = new ConcurrentHashMap<>();

    @Override
    public void registerType(String key, Class<?> clazz) {
        if (registeredTypes.containsKey(key)) {
            throw new RuntimeException("A type already registered with name " + key);
        }
        registeredTypes.put(key, clazz);
    }

    @Override
    public void registerBean(String key, Object object) {
        if (beans.containsKey(key)) {
            throw new RuntimeException("bean with name " + key + " already exist.");
        }
        beans.put(key, object);
    }

    @Override
    public Object get(String key) {
        if (!registeredTypes.containsKey(key))
            throw new RuntimeException("No registered bean with name " + key);
        Object object = beans.get(key);
        if (object == null) {
            // bean type is registered but not yet created
            create(key, registeredTypes.get(key));
            object = beans.get(key);
        }
        return object;
    }

    @Override
    public <T> T get(Class<T> clazz) {
        long count = registeredTypes.values().stream()
                .filter(c -> c == clazz || clazz.isAssignableFrom(c))
                .count();
        if (count > 1) {
            throw new RuntimeException("found more than one bean of type " + clazz);
        }
        Object[] objects = beans.values().stream()
                .filter(o -> o.getClass() == clazz
                        || clazz.isAssignableFrom(o.getClass())).toArray();
        if (objects.length > 1)
            throw new RuntimeException("found more than one bean of type " + clazz);
        if (objects.length == 0)
            throw new RuntimeException("No bean found for type " + clazz.getName());
        return clazz.cast(objects[0]);
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void remove(Class<?> clazz) {

    }
    @Override
    public Map<String, Class<?>> getTypes() {
        return registeredTypes;
    }
}
