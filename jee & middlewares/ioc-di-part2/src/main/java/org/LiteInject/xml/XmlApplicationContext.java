package org.LiteInject.xml;

import com.example.myapp.Bean;
import com.example.myapp.Beans;
import org.LiteInject.core.Context;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XmlApplicationContext extends Context {
    private final String xmlFileName;
    //todo find an alternative solution for this map
    private final Map<String, Bean> beanMap = new ConcurrentHashMap<>();

    public XmlApplicationContext(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        scan();
    }

    @Override
    public void scan() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Beans.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStream asStream = ClassLoader.getSystemResourceAsStream(xmlFileName);
            Beans beans = (Beans) unmarshaller.unmarshal(asStream);
            for (Bean bean: beans.getBean()) {
                beanMap.put(bean.getId(), bean);
                registerType(bean.getId(), Class.forName(bean.getClazz()));
            }
        } catch (JAXBException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        create();
        beanMap.clear();
    }

    public void create() {
        Map<String, Class<?>> types = getTypes();
        for (Map.Entry<String, Class<?>> entry: types.entrySet()) {
            create(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void create(String key, Class<?> clazz) {
        Bean bean = beanMap.get(key);
        Object instance;
        try {
            if (bean.getDependency() == null || bean.getDependency().isEmpty()) {
                instance = clazz.getDeclaredConstructor().newInstance();
            } else {
                Object[] args = new Object[bean.getDependency().size()];
                Class<?>[] classes = new Class[bean.getDependency().size()];
                for (int i = 0; i < bean.getDependency().size(); i++) {
                    if (i == bean.getDependency().get(i).getIndex()) {
                        //load the class
                        Class<?> dependencyClass = Class.forName(bean.getDependency().get(i).getClazz());
                        // check if there is any implementation of the loaded class, or if it is registered
                        long count = getTypes().values()
                                .stream()
                                .filter(c -> c == clazz || clazz.isAssignableFrom(c)).count();
                        if (count < 1)
                            throw new RuntimeException("No bean registered for " + clazz);
                        classes[i] = dependencyClass;
                        // retrieving an instance of the dependency
                        args[i] = get(bean.getDependency().get(i).getRef());
                    }
                }
                instance = clazz.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        registerBean(bean.getId(), instance);
    }
}
