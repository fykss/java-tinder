package ua.com.danit.dao.daoUsers;

import ua.com.danit.dto.User;

import java.util.Collection;

public interface DaoUsers<T> {
    T get(int id);
    int getId(T value);
    boolean check(T value);
    Collection<T> getAll();
    void add(T item);
    Collection<User> getAllLikes(int id);
    void updateDate(int id);
}
