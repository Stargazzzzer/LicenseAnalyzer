package com.sustech.licenseanalyzer.service;

import com.sustech.licenseanalyzer.pojo.Info;

import java.util.ArrayList;
import java.util.List;

import static com.sustech.licenseanalyzer.pojo.Info.getInfoFromResultSet;
import static com.sustech.licenseanalyzer.service.util.Util.select;


public class InfoService {

    public static List<String> searchInfoByName(String name) {
        List<String> res = new ArrayList<>();
        select("""
                        select name
                        from project_info
                        where name like ?
                        group by name;""",
                (stmt) -> stmt.setString(1, '%' + name + '%'),
                (resultSet) -> {
                    res.add(resultSet.getString(1));
                });
        return res;
    }

    public static List<Info> getInfo(String name)
    {
        List<Info> res = new ArrayList<>();
        select("""
                        select project_info.name,
                               html_url,
                               description,
                               created_time,
                               stargazers_count,
                               forks_count,
                               pt.license,
                                    array_to_string(ARRAY(SELECT unnest(array_agg(distinct (topic)))), ' ')
                               from project_info
                                    left join project_topic pt on project_info.name = pt.name
                        where project_info.name = ?
                        group by html_url, description, created_time, stargazers_count, forks_count, pt.license, project_info.name;""",
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
