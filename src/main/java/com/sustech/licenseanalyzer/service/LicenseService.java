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
                        select license, topic, cnt
                                                       from (select license,
                                                                    topic,
                                                                    count(topic)                                                        as cnt,
                                                                    row_number() over (partition by license order by count(topic) desc) as rnk
                                                             from project_topic
                                                             where license != 'null'
                                                               and topic != 'java'
                                                             group by license, topic) tmp
                                                       where rnk <= 3
                                                       order by license, cnt desc;""",
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
                                                          where license != 'null'
                                                          group by license
                                                          order by stars desc
                                                          limit 10;""",
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
                                                                   where license != 'null'
                                                                   group by license
                                                                   order by forks desc
                                                                   limit 10;""",
                (resultSet) -> {
                    Counter c = new Counter();
                    Counter.getCounterFromResultSet(c, resultSet);
                    res.add(c);
                });
        return res;
    }
}
