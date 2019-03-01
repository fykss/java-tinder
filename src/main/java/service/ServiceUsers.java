package service;


import dao.daoUsers.DaoUsers;
import dao.daoUsers.DbDaoUsers;
import dto.User;

import java.sql.Connection;
import java.util.Collection;

public class ServiceUsers {

    private DaoUsers daoUsers;

    public ServiceUsers(Connection dbConn) {
        this.daoUsers = new DbDaoUsers(dbConn);
    }

    public User getUser(int idUser) {
        return daoUsers.get(idUser);
    }

    public int getIdUser(String email, String password) {
        User user = new User(email, password);
        return daoUsers.getId(user);
    }

    public boolean checkUser(String email, String password) {
        User user = new User(email, password);
        return daoUsers.check(user);
    }

    public Collection<User> getAllUsers() {
        return daoUsers.getAll();
    }

    public Collection<User> getAllLikesUser(int userId){
        return daoUsers.getAllLikes(userId);
    }

    public void updateUserDate(int userId){
        daoUsers.updateDate(userId);
    }
}
