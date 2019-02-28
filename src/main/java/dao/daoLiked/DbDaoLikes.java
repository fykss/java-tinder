package dao.daoLiked;

import dto.Like;
import java.sql.*;
import java.util.ArrayList;
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
        try(PreparedStatement ps = dbConn.prepareStatement("update OD_88_tinderLiked set date where userId_whom=? and date=?")){
            ps.setInt(1, like.getUserIdWhom());
            ps.setTimestamp(2, new Timestamp(like.getDate().getTime()));
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Like> getAll() {
        ArrayList<Like> likes = new ArrayList<>();
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT userId_whom from OD_88_tinderLiked where userId_who=?")){
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                //rSet.getInt()
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return likes;
    }
}
