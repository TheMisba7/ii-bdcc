package org.LiteInject.xml;

import generated.Bean;
import generated.Beans;
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
    private final Map<String, Bean> beanMap = new ConcurrentHashMap<>();

    public XmlApplicationContext(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        scan();
    }

    @Override
    public void scan() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlApplicationContext.class);
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
            if (bean.getDependency() == null && bean.getDependency().isEmpty()) {
                instance = clazz.getDeclaredConstructor().newInstance();
            } else {
                Object[] args = new Object[bean.getDependency().size()];
                Class<?>[] classes = new Class[bean.getDependency().size()];
                for (int i = 0; i < bean.getDependency().size(); i++) {
                    if (i == bean.getDependency().get(i).getIndex()) {
                        classes[i] = getTypes().get(bean.getDependency().get(i).getRef());
                        args[i] = get(bean.getDependency().get(i).getRef());
                    }
                }
                instance = clazz.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        registerBean(bean.getId(), instance);

    }
}
