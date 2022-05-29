package com.sustech.licenseanalyzer.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseTopic {
    public String license;
    public String[] topics;

    public static void getLicenseTopicFromResultSet(LicenseTopic res, ResultSet resultSet) {
        try {
            res.license = resultSet.getString(1);
            res.topics = resultSet.getString(2).split(" ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
