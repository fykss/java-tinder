package ua.com.danit.dao.hashMapStorage;

import ua.com.danit.dto.User;
import java.util.ArrayList;
import java.util.Collection;

public class TempArrayListForUser implements DaoTempArrayListForUser<User> {

    private ArrayList<User> tempArrayListUsers;

    public TempArrayListForUser(ArrayList<User> tempArrayListUsers) {
        this.tempArrayListUsers = tempArrayListUsers;
    }

    @Override
    public void add(User user) {
        tempArrayListUsers.add(user);
    }

    @Override
    public void del(User user) {
        tempArrayListUsers.remove(user);
    }

    @Override
    public User get(String email) {
        return tempArrayListUsers.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public Collection<User> getAllUsers() {
        return tempArrayListUsers;
    }
}
