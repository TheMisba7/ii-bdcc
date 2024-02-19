package org.example.dao;

import org.LiteInject.Bean;

@Bean
public class IDaoImpl implements IDao {
    @Override
    public double getData() {
        return 2;
    }
}
