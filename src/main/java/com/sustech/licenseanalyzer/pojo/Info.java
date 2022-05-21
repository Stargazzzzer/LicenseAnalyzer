package com.sustech.licenseanalyzer.pojo;

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
}
