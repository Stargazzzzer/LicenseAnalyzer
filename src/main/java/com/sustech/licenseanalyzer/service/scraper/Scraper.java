//package com.sustech.licenseanalyzer.service.scraper;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//import com.sustech.licenseanalyzer.pojo.Info;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Response;
//import java.io.BufferedWriter;
//import java.util.ArrayList;
//
//public class Scraper extends Thread {
//    int year;
//    int user;
//    int startMonth, endMonth;
//    ArrayList<Client> clients = new ArrayList<>();
//    public static BufferedWriter bufferedWriter_topic, bufferedWriter_info;
//    boolean done = false;
//
//    Scraper(int year, int user, int startMonth, int endMonth) {
//        this.year = year;
//        this.user = user;
//        this.startMonth = startMonth;
//        this.endMonth = endMonth;
//    }
//
//    public void register(String name, String password) {
//        ClientConfig clientConfig = new ClientConfig();
//        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(
//                name, password);
//        clientConfig.register(feature);
//        Client client = ClientBuilder.newClient(clientConfig);
////        Client client = ClientBuilder.newClient();
//        clients.add(client);
//    }
//
//    public void run() {
//        Gson gson = new Gson();
//        register("wcvanvan", "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");
//        register("wcvanvan1", "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");
//        register("wcvanvan2", "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");
//        register("wcvanvan3", "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");
//        register("wcvanvan4", "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");
//        register("wcvanvan5", "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");
//        register("wcvanvan6", "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");
//        register("wcvanvan7", "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");
//        register("wcvanvan8", "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");
//        register("wcvanvan9", "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");
//
//        Client client = clients.get(user);
//        String request = null;
//        try {
//            java.lang.reflect.Type itemListType = new TypeToken<ArrayList<JsonObject>>() {
//            }.getType();
//            ArrayList<Info> infos = new ArrayList<>();
//            java.lang.reflect.Type list2json = new TypeToken<ArrayList<Info>>() {
//            }.getType();
//            for (int month = startMonth; month <= endMonth; month++) {
//                String monthString = Integer.toString(month);
//                if (month < 10) {
//                    monthString = "0" + month;
//                }
//                int days = 31;
//                if (month == 2) {
//                    if (year == 2020) {
//                        days = 29;
//                    } else {
//                        days = 28;
//                    }
//                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
//                    days = 30;
//                }
//                for (int day = 31; day <= days; day++) { // warning: 改回来
//                    String dayString = Integer.toString(day);
//                    if (day < 10) {
//                        dayString = "0" + day;
//                    }
//                    for (int hour = 0; hour < 24; hour += 2) {
//                        String hourString = Integer.toString(hour);
//                        String endHourString = Integer.toString(hour + 2);
//                        if (hour < 10) {
//                            hourString = "0" + hour;
//                            if (hour < 8) {
//                                endHourString = "0" + endHourString;
//                            }
//                        }
//                        String endDayString = Integer.toString(day + 1);
//                        if (day < 9) {
//                            endDayString = "0" + endDayString;
//                        }
//                        String endMonthString = Integer.toString(month + 1);
//                        if (month < 9) {
//                            endMonthString = "0" + endMonthString;
//                        }
//                        String startTime = year + "-" + monthString + "-" + dayString + "T" + hourString + ":00:00Z";
//                        String endTime;
//                        if (hour == 22) {
//                            if (day == days) {
//                                if (month == 12) {
//                                    endTime = year + 1 + "-01-01T00:00:00Z";
//                                } else {
//                                    endTime = year + "-" + endMonthString + "-01T00:00:00Z";
//                                }
//                            } else {
//                                endTime = year + "-" + monthString + "-" + endDayString + "T00:00:00Z";
//                            }
//                        } else {
//                            endTime = year + "-" + monthString + "-" + dayString + "T" + endHourString + ":00:00Z";
//                        }
//                        for (int page = 1; page <= 10; page++) {
//                            request = "https://api.github.com/search/repositories?q=language:java+sort:stars+created:" + startTime + ".." + endTime + "&per_page=100&page=" + page;
//                            WebTarget webTarget = client.target(request);
//                            Response response;
//                            int status;
//                            while ((status = (response = webTarget.request("application/json").get()).getStatus()) != 200) {
//                                System.out.println(status);
//                                client = clients.get(++user);
//                                webTarget = client.target(request);
//                                if (user >= 9) {
//                                    user = -1;
//                                }
//                            }
////                            System.out.println("success");
//                            JsonObject jsonObject = response.readEntity(JsonObject.class);
//                            ArrayList<JsonObject> infoArray = gson.fromJson(jsonObject.getAsJsonArray("items"), itemListType);
//                            if (infoArray.isEmpty()) {
//                                break;
//                            } else if (page == 1 && Integer.parseInt(jsonObject.get("total_count").toString()) > 1000) {
//                                System.err.println("overflow: " + request);
//                                break;
//                            }
//                            System.out.println(request);
//                            write(infoArray, gson, infos);
//                        }
//                    }
//                }
//            }
//            String json = gson.toJson(infos, list2json);
//            assert bufferedWriter_info != null;
//            bufferedWriter_info.write(json);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(currentThread().getName() + "work done");
//        done = true;
//    }
//
//    private static synchronized void write(ArrayList<JsonObject> infoArray, Gson gson, ArrayList<Info> infos) {
//        for (JsonObject object : infoArray) {
//            Info info = new Info();
//            info.name = object.get("name").toString().replaceAll("\"", "");
//            info.topics = gson.fromJson(object.get("topics"), String[].class);
//            for (String s : info.topics) {
//                s = s.replaceAll("\"", "");
//            }
//            info.html_url = object.get("html_url").toString().replaceAll("\"", "");
//            ;
//            info.created_time = object.get("created_at").toString();
//            info.description = object.get("description").toString().replaceAll("\"", "");
//            ;
//            info.stargazers_count = Integer.parseInt(object.get("stargazers_count").toString());
//            info.forks_count = Integer.parseInt(object.get("forks_count").toString());
//            infos.add(info);
//        }
//    }
//}
