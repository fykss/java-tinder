package ua.com.danit;

import ua.com.danit.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppDb {
    public static void main(String[] args) throws SQLException {
        Connection connection = new DbConnection().connection();
        String createSQL = "CREATE TABLE IF NOT EXISTS public.OD_88_tinderUsers (" +
                "id(SQL) SERIAL PRIMARY KEY, " +
                "name VARCHAR(32), " +
                "surname VARCHAR(32), " +
                "position VARCHAR(32)," +
                "email VARCHAR(32)," +
                "password VARCHAR(32)," +
                "data TIMESTAMP," +
                "img VARCHAR (250)," +
                "gender VARCHAR(32))";
        PreparedStatement stm = connection.prepareStatement(createSQL);
        stm.execute();
    }
}