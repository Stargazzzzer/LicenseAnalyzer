package com.sustech.licenseanalyzer.service;

import com.sustech.licenseanalyzer.pojo.Counter;

import java.util.ArrayList;
import java.util.List;

import static com.sustech.licenseanalyzer.service.util.Util.select;

public class TopicService {


    public static List<Counter> getAllTopic() {
        List<Counter> res = new ArrayList<>();
        select("""
                        select topic, count(topic) as cnt
                                       from project_topic
                                       group by topic
                                       order by cnt desc;""",
                (resultSet) -> {
                    Counter c = new Counter();
                    Counter.getCounterFromResultSet(c, resultSet);
                    res.add(c);
                });
        return res;
    }

}
