package com.example;

import java.sql.*;

public class DAO {
    String user = "root";
    String pw = "dbsdnjs2002";
    String url = "jdbc:mysql://localhost:3306/fusionproject";
    
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    
    public void database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
            System.out.println("\n√  DB 연결 성공");
        } catch (SQLException e) {
            System.out.println("\n! DB 연결 실패");
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("\n!!  드라이버 로딩 실패");
            System.err.println(e);
        }
    }
}
