package ua.com.danit.dto;


public class Like {
    private int id;
    private int userIdWho;
    private int userIdWhom;

    public Like() {}

    public Like(int idWho, int idWhom) {
        this.userIdWho = idWho;
        this.userIdWhom = idWhom;
    }

    public Like(int id, int userIdWho, int userIdWhom) {
        this(userIdWho,userIdWhom);
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserIdWho() {
        return userIdWho;
    }

    public int getUserIdWhom() {
        return userIdWhom;
    }

    public void setUserIdWho(int userIdWho) {
        this.userIdWho = userIdWho;
    }

    public void setUserIdWhom(int userIdWhom) {
        this.userIdWhom = userIdWhom;
    }


}
