package dao.daoMessage;

import java.util.Collection;

public interface DaoMessage<T> {
    void add(T value);
    T get(int id);
    int getId(T value);
    Collection<T> getAll(int idS, int idR);
}
