package com.sustech.licenseanalyzer;


import com.sustech.licenseanalyzer.pojo.Info;
import com.sustech.licenseanalyzer.service.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sustech.licenseanalyzer.service.InfoService.getInfoByLicense;


public class ServiceTests {
    public static void main(String[] args) throws SQLException {
        List<Info> res = new ArrayList<>();
        // res = getInfoByLicense("apache-2.0");
        int a = 2;
    }
}

