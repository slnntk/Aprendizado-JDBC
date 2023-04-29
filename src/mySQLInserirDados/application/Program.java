package mySQLInserirDados.application;


import mySQLInserirDados.db.DbException;
import mySQLRecuperarDados.db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        // Conectar com BD
        Connection conn = null;
        PreparedStatement st = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {

            conn = DB.getConnection();
            // Receber o comando para inserção de dados
            try {
                st = conn.prepareStatement(
                        "INSERT INTO seller "
                       +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                       +"VALUES "
                       +"(?, ?, ?, ?, ?)");
                st.setString(1, "Carl Purple");
                st.setString(2, "carl@gmail.com");
                st.setDate(3, new java.sql.Date(sdf.parse("16/08/2002").getTime()));
                st.setDouble(4, 3000.0);
                st.setInt(5, 4);
                
                int rowsAffected = st.executeUpdate();
                System.out.println("Done! Rows affected: " + rowsAffected);
            } catch (ParseException | SQLException e) {
                throw new DbException(e.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
