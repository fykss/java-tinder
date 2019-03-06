package ua.com.danit.service;

import ua.com.danit.dao.daoMessage.DaoMessage;
import ua.com.danit.dao.daoMessage.DbDaoMessages;
import ua.com.danit.dto.Message;
import java.sql.Connection;
import java.util.Collection;

public class ServiceMessages {

    private DaoMessage<Message> daoMessage;

    public ServiceMessages(Connection dbConn) {
        this.daoMessage = new DbDaoMessages(dbConn);
    }

    public void addMessage(int idSender, int idRecipient, String text){
        Message message = new Message(idSender, idRecipient, text);
        daoMessage.add(message);
    }

    public Collection<Message> getAllMessages(int idSender, int idRecipient){
        return daoMessage.getAll(idSender,idRecipient);
    }
}
