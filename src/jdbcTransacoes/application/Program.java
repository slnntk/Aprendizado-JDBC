package jdbcTransacoes.application;


import jdbcTransacoes.db.DB;
import jdbcTransacoes.db.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false); // deixar as atualizações pendentes
            st = conn.createStatement();
            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
            conn.commit();

            System.out.println(rows1);
            System.out.println(rows2);
        } catch (SQLException e){
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: "+ e1.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}