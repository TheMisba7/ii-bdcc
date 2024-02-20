package org.example;

import org.LiteInject.annotation.AnnotationContext;
import org.example.service.IService;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AnnotationContext context = new AnnotationContext("org.example.service", "org.example.dao");
        IService service = context.get(IService.class);

        System.out.println(service.calculate(3, 1));
    }
}