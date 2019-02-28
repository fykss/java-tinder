package dao.daoUsers;

import java.util.Collection;

public interface DaoUsers<T> {
    T get(int id);
    int getId(T value);
    boolean check(T value);
    Collection<T> getAll();
}
