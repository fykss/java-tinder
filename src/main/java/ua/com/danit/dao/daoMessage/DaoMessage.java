package ua.com.danit.dao.daoMessage;

import java.util.Collection;

public interface DaoMessage<T> {
    void add(T value);
    Collection<T> getAll(int idValue, int valueId);
}
