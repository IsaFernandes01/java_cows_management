package interfaces;

import entities.Pasto;
import entities.Vaca;

import java.util.ArrayList;

public interface CrudRepository<T, ID> {
    void insert(T entity);
    T findById(ID id);
    ArrayList<T> findAll();
    void update(T entity);
    void remove(ID id);
}
