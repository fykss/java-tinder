package dao.daoLiked;

import dto.Like;
import java.sql.*;
import java.util.Collection;

public class DbDaoLikes implements DaoLiked<Like> {

    private Connection dbConn;
    public DbDaoLikes(Connection dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public void save(Like like) {
        try(PreparedStatement ps = dbConn.prepareStatement("insert into OD_88_tinderLiked(userId_who, userId_whom, date) values (?, ?, ?)")){
            ps.setInt(1, like.getUserIdWho());
            ps.setInt(2, like.getUserIdWhom());
            ps.setTimestamp(3, new Timestamp(like.getDate().getTime()));
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Like like) {
        try(PreparedStatement ps = dbConn.prepareStatement("update OD_88_tinderLiked set date=? where userId_who=? and userId_whom=?")){
            ps.setTimestamp(1, new Timestamp(like.getDate().getTime()));
            ps.setInt(2, like.getUserIdWho());
            ps.setInt(3, like.getUserIdWhom());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean check(Like like) {
        boolean flag = false;
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT userId_who, userId_whom FROM OD_88_tinderLiked where userId_who=? and userId_whom=?")){
            ps.setInt(1, like.getUserIdWho());
            ps.setInt(2, like.getUserIdWhom());
            ResultSet rSet = ps.executeQuery();
            int userId_whoSql = 0;
            int userId_whomSql = 0;
            while (rSet.next()){
                userId_whoSql = rSet.getInt("userId_who");
                userId_whomSql = rSet.getInt("userId_whom");
            }
            flag = (like.getUserIdWho() == userId_whoSql) && (like.getUserIdWhom() == userId_whomSql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
}
