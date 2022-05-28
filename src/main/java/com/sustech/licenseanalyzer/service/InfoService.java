package com.sustech.licenseanalyzer.service;

import com.sustech.licenseanalyzer.pojo.Info;

import java.util.ArrayList;
import java.util.List;

import static com.sustech.licenseanalyzer.pojo.Info.getInfoFromResultSet;
import static com.sustech.licenseanalyzer.service.util.Util.select;


public class InfoService {

    public static List<Info> getInfoByName(String name) {
        List<Info> res = new ArrayList<>();
        select("""
                        select pt.name, html_url, description, created_time, stargazers_count, forks_count, pt.license, array_to_string(ARRAY(SELECT unnest(array_agg(distinct(topic)))),' ')
                               from project_info join project_topic pt on project_info.name = pt.name
                        where pt.name like ?\s
                        group by pt.name, html_url, description, created_time, stargazers_count, forks_count, pt.license;""",
                (stmt) -> stmt.setString(1, '%' + name + '%'),
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
