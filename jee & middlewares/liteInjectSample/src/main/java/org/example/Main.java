package org.example;

import org.LiteInject.core.Context;
import org.LiteInject.xml.XmlApplicationContext;
import org.example.service.IService;

public class Main {
    public static void main(String[] args) {
        Context context = new XmlApplicationContext("config.xml");
        IService iService = context.get(IService.class);
        System.out.println(iService.calculate(4, 3));;
    }
}