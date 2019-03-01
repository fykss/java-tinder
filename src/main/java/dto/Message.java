package dto;

import java.util.Date;

public class Message {
    private int id;
    private int sender;
    private int recipient;
    private String textMessage;
    private Date date;

    public Message(int id, int sender, int recipient, String textMessage, Date date) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.textMessage = textMessage;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
