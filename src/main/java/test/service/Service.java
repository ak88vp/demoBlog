package test.service;

import java.sql.SQLException;
import java.util.List;

public interface Service <T>{
    List<T> printAll() throws SQLException;
    List<T> printAllIdCategory() throws SQLException;
    List<T> findByName(String name) throws SQLException;
    T findById(int id) throws SQLException;
    void add(T t) throws SQLException;
    void edit(int id,T t) throws SQLException;
    void delete(int id) throws SQLException;
}
