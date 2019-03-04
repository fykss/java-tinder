package service;

import dao.daoUsers.DbDaoUsers;
import dto.User;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

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

    public Collection<User>getAllLikedUsers(int idUserWhoLiked){
        return dbDaoUsers.getAllLikes(idUserWhoLiked);
    }

    public void updateUserDate(int userId){
        dbDaoUsers.updateDate(userId);
    }

    public void addUser(String name, String surname, String password, String position, String email, String urlImg, String gender){
        User user = new User(name, surname, password, position, email, urlImg, gender, new Timestamp(System.currentTimeMillis()));
        dbDaoUsers.add(user);
    }

    public boolean checkEmail(String email){
        return dbDaoUsers.checkEmail(email);
    }

}
