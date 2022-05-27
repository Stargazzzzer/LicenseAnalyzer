package com.sustech.licenseanalyzer.controller;

import com.sustech.licenseanalyzer.service.LicenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LicenseController {
    @RequestMapping("/license")
    @ResponseBody
    public String licenseHello() {
        return "Get License Hello";
    }

    @GetMapping(value = "/license/details")
    public Object getAllLicense() {
        return LicenseService.getAllLicense();
    }

    @GetMapping(value = "/license/by_topic")
    public Object getLicenseTopic() {
        return LicenseService.getLicenseTopic();
    }

    @GetMapping(value = "/license/stars")
    public Object getStarsByLicense() {
        return LicenseService.getStarByLicense();
    }

    @GetMapping(value = "/license/forks")
    public Object getForksByLicense() {
        return LicenseService.getForkByLicense();
    }
}
