package org.LiteInject;

@Bean(name = "testBean")
public class Test implements ITest{
    private final String vqlue;

    public Test(String vqlue) {
        this.vqlue = vqlue;
    }

    public void test() {
        System.out.println("Hello There!");
    }

}
