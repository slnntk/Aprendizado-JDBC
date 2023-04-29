package jdbcAtualizarDados.application;


import jdbcInserirDados.db.DB;
import jdbcInserirDados.db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement stUpSalary = null;
        PreparedStatement stDownSalary = null;

        try{
            conn = DB.getConnection();

            stUpSalary = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary - ? "
                    + "WHERE "
                    + "(DepartmentId = ?)");

            stUpSalary.setDouble(1, 200.0);
            stUpSalary.setInt(2, 2);

            int rowsAffected = stUpSalary.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(stUpSalary);
            DB.closeStatement(stDownSalary);
            DB.closeConnection();
        }
    }
}
