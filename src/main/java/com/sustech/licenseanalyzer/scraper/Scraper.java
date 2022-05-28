package com.sustech.licenseanalyzer.scraper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.io.*;

import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.google.gson.reflect.TypeToken;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

class Info {
    String name;
    String html_url;
    String description;
    String created_time;
    int stargazers_count;
    int forks_count;
    String[] topics;
    int size;
    int open_issues_count;
    String license;
}

public class Scraper extends Thread {
    int year;
    int user;
    int startMonth, endMonth;
    ArrayList<Client> clients = new ArrayList<>();
    public BufferedWriter bufferedWriter_info, bufferedWriter_topic;
    boolean done = false;

    Scraper(int year, int user, int startMonth, int endMonth, BufferedWriter bufferedWriter_info, BufferedWriter bufferedWriter_topic) {
        this.year = year;
        this.user = user;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.bufferedWriter_info = bufferedWriter_info;
        this.bufferedWriter_topic = bufferedWriter_topic;
    }

    public void register(String name, String password) {
        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(
                name, password);
        clientConfig.register(feature);
        Client client = ClientBuilder.newClient(clientConfig);
        client.property(ClientProperties.CONNECT_TIMEOUT, 30000);
        client.property(ClientProperties.READ_TIMEOUT, 30000);
        clients.add(client);
    }

    public void run() {
        Gson gson = new Gson();
        register("wcvanvan", "");
        register("wcvanvan1", "");
        register("wcvanvan2", "");
        register("wcvanvan3", "");
        register("wcvanvan4", "");
        register("wcvanvan5", "");
        register("wcvanvan6", "");
        register("wcvanvan7", "");
        register("wcvanvan8", "");
        register("wcvanvan9", "");

        Client client = clients.get(user);
        String request = null;
        try {
            java.lang.reflect.Type itemListType = new TypeToken<ArrayList<JsonObject>>() {
            }.getType();
            ArrayList<Info> infos = new ArrayList<>();
            java.lang.reflect.Type list2json = new TypeToken<ArrayList<Info>>() {
            }.getType();
            for (int month = startMonth; month <= endMonth; month++) {
                String monthString = Integer.toString(month);
                if (month < 10) {
                    monthString = "0" + month;
                }
                int days = 31;
                if (month == 2) {
                    if (year == 2020) {
                        days = 29;
                    } else {
                        days = 28;
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    days = 30;
                }
                for (int day = 1; day <= days; day++) {
                    String dayString = Integer.toString(day);
                    if (day < 10) {
                        dayString = "0" + day;
                    }
                    for (int hour = 0; hour < 24; hour += 2) {
                        String hourString = Integer.toString(hour);
                        String endHourString = Integer.toString(hour + 2);
                        if (hour < 10) {
                            hourString = "0" + hour;
                            if (hour < 8) {
                                endHourString = "0" + endHourString;
                            }
                        }
                        String endDayString = Integer.toString(day + 1);
                        if (day < 9) {
                            endDayString = "0" + endDayString;
                        }
                        String endMonthString = Integer.toString(month + 1);
                        if (month < 9) {
                            endMonthString = "0" + endMonthString;
                        }
                        String startTime = year + "-" + monthString + "-" + dayString + "T" + hourString + ":00:00Z";
                        String endTime;
                        if (hour == 22) {
                            if (day == days) {
                                if (month == 12) {
                                    endTime = year + 1 + "-01-01T00:00:00Z";
                                } else {
                                    endTime = year + "-" + endMonthString + "-01T00:00:00Z";
                                }
                            } else {
                                endTime = year + "-" + monthString + "-" + endDayString + "T00:00:00Z";
                            }
                        } else {
                            endTime = year + "-" + monthString + "-" + dayString + "T" + endHourString + ":00:00Z";
                        }
                        for (int page = 1; page <= 10; page++) {
                            request = "https://api.github.com/search/repositories?q=language:java+sort:stars+created:" + startTime + ".." + endTime + "&per_page=100&page=" + page;
                            WebTarget webTarget = client.target(request);
                            Response response;
                            int status;
                            while ((status = (response = webTarget.request("application/json").get()).getStatus()) != 200) {
                                response.close();
                                client = clients.get(++user);
                                webTarget = client.target(request);
                                if (user >= 9) {
                                    user = -1;
                                }
                            }
//                            System.out.println("success");
                            JsonObject jsonObject = response.readEntity(JsonObject.class);
                            ArrayList<JsonObject> infoArray = gson.fromJson(jsonObject.getAsJsonArray("items"), itemListType);
                            response.close();
                            if (infoArray.isEmpty()) {
                                break;
                            } else if (page == 1 && Integer.parseInt(jsonObject.get("total_count").toString()) > 1000) {
                                System.err.println("overflow: " + request);
                                break;
                            }
                            System.out.println(currentThread().getName() + " " + day);
                            write(infoArray, gson);
                        }
                    }
                }
            }
            bufferedWriter_topic.close();
            bufferedWriter_info.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(currentThread().getName() + "work done");
        done = true;
    }

    private void write(ArrayList<JsonObject> infoArray, Gson gson) {
        for (JsonObject object : infoArray) {
            Info info = new Info();
            info.name = object.get("name").toString().replaceAll("\"", "").replaceAll(",", "");
            info.topics = gson.fromJson(object.get("topics"), String[].class);
            if (JsonNull.INSTANCE.equals(object.get("license"))) {
                info.license = null;
            } else {
                info.license = object.getAsJsonObject("license").get("key").toString().replaceAll("\"", "").replaceAll(",", "");
            }
            for (String s : info.topics) {
                s = s.replaceAll("\"", "").replaceAll(",", "");
                try {
                    bufferedWriter_topic.write(info.name + "," + info.license + "," + s + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            info.html_url = object.get("html_url").toString().replaceAll("\"", "").replaceAll(",", "");
            info.created_time = object.get("created_at").toString().replaceAll("\"", "").replaceAll(",", "");
            info.description = object.get("description").toString().replaceAll("\"", "").replaceAll(",", "");
            info.stargazers_count = Integer.parseInt(object.get("stargazers_count").toString());
            info.forks_count = Integer.parseInt(object.get("forks_count").toString());
            info.size = Integer.parseInt(object.get("size").toString());
            info.open_issues_count = Integer.parseInt(object.get("open_issues_count").toString());
            try {
                bufferedWriter_info.write(info.name + "," + info.html_url + "," + info.description +
                        "," + info.created_time + "," + info.stargazers_count + "," + info.forks_count + "," + info.size + "," + info.open_issues_count + "," + info.license + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}