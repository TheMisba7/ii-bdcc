package org.example;

import org.LiteInject.core.AnnotationContext;
import org.example.service.IService;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AnnotationContext context = new AnnotationContext("org.example.dao", "org.example.service");
        IService service = context.getBean(IService.class);

        System.out.println(service.calculate(3, 1));
    }
}