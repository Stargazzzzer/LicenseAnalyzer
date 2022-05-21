//package com.sustech.licenseanalyzer.service.scraper;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.ArrayList;
//
//public class ScraperDriver {
//    public static void main(String[] args) {
//        int year = 2020;
////        File errorFile = new File("src/main/resources/" + year + "error.csv");
//        File file_info = new File("src/main/resources/" + year + ".csv");
//        File file_topic = new File("src/main/resources/" + year + "topics.csv");
//        BufferedWriter writer_info = null, writer_topic = null;
//        try {
//            writer_info = new BufferedWriter(new FileWriter(file_info));
//            writer_topic = new BufferedWriter(new FileWriter(file_topic));
//            Scraper.bufferedWriter_info = writer_info;
//            Scraper.bufferedWriter_topic = writer_topic;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ArrayList<Scraper> scrapers = new ArrayList<>();
//        Scraper scraper1 = new Scraper(2019, 0, 1, 1);
//        scraper1.start();
//        scrapers.add(scraper1);
//        Scraper scraper2 = new Scraper(2019, 5, 3, 3);
//        scraper2.start();
//        scrapers.add(scraper2);
////        scraper.Scraper scraper3 = new scraper.Scraper(2020, 4, 6, 7);
////        scraper3.start();
////        scrapers.add(scraper3);
////        scraper.Scraper scraper4 = new scraper.Scraper(2020, 6, 8, 9);
////        scraper4.start();
////        scrapers.add(scraper4);
////        scraper.Scraper scraper5 = new scraper.Scraper(2020, 8, 10, 12);
////        scraper5.start();
////        scrapers.add(scraper5);
//
//        StreamCloser streamCloser = new StreamCloser(writer_info, writer_topic, scrapers);
//        streamCloser.start();
//    }
//}
