package dto;


import java.util.Date;

public class Like {
    private int id;
    private int userIdWho;
    private int userIdWhom;
    private Date date;

    public Like() {}

    public Like(int userIdWho, int userIdWhom, Date date) {
        this.userIdWho = userIdWho;
        this.userIdWhom = userIdWhom;
        this.date = date;
    }

    public Like(int id, int userIdWho, int userIdWhom, Date date) {
        this.id = id;
        this.userIdWho = userIdWho;
        this.userIdWhom = userIdWhom;
        this.date = date;
    }

    public Like(int idWho, int idWhom) {
        this.userIdWho = idWho;
        this.userIdWhom = idWhom;
    }

    public int getId() {
        return id;
    }

    public int getUserIdWho() {
        return userIdWho;
    }

    public int getUserIdWhom() {
        return userIdWhom;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserIdWho(int userIdWho) {
        this.userIdWho = userIdWho;
    }

    public void setUserIdWhom(int userIdWhom) {
        this.userIdWhom = userIdWhom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
