package org.LiteInject.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Context {
    private final Map<String, Object> beans = new ConcurrentHashMap<>();

    public Object getBean(String key) {
        return beans.get(key);
    }

    protected void addBean(String name, Object obj) {
        if (beans.containsKey(name)) {
            throw new RuntimeException("bean with name " + name + " already exist.");
        }
        beans.put(name, obj);
    }

    protected abstract void start();
}
