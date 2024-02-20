package org.example.service;

import org.LiteInject.annotation.Bean;
import org.LiteInject.annotation.Strategy;
import org.example.dao.IDao;
@Bean
public class ServiceImpl implements IService {
    private final IDao iDao;
    private final ITest iTest;

    public ServiceImpl(@Strategy(name = "mongo") IDao iDao,
                       @Strategy(name = "TestImpl11x") ITest iTest) {
        this.iDao = iDao;
        this.iTest = iTest;
    }

    @Override
    public double calculate(double first, double second) {
        iTest.test();
        double multiplier = iDao.getData();
        return (first - second) * multiplier;
    }
}