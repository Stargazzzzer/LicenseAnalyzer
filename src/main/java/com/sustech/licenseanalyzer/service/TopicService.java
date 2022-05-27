package com.sustech.licenseanalyzer.service;

import com.sustech.licenseanalyzer.pojo.Counter;
import com.sustech.licenseanalyzer.pojo.LicenseTopic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sustech.licenseanalyzer.service.util.Util.select;
import static com.sustech.licenseanalyzer.pojo.LicenseTopic.getLicenseTopicFromResultSet;

public class TopicService {


    public static List<Counter> getAllTopic() {
        List<Counter> res = new ArrayList<>();
        select("""
                        select topic,count(topic)
                        from project_topic
                        group by topic;""",
                (resultSet) -> {
                    Counter c = new Counter();
                    Counter.getCounterFromResultSet(c, resultSet);
                    res.add(c);
                });
        return res;
    }

}
