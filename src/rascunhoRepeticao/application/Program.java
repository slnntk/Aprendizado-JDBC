package rascunhoRepeticao.application;


import rascunhoRepeticao.db.DbException;
import rascunhoRepeticao.db.DB;

import java.sql.*;

public class Program {
    public static void main(String[] args){

        Connection conn = null;
        PreparedStatement st = null;
        conn = DB.getConnection();
    }
}