package org.LiteInject.core;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Context {
    private final Map<String, Object> beans = new ConcurrentHashMap<>();

    public Object getBean(String key) {
        return beans.get(key);
    }

    public <T> T getBean(Class<T> type) {
        Object[] objects = beans.values().stream()
                .filter(o -> o.getClass() == type
                        || type.isAssignableFrom(o.getClass())).toArray();
        if (objects.length > 1)
            throw new RuntimeException("found more than one bean of type " + type);
        if (objects.length == 0)
            throw new RuntimeException("No bean found for type " + type.getName());

        return (T) objects[0];
    }

    protected void addBean(String name, Object obj) {
        if (beans.containsKey(name)) {
            throw new RuntimeException("bean with name " + name + " already exist.");
        }
        beans.put(name, obj);
    }

    protected abstract void start();
}
