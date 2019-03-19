package ua.com.danit.dao.hashMapStorage;

import ua.com.danit.dto.User;

import java.util.ArrayList;

public class TemporaryStorageForUser implements DaoTemporaryStorageForUser<User> {

    ArrayList<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void del(User user) {
        users.remove(user);
    }

    @Override
    public User get(int id) {
        return null;
    }
}
