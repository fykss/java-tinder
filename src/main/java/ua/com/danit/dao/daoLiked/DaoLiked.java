package ua.com.danit.dao.daoLiked;

public interface DaoLiked<T> {
    void save(T value);
    void del(T value);
    boolean check(T value);
}
