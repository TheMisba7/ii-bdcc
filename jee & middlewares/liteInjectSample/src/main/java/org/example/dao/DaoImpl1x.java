package org.example.dao;

import org.LiteInject.annotation.Bean;

@Bean(name = "mysqlServer")
public class DaoImpl1x implements IDao {
    @Override
    public double getData() {
        return 1;
    }
}
