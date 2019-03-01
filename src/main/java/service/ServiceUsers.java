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
        return dbDaoUsers.getId(new User(email, password));
    }

    public boolean checkUser(String email, String password) {
        return dbDaoUsers.check(new User(email, password));
    }

    public Collection<User> getAllUsers() {
        return dbDaoUsers.getAll();
    }

    public void updateUserDate(int userId){
        dbDaoUsers.updateDate(userId);
    }
}
