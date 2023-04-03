package com.exam.Servlet;

import com.exam.util.DBUtil;
import com.exam.util.SecSql;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;

// 게시물 불러오기
@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
    // 좀더 적은 데이터는 doGet, 좀더 보안처리는 doPost
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 언어 읽을 수 있게 추가
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset-utf-8");

        // db 연동부분 가져오기 App.java 확인해보고,db연동 확인하고, 필요한 객체 생성.

        // DB 연결시작
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.printf("[ClassNotFoundException 예외발생, %s]",e.getMessage());
            System.out.println("DB 드라이버 클래스 로딩 실패");
            return;
        }

        String url = "dbc:mysql://localhost:3306/JSP_Community";
        String user = "root";
        String password = "85947ads$%";
        try {
            conn = DriverManager.getConnection(url, user, password);

            SecSql sql = SecSql.from("SELECT *");
            sql.append("FROM article");
            sql.append("ORDER BY id DESC");
            List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

            // 제목 넘기기
            req.setAttribute("articleRows", articleRows);
            // 이렇게 하면 웹앱을 안써도 상위 디렉토리로 이동가능.
            req.getRequestDispatcher("../article/list.jsp").forward(req, resp);

        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // db 연결 끝
    }
}