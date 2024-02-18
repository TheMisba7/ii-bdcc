package org.example.dao;

public class DaoImpl1 implements IDao {
    @Override
    public double getData() {
        System.out.println("Second Dao implementation");
        int temp = 5;
        return temp;
    }
}
