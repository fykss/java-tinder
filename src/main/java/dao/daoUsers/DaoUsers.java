package dao.daoUsers;

import java.util.Collection;

public interface DaoUsers<T> {
    T get(int id);
    int getId(T value);
    boolean check(T value);
    Collection<T> getAll();
    void add(T item);
    void updateDate(int id);
    Collection<T> getAllLikes(int id);
}