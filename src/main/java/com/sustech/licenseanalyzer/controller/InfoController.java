package com.sustech.licenseanalyzer.controller;

import com.sustech.licenseanalyzer.service.InfoService;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfoController {
    @RequestMapping("/info")
    @ResponseBody
    public String infoHello() {
        return "Get Info Hello";
    }

    @GetMapping(value = "/info/search/{name}")
    public Object searchByName(@PathVariable("name") String name) {
        return InfoService.searchInfoByName(name);
    }

    @GetMapping(value = "/info/get/{name}")
    public Object getByName(@PathVariable("name") String name) {
        return InfoService.getInfo(name);
    }

    @GetMapping(value = "/info/by_license/{license_name}")
    public Object getByLicense(@PathVariable("license_name") String licenseName) {
        return InfoService.getInfoByLicense(licenseName);
    }
}
