//package com.sustech.licenseanalyzer.service.scraper;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class StreamCloser extends Thread {
//    BufferedWriter writer1, writer2;
//    ArrayList<Scraper> scrapers;
//
//    StreamCloser(BufferedWriter writer1, BufferedWriter writer2, ArrayList<Scraper> scrapers) {
//        this.writer1 = writer1;
//        this.writer2 = writer2;
//        this.scrapers = scrapers;
//    }
//
//    public void run() {
//        while (true) {
//            boolean done = true;
//            for (Scraper s : scrapers) {
//                if (!s.done) {
//                    done = false;
//                }
//            }
//            try {
//                if (done) {
//                    writer1.close();
//                    writer2.close();
//                    break;
//                } else {
//                    sleep(600000);
//                }
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//}
