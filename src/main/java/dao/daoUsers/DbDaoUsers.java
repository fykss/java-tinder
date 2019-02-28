package dao.daoUsers;

import dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DbDaoUsers implements DaoUsers<User>{

    private Connection dbConn;
    public DbDaoUsers(Connection dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public User get(int id) {
        User user = null;
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT id, name, surname, position, img FROM OD_88_tinderUsers WHERE id=?")){
            ps.setInt(1, id);
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()){
                int idSql = rSet.getInt("id");
                String nameSql = rSet.getString("name");
                String surnameSql = rSet.getString("surname");
                String positionSql = rSet.getString("position");
                String urlImgSql = rSet.getString("img");
                user = new User(idSql,nameSql,surnameSql,positionSql,urlImgSql);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int getId(User user) {
        int result = 0;
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT id FROM OD_88_tinderUsers where email=? and password=?" )){
            ps.setString( 1, user.getEmail());
            ps.setString( 2, user.getPassword());
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()) {
                result = rSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean check(User user) {
        boolean flag = false;
        try (PreparedStatement ps = dbConn.prepareStatement("SELECT email, password FROM OD_88_tinderUsers where email=? AND password=?")){
            ps.setString( 1, user.getEmail());
            ps.setString( 2, user.getPassword());
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
        try(PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM OD_88_tinderUsers")){
            ResultSet rSet = ps.executeQuery();
            while (rSet.next()) {
                User user = new User(rSet.getInt("id"),
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
}
