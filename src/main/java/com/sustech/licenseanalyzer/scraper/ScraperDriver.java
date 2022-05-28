package com.sustech.licenseanalyzer.scraper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ScraperDriver {
    public static void main(String[] args) {
        int year = 2021;
        File file_info1 = new File("src/main/resources/" + year + "_info1.csv");
        File file_topic1 = new File("src/main/resources/" + year + "_topic1.csv");

        BufferedWriter writer_info1 = null;
        BufferedWriter writer_topic1 = null;
        try {
            writer_info1 = new BufferedWriter(new FileWriter(file_info1));
            writer_topic1 = new BufferedWriter(new FileWriter(file_topic1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scraper scraper1 = new Scraper(2021, 0, 0, 12, writer_info1, writer_topic1);
        scraper1.start();
    }
}
