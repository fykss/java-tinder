package ua.com.danit.dao.daoMessage;

import ua.com.danit.dto.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DbDaoMessages implements DaoMessage<Message> {

    private Connection dbConn;
    public DbDaoMessages(Connection dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public void add(Message message) {
        try(PreparedStatement ps = dbConn.prepareStatement(
                "insert into OD_88_tinderMessage(sender, recipient, message, date) values (?,?,?,?)")) {
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
    public Collection<Message> getAll(int idSender, int idRecipient) {
        List<Message> messages = new ArrayList<>();
        try(PreparedStatement ps = dbConn.prepareStatement(
                "(SELECT * from OD_88_tinderMessage where sender=? and recipient=?)\n" +
                "UNION\n" +
                "(SELECT * from OD_88_tinderMessage where sender=? and recipient=?)\n" +
                "order by date;")){
            ps.setInt(1, idSender);
            ps.setInt(2, idRecipient);
            ps.setInt(3, idRecipient);
            ps.setInt(4, idSender);
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
}

