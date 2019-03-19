package ua.com.danit.service;

import ua.com.danit.dao.hashMapStorage.DaoTemporaryStorageForUser;
import ua.com.danit.dao.hashMapStorage.TemporaryStorageForUser;
import ua.com.danit.dto.User;

import java.sql.Timestamp;

public class ServiceTemporaryStorageForUser {

    private DaoTemporaryStorageForUser<User> dtsForUser = new TemporaryStorageForUser();

    public void addUser(String name, String surname, String password, String position, String email, String urlImg, String gender){
        User user = new User(name, surname, password, position, email, urlImg, gender, new Timestamp(System.currentTimeMillis()));
        dtsForUser.add(user);
    }

}
