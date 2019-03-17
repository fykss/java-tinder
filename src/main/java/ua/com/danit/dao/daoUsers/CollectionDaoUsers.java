package ua.com.danit.dao.daoUsers;

import ua.com.danit.dto.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionDaoUsers implements DaoUsers<User> {
    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User get(int userIndex) {
        return users.get(userIndex);
    }

    @Override
    public int getId(User user) {
        return 0;
    }

    @Override
    public boolean checkEmail(String value) {
        return false;
    }

    @Override
    public Collection<User> getAllLiked(int valueId) {
        return null;
    }

    @Override
    public void updateDate(int valueId) {

    }

    @Override
    public int maxId() {
        return 0;
    }
}
