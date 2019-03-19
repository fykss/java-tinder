package ua.com.danit.service;

import ua.com.danit.dao.hashMapStorage.DaoTempArrayListForUser;
import ua.com.danit.dao.hashMapStorage.TempArrayListForUser;
import ua.com.danit.dto.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

public class ServiceTempArrayListForUser {

    private DaoTempArrayListForUser<User> daoTempArrayListForUser;

    public ServiceTempArrayListForUser(ArrayList<User> tsUsers) {
        this.daoTempArrayListForUser = new TempArrayListForUser(tsUsers);
    }

    public void addUser(String name, String surname, String password, String position, String email, String urlImg, String gender){
        User user = new User(name, surname, password, position, email, urlImg, gender, new Timestamp(System.currentTimeMillis()));
        daoTempArrayListForUser.add(user);
    }

    public void delUser(String email){
        daoTempArrayListForUser.del(daoTempArrayListForUser.get(email));
    }

    public User getUser(String email){
        return daoTempArrayListForUser.get(email);
    }

    public Collection<User> getAllUsers(){
        return daoTempArrayListForUser.getAllUsers();
    }

    public boolean checkEmailTemporaryStorage(String email){
        return daoTempArrayListForUser.getAllUsers().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public boolean timeActive(String email){
        boolean flag = false;
        long timeUser = daoTempArrayListForUser.get(email).getDate().getTime();
        long timeNow = System.currentTimeMillis();
        long result = timeNow - timeUser;
        long activeTime = 5 * 60 * 1000;
        if(result > activeTime){
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }
}
