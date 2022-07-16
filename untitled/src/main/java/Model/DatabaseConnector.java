package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DatabaseConnector {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbName = "quiz_db";
        String sqlUsername = "root";
        String sqlPassword = "rootroot";
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName +"?charachterEncoding=UTF8&user=" + sqlUsername + "&password=" + sqlPassword);
    }

    public static void resetTables() throws IOException, SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("resetTables.sql"));
        String str;
        while ((str = reader.readLine()) != null) {
            sql.append(str);
        }
        Connection myConn = getConnection();
        Statement statement = myConn.createStatement();
        String[] inst = sql.toString().split(";");
        for (String value : inst) {
            if (!value.trim().equals("")) {
                statement.executeUpdate(value);
            }
        }
    }
}
