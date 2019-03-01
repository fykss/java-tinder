package dto;

import java.util.Date;

public class LikeExtra extends Like {
    private String nameUser;
    private String surnameUser;
    private String urlImgUser;
    private String positionUser;


    public LikeExtra(int id, int userIdWho, int userIdWhom, Date date, String nameUser, String surnameUser, String urlImgUser, String positionUser) {
        super(id, userIdWho, userIdWhom, date);
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.urlImgUser = urlImgUser;
        this.positionUser = positionUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public void setSurnameUser(String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getUrlImgUser() {
        return urlImgUser;
    }

    public void setUrlImgUser(String urlImgUser) {
        this.urlImgUser = urlImgUser;
    }

    public String getPositionUser() {
        return positionUser;
    }

    public void setPositionUser(String positionUser) {
        this.positionUser = positionUser;
    }
}
