package dao.daoMessage;

import dto.Message;
import dto.MessageExtra;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DbDaoMessages implements DaoMessage<Message> {

    private Connection dbConn;
    public DbDaoMessages(Connection dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public void add(Message message) {
        try(PreparedStatement ps = dbConn.prepareStatement("insert into OD_88_tinderMessage(sender, recipient, message, date) values (?,?,?,?)")) {
            ps.setInt(1, message.getSender());
            ps.setInt(2, message.getRecipient());
            ps.setString(3, message.getTextMessage());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Message get(int idMessage) {
        Message message = null;
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM OD_88_tinderMessage WHERE id=?")){
            ps.setInt(1, idMessage);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                int idSql = rSet.getInt("id");
                int senderSql = rSet.getInt("sender");
                int recipientSql = rSet.getInt("recipient");
                String messageSql = rSet.getString("message");
                Date dateSql = rSet.getDate("date");
                message = new Message(idSql, senderSql,recipientSql,messageSql,dateSql);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public int getId(Message message){
        int result = 0;
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT id FROM OD_88_tinderMessage WHERE sender=? and recipient=? and date=?")) {
            ps.setInt(1, message.getSender());
            ps.setInt(2, message.getRecipient());
            ps.setTimestamp(3, new Timestamp(message.getDate().getTime()));
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                result = rSet.getInt("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Message> getAll(int idSender, int idRecipient) {
        ArrayList<Message> messages = new ArrayList<>();
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT * from OD_88_tinderMessage where sender=? and recipient=?")){
            ps.setInt(1, idSender);
            ps.setInt(2, idRecipient);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                Message message = new Message(
                        rSet.getInt("id"),
                        rSet.getInt("sender"),
                        rSet.getInt("recipient"),
                        rSet.getString("message"),
                        rSet.getTimestamp("date")
                );
                messages.add(message);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return messages;
    }

    public Collection<MessageExtra> getAllMsgExtra(int idSender, int idRecipient){
        ArrayList<MessageExtra> messages = new ArrayList<>();
        try(PreparedStatement ps = dbConn.prepareStatement(
                "SELECT OD_88_tinderMessage.id," +
                        "OD_88_tinderMessage.sender," +
                        "OD_88_tinderMessage.recipient, " +
                        "OD_88_tinderMessage.message," +
                        "OD_88_tinderMessage.date," +
                        "OD_88_tinderUsers.name," +
                        "OD_88_tinderUsers.surname, " +
                        "OD_88_tinderUsers.img " +
                        "FROM OD_88_tinderMessage " +
                        "INNER JOIN OD_88_tinderUsers " +
                        "ON OD_88_tinderMessage.sender = OD_88_tinderUsers.id " +
                        "WHERE sender = ? and recipient = ?;")){
            ps.setInt(1, idSender);
            ps.setInt(2, idRecipient);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                MessageExtra messageExtra = new MessageExtra(
                        rSet.getInt("id"),
                        rSet.getInt("sender"),
                        rSet.getInt("recipient"),
                        rSet.getString("message"),
                        rSet.getDate("date"),
                        rSet.getString("name"),
                        rSet.getString("surname"),
                        rSet.getString("img")
                );
                messages.add(messageExtra);
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return messages;
    }
}

