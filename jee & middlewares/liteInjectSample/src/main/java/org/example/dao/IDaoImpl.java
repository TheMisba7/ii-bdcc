package org.example.dao;

import org.LiteInject.annotation.Bean;

@Bean(name = "mongo")
public class IDaoImpl implements IDao {
    @Override
    public double getData() {
        return 2;
    }
}
