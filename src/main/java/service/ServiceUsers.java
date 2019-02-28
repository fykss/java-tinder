package service;

import dao.daoUsers.DbDaoUsers;
import dto.User;

import java.sql.Connection;
import java.util.Collection;

public class ServiceUsers {

    private DbDaoUsers dbDaoUsers;

    public ServiceUsers(Connection dbConn) {
        this.dbDaoUsers = new DbDaoUsers(dbConn);
    }

    public User getUser(int idUser) {
        return dbDaoUsers.get(idUser);
    }

    public int getIdUser(String email, String password) {
        User user = new User(email, password);
        return dbDaoUsers.getId(user);
    }

    public boolean checkUser(String email, String password) {
        User user = new User(email, password);
        return dbDaoUsers.check(user);
    }

    public Collection<User> getAllUsers() {
        return dbDaoUsers.getAll();
    }

    public Collection<User> getAllLikesUser(int userId){
        return dbDaoUsers.getAllLikes(userId);
    }
}
