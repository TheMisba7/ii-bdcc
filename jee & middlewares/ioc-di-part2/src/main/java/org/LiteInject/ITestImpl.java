package org.LiteInject;

@Bean
public class ITestImpl implements ITest {
    @Override
    public void test() {
        System.out.println("second impl");
    }
}
