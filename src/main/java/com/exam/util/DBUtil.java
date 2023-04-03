package com.exam.util;

import com.exam.exception.SQLErrorException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    // 생성해줘야 Article 클래스에서 사용 가능.
    public class DBUtil {
        private HttpServletRequest res;
        private HttpServletResponse resp;

        public DBUtil(HttpServletRequest res, HttpServletResponse resp) {
            this.res = res;
            this.resp = resp;
        }

        public Map<String, Object> selectRow(Connection dbConn, String sql) {
            List<Map<String, Object>> rows = selectRows(dbConn, sql);

            if (rows.size() == 0) {
                return new HashMap<>();
            }

            return rows.get(0);
        }

        public List<Map<String, Object>> selectRows(Connection dbConn, String sql){
            List<Map<String, Object>> rows = new ArrayList<>();

            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = dbConn.createStatement();
                rs = stmt.executeQuery(sql);
                ResultSetMetaData metaData = rs.getMetaData();
                int columnSize = metaData.getColumnCount();

            while (rs.next()) {

                Map<String, Object> row = new HashMap<>();

                for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                    String columnName = metaData.getColumnName(columnIndex + 1);
                    Object value = rs.getObject(columnName);

                    if (value instanceof Long) {
                        int numValue = (int) (long) value;
                        row.put(columnName, numValue);
                    } else if (value instanceof Timestamp) {
                        String dateValue = value.toString();
                        dateValue = dateValue.substring(0, dateValue.length() - 2);
                        row.put(columnName, dateValue);
                    } else {
                        row.put(columnName, value);
                    }
                }
                rows.add(row);
            }
            } catch (SQLException e) {
                Util.printEx("SQL 예외, SQL : " + sql,resp, e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        Util.printEx("SQL 예외, rs 닫기 " ,resp, e);
                    }
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        Util.printEx("SQL 예외, stmt 닫기" ,resp,e);
                    }
                }
            }

            return rows;
        }

        public int selectRowIntValue(Connection dbConn, String sql) {
            Map<String, Object> row = selectRow(dbConn, sql);
            for (String key : row.keySet()) {
                return (int) row.get(key);
            }
            return -1;
        }

        public String selectRowStringValue(Connection dbConn, String sql) {
            Map<String, Object> row = selectRow(dbConn, sql);

            for (String key : row.keySet()) {
                return (String) row.get(key);
            }
            return "";
        }

        public boolean selectRowBooleanValue(Connection dbConn, String sql) {
            Map<String, Object> row = selectRow(dbConn, sql);
            for (String key : row.keySet()) {
                return((int)row.get(key))==1;
            }
            return false;
        }
    }