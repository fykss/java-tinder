package dao.daoMessage;

import dto.Message;

import java.sql.*;

public class DbDaoMessages implements DaoMessage<Message> {

    private Connection dbConn;
    public DbDaoMessages(Connection dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public void add(Message message) {
        try(PreparedStatement ps = dbConn.prepareStatement("insert into OD_88_tinderMessage(sender, recipient, message) values (?,?,?)")) {
            ps.setInt(1, message.getSender());
            ps.setInt(2, message.getRecipient());
            ps.setString(3, message.getTextMessage());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Message get(int idMsg) {
        Message message = null;
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM OD_88_tinderMessage WHERE id=?")){
            ps.setInt(1, idMsg);
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
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT id FROM OD_88_tinderMessage WHERE sender=? and recipient=?")) {
            ps.setInt(1, message.getSender());
            ps.setInt(2, message.getRecipient());
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                result = rSet.getInt("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}

