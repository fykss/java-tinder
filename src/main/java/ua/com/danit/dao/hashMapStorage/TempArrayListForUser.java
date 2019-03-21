package ua.com.danit.dao.hashMapStorage;

import ua.com.danit.dto.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TempArrayListForUser implements DaoTempArrayListForUser<User> {

    private List<User> tempArrayListUsers = new ArrayList<>();

    public TempArrayListForUser() { }

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
        User user = null;
        Optional<User> optionalUser = tempArrayListUsers.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return tempArrayListUsers;
    }
}
