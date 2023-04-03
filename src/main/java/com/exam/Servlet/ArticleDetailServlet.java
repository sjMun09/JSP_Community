package com.exam.Servlet;

import com.exam.util.DBUtil;
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
@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
    // 좀더 적은 데이터는 doGet, 좀더 보안처리는 doPost
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        String url = "jdbc:mysql://127.0.0.1:3306/JSP_Community?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
        String user = "sjMun09";
        String password = "85947ads";
        try {
            // user pw 는 본인 마음대로 설정해도 됨. (넘겨줄거)
            conn = DriverManager.getConnection(url, user, password);
            // req랑 resp 매개변수로 넘겨줌, Util 클래스에 생성해줘야함.
            int id = Integer.parseInt(req.getParameter("id"));

            // 게시물 가져오기
            String sql = "SELECT * FROM article";
            // 그냥 못 가져오니까 맵형태로 가져오고 키로 받고, list
            Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

            // 찍어보기
            // System.out.println(sql);

            // 제목 넘기기
            req.setAttribute("articleRow", articleRow);
            // 이렇게 하면 웹앱을 안써도 상위 디렉토리로 이동가능.
            req.getRequestDispatcher("../article/detail.jsp").forward(req, resp);

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