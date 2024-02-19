package org.example.service;

import org.LiteInject.Bean;
import org.example.dao.IDao;
@Bean
public class ServiceImpl implements IService {
    private final IDao iDao;

    public ServiceImpl(IDao iDao) {
        this.iDao = iDao;
    }

    @Override
    public double calculate(double first, double second) {
        double multiplier = iDao.getData();
        return (first - second) * multiplier;
    }
}
