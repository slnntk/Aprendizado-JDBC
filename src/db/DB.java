package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB{

    private static Connection conn = null;

    public static Connection getConnection(){
        //Testar se o objeto Connection (conn) continua nulo, se continuar a connection ainda não foi feita.
        if (conn == null){
            try {
                // Criar um objeto props que vai chamar a função load properties para justamente receber todas as propriedades necessarias para a connection
                Properties props = loadProperties();
                // String url vai receber a url, o props.getProperty funciona como um map, então é enviado o valor da key e é recebido o valor
                String url = props.getProperty("dburl");
                // Agora é finalmente instanciar connection com a coneção, usando o driver
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    private static Properties loadProperties(){
        // Abrir o arquivo db.properties quje está na pasta do projeto.
        try (FileInputStream fs = new FileInputStream("db.properties")){
            // Criar o objeto properties que ficara responsavel por guardar as informações das propriedas do banco.
            Properties ps = new Properties();
            // Carregar as propriedades que estão no nosso arquivo para o objeto Properties
            ps.load(fs);
            return ps;
        } catch (IOException e) {
            throw new DbException("Error in load the properties" + e.getMessage());
        }
    }

    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e){
                throw new DbException("Error in close the Connection" + e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException("Error in close the Statement" + e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException("Error in close the ResultSet" + e.getMessage());
            }
        }
    }



}
