package rascunhoRepeticao.application;


import rascunhoRepeticao.db.DbException;
import rascunhoRepeticao.db.DB;

import java.sql.*;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, Birthday, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)");
            st.setString(1, "Name");
            st.setString(2, "Email");
            st.setString(3, "Birth");
            st.setString(4, "Email");
            st.setString(5, "Email");
            )
        }


    }
}