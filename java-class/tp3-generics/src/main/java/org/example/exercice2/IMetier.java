package org.example.exercice2;

import java.util.List;

public interface IMetier<T> {
    void add(T o);
    List<T> getAll();
    T findById(int id);
    void delete(int id);
}
