package dto;


import java.util.Date;

public class Like {
    private int id;
    private int userIdWho;
    private int userIdWhom;
    private Date date;

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

    @Override
    public String toString() {
        return "Like{" +
                ", userIdWho=" + userIdWho +
                ", userIdWhom=" + userIdWhom +
                ", date=" + date +
                '}';
    }
}
