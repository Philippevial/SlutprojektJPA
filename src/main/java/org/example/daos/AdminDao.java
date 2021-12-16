package org.example.daos;

import java.util.List;

public interface AdminDao <T> {
    void add(T t);
    void update(T t);
    void delete(T t);
    List<T> showAll();
    void showInfo();
    T getById(String output);
}
