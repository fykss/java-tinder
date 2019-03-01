package service;

import dao.daoMessage.DbDaoMessages;
import dto.Message;
import dto.MessageExtra;

import java.sql.Connection;
import java.util.Collection;
import java.util.Date;

public class ServiceMessages {

    private DbDaoMessages dbDaoMessages;

    public ServiceMessages(Connection dbConn) {
        this.dbDaoMessages = new DbDaoMessages(dbConn);
    }

    public void addMessage(Message message){
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

    public Collection<MessageExtra> getAllMessagesExtra(int idSender, int idRecipient){
        return dbDaoMessages.getAllMsgExtra(idSender,idRecipient);
    }
}
