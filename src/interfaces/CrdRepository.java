package interfaces;

import java.util.ArrayList;

public interface CrdRepository<T, ID> {
    void insert(T entity);
    T findById(ID id);
    ArrayList<T> findAll();
    void remove(ID id);
}
