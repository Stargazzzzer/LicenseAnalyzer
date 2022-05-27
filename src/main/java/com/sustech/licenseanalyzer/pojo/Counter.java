package com.sustech.licenseanalyzer.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Counter {
    public String name;
    public int count;

    public static void getCounterFromResultSet(Counter res, ResultSet resultSet) {
        try {
            res.name = resultSet.getString(1);
            res.count = resultSet.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
