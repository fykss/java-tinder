package dao.daoMessage;

public interface DaoMessage<T> {
    void add(T value);
    T get(int id);
    int getId(T value);
}
