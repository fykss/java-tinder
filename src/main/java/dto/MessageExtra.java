package dto;

import java.util.Date;

public class MessageExtra extends Message {
    private String nameUser;
    private String surnameUser;
    private String urlImgUser;

    public MessageExtra(int sender, int recipient, Date date) {
        super(sender, recipient, date);
    }

    public MessageExtra(int id, int sender, int recipient, String textMessage, Date date, String nameUser, String surnameUser, String urlImgUser) {
        super(id, sender, recipient, textMessage, date);
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.urlImgUser = urlImgUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public void setSurnameUser(String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public String getUrlImgUser() {
        return urlImgUser;
    }

    public void setUrlImgUser(String urlImgUser) {
        this.urlImgUser = urlImgUser;
    }
}
