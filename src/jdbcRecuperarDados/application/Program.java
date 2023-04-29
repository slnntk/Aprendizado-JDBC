package jdbcRecuperarDados.application;


import jdbcRecuperarDados.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        // Conectar com BD
        Connection conn = null;
        // Preparar uma consulta SQL para buscar todos os dados que quero.
        Statement st = null;
        ResultSet rs = null;
        try {

            conn = DB.getConnection();
            st = conn.createStatement();
            // Executa a busca procurando todos os elementos de department e guarda em st
            st.executeQuery("select * from department");
            // ResultSet recebido de st.getResultSet();
            rs = st.getResultSet();

            // Percorrer rs.next enquanto for verdadeiro (quando chegar no final ele da false)
            while (rs.next()){
                //pegar o inteiro que está no campo "Id", pegar a string que está no campo name
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
