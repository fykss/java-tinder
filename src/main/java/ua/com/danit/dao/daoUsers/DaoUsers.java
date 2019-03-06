package ua.com.danit.dao.daoUsers;

import ua.com.danit.dto.User;
import java.util.Collection;

public interface DaoUsers<T> {
    void add(T item);
    T get(int userId);
    int getId(T value);
    boolean checkEmail(String value);
    Collection<User> getAllLiked(int valueId);
    void updateDate(int valueId);
    int maxId();
}
