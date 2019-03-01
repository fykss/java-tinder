package dto;

import java.util.Date;

public class LikeExtra extends Like {
    private String surnameUser;
    private String nameUser;
    private String urlImgUser;
    private String positionUser;

    public LikeExtra(String surnameUser, String nameUser, String urlImgUser, String positionUser) {
        this.surnameUser = surnameUser;
        this.nameUser = nameUser;
        this.urlImgUser = urlImgUser;
        this.positionUser = positionUser;
    }

    public LikeExtra(int id, int userIdWho, int userIdWhom, Date date, String surnameUser, String nameUser, String urlImgUser, String positionUser) {
        super(id, userIdWho, userIdWhom, date);
        this.surnameUser = surnameUser;
        this.nameUser = nameUser;
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
