package ua.com.danit.dao.daoUsers;

import ua.com.danit.dto.User;

import java.util.Collection;

public interface DaoUsers<T> {
    void add(T item);
    T get(int userId);
    int getId(T value);
    boolean checkEmail(String email);
    Collection<User> getAllLiked(int userId);
    void updateDate(int id);
    int maxId();
}
