package com.sustech.licenseanalyzer.service;

import com.sustech.licenseanalyzer.pojo.Counter;
import com.sustech.licenseanalyzer.pojo.LicenseTopic;

import java.util.ArrayList;
import java.util.List;

import static com.sustech.licenseanalyzer.pojo.LicenseTopic.getLicenseTopicFromResultSet;
import static com.sustech.licenseanalyzer.service.util.Util.select;

public class LicenseService {
    public static List<Counter> getAllLicense() {
        List<Counter> res = new ArrayList<>();
        select("""
                        select license, count(license)
                        from project_info
                        group by license;""",
                (resultSet) -> {
                    Counter c = new Counter();
                    Counter.getCounterFromResultSet(c, resultSet);
                    res.add(c);
                });
        return res;
    }

    public static List<LicenseTopic> getLicenseTopic() {
        List<LicenseTopic> res = new ArrayList<>();
        select("""
                        select license, topic, count(topic)
                                       from project_topic
                                       where license != 'null'
                                       group by license, topic
                                       order by license;""",
                resultSet -> {
                    LicenseTopic lp = new LicenseTopic();
                    getLicenseTopicFromResultSet(lp, resultSet);
                    res.add(lp);
                });
        return res;
    }

    public static List<Counter> getStarByLicense() {
        List<Counter> res = new ArrayList<>();
        select("""
                        select license, sum(stargazers_count) as stars
                                         from project_info
                                         group by license;""",
                (resultSet) -> {
                    Counter c = new Counter();
                    Counter.getCounterFromResultSet(c, resultSet);
                    res.add(c);
                });
        return res;
    }

    public static List<Counter> getForkByLicense() {
        List<Counter> res = new ArrayList<>();
        select("""
                        select license, sum(forks_count) as forks
                                                  from project_info
                                                  group by license;""",
                (resultSet) -> {
                    Counter c = new Counter();
                    Counter.getCounterFromResultSet(c, resultSet);
                    res.add(c);
                });
        return res;
    }
}
