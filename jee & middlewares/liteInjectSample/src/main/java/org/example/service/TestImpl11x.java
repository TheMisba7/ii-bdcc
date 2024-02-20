package org.example.service;

import org.LiteInject.annotation.Bean;

@Bean(name = "TestImpl11x")
public class TestImpl11x implements ITest {
    @Override
    public void test() {
        System.out.println("TestImpl11x implementation!!");
    }
}
