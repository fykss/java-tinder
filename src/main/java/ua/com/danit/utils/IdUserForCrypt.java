package ua.com.danit.utils;

import ua.com.danit.dto.User;

import java.util.Date;

public class IdUserForCrypt extends User {
    private String idUser;

    public IdUserForCrypt(String idUser, String name, String surname, String position, String urlImg, Date date) {
        super(name, surname, position, urlImg, date);
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
