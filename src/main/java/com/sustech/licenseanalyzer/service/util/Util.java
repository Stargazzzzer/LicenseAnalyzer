package com.sustech.licenseanalyzer.service.util;

import com.sustech.licenseanalyzer.database.SQLDataSource;
import com.sustech.licenseanalyzer.pojo.Info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    // consumer is for inputting arguments, resHandler is for handling resultSet
    public static boolean select(String sql, SafeConsumer<PreparedStatement> consumer, SafeConsumer<ResultSet> resHandler) {
        SQLDataSource sqlDataSource = new SQLDataSource();
        boolean flag = false;
        sqlDataSource.openDatasource();
        try (Connection conn = sqlDataSource.con) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            consumer.accept(stmt);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                resHandler.accept(res);
                flag = true;
            }
            sqlDataSource.closeDatasource();
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean select(String sql, SafeConsumer<ResultSet> resHandler) {
        return select(sql, stmt -> {
        }, resHandler);
    }

}