package com.sustech.licenseanalyzer.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Info {
    public String name;
    public String html_url;
    public String description;
    public String created_time;
    public String license;
    public int stargazers_count;
    public int forks_count;
    public String[] topics;

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", html_url='" + html_url + '\'' +
                ", description='" + description + '\'' +
                ", created_time='" + created_time + '\'' +
                ", license='" + license + '\'' +
                ", stargazers_count=" + stargazers_count +
                ", forks_count=" + forks_count +
                ", topics=" + Arrays.toString(topics) +
                '}';
    }

    public static void getInfoFromResultSet(Info res, ResultSet resultSet) {
        try {
            res.name = resultSet.getString(1);
            res.html_url = resultSet.getString(2);
            res.description = resultSet.getString(3);
            res.created_time = resultSet.getString(4);
            res.stargazers_count = resultSet.getInt(5);
            res.forks_count = resultSet.getInt(6);
            res.license = resultSet.getString(7);
            res.topics = resultSet.getString(8).split(" ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
