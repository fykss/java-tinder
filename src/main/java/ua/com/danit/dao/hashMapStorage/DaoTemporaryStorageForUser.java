package ua.com.danit.dao.hashMapStorage;

public interface DaoTemporaryStorageForUser<T> {
    void add(T value);
    void del(T value);
    T get(int id);
}
