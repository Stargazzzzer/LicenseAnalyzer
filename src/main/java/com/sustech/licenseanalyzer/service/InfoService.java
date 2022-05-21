package com.sustech.licenseanalyzer.service;

import com.sustech.licenseanalyzer.pojo.Info;
import com.sustech.licenseanalyzer.service.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sustech.licenseanalyzer.service.util.Util.select;

// return Info or List<Info>
public class InfoService {
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

    public static List<Info> getInfoByName(String name) {
        List<Info> res = new ArrayList<>();
        select("""
                        select pt.name, html_url, description, created_time, stargazers_count, forks_count, pt.license, array_to_string(ARRAY(SELECT unnest(array_agg(distinct(topic)))),' ')
                               from project_info join project_topic pt on project_info.name = pt.name
                        where pt.name = ?\s
                        group by pt.name, html_url, description, created_time, stargazers_count, forks_count, pt.license;""",
                (stmt) -> stmt.setString(1, name),
                (resultSet) -> {
                    Info i = new Info();
                    getInfoFromResultSet(i, resultSet);
                    res.add(i);
                });
        return res;
    }

    public static List<Info> getInfoByLicense(String license) {
        List<Info> res = new ArrayList<>();
        select("""
                        select pt.name, html_url, description, created_time, stargazers_count, forks_count, pt.license, array_to_string(ARRAY(SELECT unnest(array_agg(distinct(topic)))),' ')
                               from project_info join project_topic pt on project_info.name = pt.name
                        where pt.license = ?\s
                        group by pt.name, html_url, description, created_time, stargazers_count, forks_count, pt.license;""",
                (stmt) -> stmt.setString(1, license),
                (resultSet) -> {
                    Info i = new Info();
                    getInfoFromResultSet(i, resultSet);
                    res.add(i);
                });
        return res;
    }

}
