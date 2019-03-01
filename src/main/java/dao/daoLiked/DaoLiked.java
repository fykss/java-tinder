package dao.daoLiked;

import java.util.Collection;

public interface DaoLiked<T> {
    void save(T value);
    void update(T value);
    boolean check(T value);
    Collection<T> getAll(int id);
}
