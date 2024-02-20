package org.example.service;

import org.LiteInject.Bean;

@Bean(name = "TestImpl1xx")
public class TestImpl1xx implements ITest {
    @Override
    public void test() {
        System.out.println("TestImpl1xx implementation!!");
    }
}
