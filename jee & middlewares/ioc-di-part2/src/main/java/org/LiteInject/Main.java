package org.LiteInject;

import org.LiteInject.core.AnnotationContext;
import org.LiteInject.core.Context;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Context context = new AnnotationContext("org.LiteInject");

        Test bean = (Test) context.getBean(Test.class.getName());
        bean.test();


    }
}

