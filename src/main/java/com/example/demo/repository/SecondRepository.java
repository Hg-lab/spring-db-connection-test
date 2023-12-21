package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
@Repository
public class SecondRepository {
    private HikariDataSource dataSource;
    private FirstRepository firstRepository;

    public SecondRepository(@Qualifier("secondDataSource") HikariDataSource dataSource, FirstRepository firstRepository) {
        this.dataSource = dataSource;
        this.firstRepository = firstRepository;
    }

    public Member findAll() {
        String sql = "select * from member";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            Thread.sleep(1000);
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                return new Member(id, name);
            } else {
                throw new NoSuchElementException();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.info("Second - findAll completed!");
            close(con, pstmt, rs);
        }
    }


    public void update(String memberName, int memberId) throws SQLException {
        String sql = "update member set name=? where id=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberName);
            pstmt.setInt(2, memberId);

            Thread.sleep(1000);

            con.setAutoCommit(false);
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw new SQLException(e);
        } finally {
            log.info("Second - update completed!");
            close(con, pstmt, null);
        }
    }


    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    private Connection getConnection() {
        Connection con = DataSourceUtils.getConnection(dataSource);
        return con;
    }
}
