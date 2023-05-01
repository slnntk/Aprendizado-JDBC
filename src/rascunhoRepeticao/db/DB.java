package rascunhoRepeticao.db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null){
            try {
                Properties ps = loadProperties();
                conn = DriverManager.getConnection(ps.getProperty("dburl"), ps);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    private static Properties loadProperties(){
        try (FileInputStream fl = new FileInputStream("db.properties")) {
            Properties ps = new Properties();
            ps.load(fl);
            return ps;
        }catch (IOException e) {
            throw new DbException("Error in the load the archive");
        }
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new jdbcRecuperarDados.db.DbException("Error in close the Statement st " + e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new jdbcRecuperarDados.db.DbException("Error in close the ResultSet " + e.getMessage());
            }
        }
    }

    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e){
                throw new DbException("Error in close the connection " + e.getMessage());

            }
        }
    }





}
