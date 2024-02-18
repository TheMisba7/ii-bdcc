package org.example.service;

import org.example.dao.IDao;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IMetier {
    private final IDao iDao;

    public ServiceImpl(IDao iDao) {
        this.iDao = iDao;
    }

    @Override
    public double calculate(double first, double second) {
        double multiplier =  iDao.getData();
        return (first / second) * multiplier;
    }
}
