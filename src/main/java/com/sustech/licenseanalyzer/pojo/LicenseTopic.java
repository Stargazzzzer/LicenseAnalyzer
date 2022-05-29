package com.sustech.licenseanalyzer.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseTopic {
    public String license;
    public String topic;

    public static void getLicenseTopicFromResultSet(LicenseTopic res, ResultSet resultSet) {
        try {
            res.license = resultSet.getString(1);
            res.topic = resultSet.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
