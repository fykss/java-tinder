package dao.daoUsers;

import dto.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DbDaoUsers implements DaoUsers<User> {

    private Connection dbConn;

    public DbDaoUsers(Connection dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public void add(User user) {
        try(PreparedStatement ps = dbConn.prepareStatement("insert into OD_88_tinderUsers(id, name, surname, position, email, password, date, img, gender) values (?,?,?,?,?,?,?,?,?)")){
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getPosition());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPassword());
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            ps.setString(8, user.getUrlImg());
            ps.setString(9, user.getGender());
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User get(int id) {
        User user = null;
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT id, name, surname, position, img, gender FROM OD_88_tinderUsers WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()) {
                int idSql = rSet.getInt("id");
                String nameSql = rSet.getString("name");
                String surnameSql = rSet.getString("surname");
                String positionSql = rSet.getString("position");
                String urlImgSql = rSet.getString("img");
                String genderSql = rSet.getString("gender");
                user = new User(idSql, nameSql, surnameSql, positionSql, urlImgSql, genderSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int getId(User user) {
        int result = 0;
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT id FROM OD_88_tinderUsers where email=? and password=?")) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()) {
                result = rSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean check(User user) {
        boolean flag = false;
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT email, password FROM OD_88_tinderUsers where email=? AND password=?")) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()) {
                String emailSql = rSet.getString(1);
                String passwordSql = rSet.getString(2);
                flag = user.getEmail().equals(emailSql) && user.getPassword().equals(passwordSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Collection<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM OD_88_tinderUsers")) {
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()) {
                User user = new User(
                        rSet.getInt("id"),
                        rSet.getString("name"),
                        rSet.getString("surname"),
                        rSet.getString("position"),
                        rSet.getString("img"),
                        rSet.getString("gender"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Collection<User> getAllLikes(int id){
        ArrayList<User> users = new ArrayList<>();
        try(PreparedStatement ps = dbConn.prepareStatement("" +
                "SELECT OD_88_tinderLiked.userId_who," +
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
            ps.setInt(1, id);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                User user = new User(
                        rSet.getInt("userId_whom"),
                        rSet.getString("name"),
                        rSet.getString("surname"),
                        rSet.getString("position"),
                        rSet.getString("img"),
                        rSet.getDate("date"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateDate(int id){
        try(PreparedStatement ps = dbConn.prepareStatement("UPDATE OD_88_tinderUsers SET date = ? WHERE id = ?")) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis() + 2*60*60*1000));
            ps.setInt(2, id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean checkEmail(String email) {
        boolean flag = false;
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT email FROM OD_88_tinderUsers where email=?")) {
            ps.setString(1, email);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()) {
                String emailSql = rSet.getString(1);
                flag = email.equals(emailSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
