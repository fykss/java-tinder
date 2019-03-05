package ua.com.danit.service;

import ua.com.danit.dao.daoMessage.DbDaoMessages;
import ua.com.danit.dto.Message;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

public class ServiceMessages {

    private DbDaoMessages dbDaoMessages;

    public ServiceMessages(Connection dbConn) {
        this.dbDaoMessages = new DbDaoMessages(dbConn);
    }

    public void addMessage(int idSender, int idRecipient, String text){
        Message message = new Message(idSender, idRecipient, text, new Timestamp(System.currentTimeMillis()));
        dbDaoMessages.add(message);
    }

    public Message getMessage(int idMessage){
        return dbDaoMessages.get(idMessage);
    }

    public int getIdMessage(int senderId, int recipientId, Date date){
        return dbDaoMessages.getId(new Message(senderId, recipientId, date));
    }

    public Collection<Message> getAllMessages(int idSender, int idRecipient){
        return dbDaoMessages.getAll(idSender,idRecipient);
    }

//    public Collection<MessageExtra> getAllMessagesExtra(int idSender, int idRecipient){
//        return dbDaoMessages.getAllMsgExtra(idSender,idRecipient);
//    }
}
