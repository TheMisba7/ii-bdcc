package org.example.dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Dao Impl 1 ");
        int temp = 12;
        return temp;
    }
}
