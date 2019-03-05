package ua.com.danit.service;

import ua.com.danit.dao.daoUsers.DaoUsers;
import ua.com.danit.dao.daoUsers.DbDaoUsers;
import ua.com.danit.dto.User;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Collection;

public class ServiceUsers {

    private DaoUsers<User> daoUsers;

    public ServiceUsers(Connection dbConn) {
        this.daoUsers = new DbDaoUsers(dbConn);
    }

    public User getUser(int idUser) {
        return daoUsers.get(idUser);
    }

    public int getIdUser(String email, String password) {
        return daoUsers.getId(new User(email, password));
    }

    public Collection<User>getAllLikedUsers(int idUserWhoLiked){
        return daoUsers.getAllLiked(idUserWhoLiked);
    }

    public void updateUserDate(int userId){
        daoUsers.updateDate(userId);
    }

    public void addUser(String name, String surname, String password, String position, String email, String urlImg, String gender){
        User user = new User(name, surname, password, position, email, urlImg, gender, new Timestamp(System.currentTimeMillis()));
        daoUsers.add(user);
    }

    public boolean checkEmail(String email){
        return daoUsers.checkEmail(email);
    }

    public int maxIdUser(){
       return daoUsers.maxId();
    }

}
