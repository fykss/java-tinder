package dao.daoLiked;

import dto.Like;
import dto.LikeExtra;
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
    public void del(Like like) {
        try(PreparedStatement ps = dbConn.prepareStatement("DELETE FROM OD_88_tinderLiked WHERE userId_who=? and userId_whom=?")){
            ps.setInt(1, like.getUserIdWho());
            ps.setInt(2, like.getUserIdWhom());
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

    @Override
    public Collection<Like> getAll(int id){
        ArrayList<Like> likes = new ArrayList<>();
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM OD_88_tinderLiked")){
            ps.setInt(1, id);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                Like like = new Like(
                        rSet.getInt("userId_who"),
                        rSet.getInt("userId_whom"),
                        rSet.getDate("date"));
                likes.add(like);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return likes;
    }

    public Collection<LikeExtra> getAllLikesUser(int idUser){
        ArrayList<LikeExtra> likes = new ArrayList<>();
        try(PreparedStatement ps = dbConn.prepareStatement("" +
                "SELECT OD_88_tinderLiked.id,\n" +
                "       OD_88_tinderLiked.userId_who," +
                "       OD_88_tinderLiked.userId_whom,\n" +
                "       OD_88_tinderUsers.date,\n" +
                "       OD_88_tinderUsers.name,\n" +
                "       OD_88_tinderUsers.surname,\n" +
                "       OD_88_tinderUsers.img,\n" +
                "       OD_88_tinderUsers.position\n" +
                "FROM OD_88_tinderLiked\n" +
                "INNER JOIN OD_88_tinderUsers \n" +
                "  ON OD_88_tinderLiked.userId_whom = OD_88_tinderUsers.id \n" +
                "WHERE userId_who=?")){
            ps.setInt(1, idUser);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                int idSql = rSet.getInt("id");
                int userId_whoSql = rSet.getInt("userId_who");
                int userId_whomSql = rSet.getInt("userId_whom");
                Date dateSql = rSet.getDate("date");
                String nameSql = rSet.getString("name");
                String surnameSql = rSet.getString("surname");
                String imgSql = rSet.getString("img");
                String positionSql = rSet.getString("position");
                LikeExtra likeExtra = new LikeExtra(idSql, userId_whoSql, userId_whomSql, dateSql, nameSql, surnameSql, imgSql, positionSql);
                likes.add(likeExtra);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return likes;
    }
}
