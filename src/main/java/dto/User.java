package dto;

import java.util.Date;

public class User {

    private int id;
    private String name;
    private String surname;
    private String password;
    private String position;
    private String email;
    private String urlImg;
    private String gender;
    private Date date;
    private String timeDif;

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String surname, String urlImg) {
        this.name = name;
        this.surname = surname;
        this.urlImg = urlImg;
    }

    public User(int id, String name, String surname, String position, String urlImg) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.urlImg = urlImg;
    }

    public User(int id, String name, String surname, String position, String urlImg, String gender) {
        this(id,name,surname,position,urlImg);
        this.gender = gender;
    }

    public User(int id, String name, String surname, String position, String urlImg, Date date) {
        this(id,name,surname,position,urlImg);
        this.date = date;
    }

    public User(int id, String name, String surname, String password, String position, String email, String urlImg, String gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.position = position;
        this.email = email;
        this.urlImg = urlImg;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeDif() {
        return timeDif;
    }

    public void setTimeDif(String timeDif) {
        this.timeDif = timeDif;
    }
}
