package ua.com.danit.dao.hashMapStorage;

import ua.com.danit.dto.User;
import java.util.Collection;

public interface DaoTempArrayListForUser<T> {
    void add(T value);
    void del(T value);
    T get(String string);
    Collection<User> getAllUsers();
}
