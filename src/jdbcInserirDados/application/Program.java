package jdbcInserirDados.application;


import jdbcInserirDados.db.DbException;
import jdbcRecuperarDados.db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        // Conectar com BD
        Connection conn = null;
        PreparedStatement st = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            conn = DB.getConnection();

           /*
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                   +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                   +"VALUES "
                   +"(?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "Carl Purple");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("16/08/2002").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);*/

            st = conn.prepareStatement("insert into department (Name) values ('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done| Id = " + id);
                }
            } else {
                System.out.println("No rows affected! ");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
