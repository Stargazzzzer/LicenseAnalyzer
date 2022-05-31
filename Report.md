# CS209 - Final Project Report
12010323 张文程 12012524 陈张杰

### Introduction

Public repositories on GitHub are often used to share open source software. For your repository to truly be open source, it is necessary to license it so that others are free to use, change, and distribute the software. 

![image-20220531111232779](D:\Sustech\Java2\Project\LicenseAnalyzer\LicenseAnalyzer\ReportPics\image-20220531111232779.png)

In this project, we created a Java program which analyzes the open-source license of repositories on GitHub from past 10 years.



### Scraper Implementation





### Frontend Implementation





### Backend Implementation

Backend part of this project is based on JDBC and SpringBoot. The data retrieved from scraper are stored in PostgreSQL Database. The SpringBoot enable frontend to get the information by Restful API. After a query is received, it would invoke functions from service module and interact with the database by JDBC.

For example:

```java
@GetMapping(value = "/info/search/{name}")
public Object searchByName(@PathVariable("name") String name) {
    return InfoService.searchInfoByName(name);
}

@GetMapping(value = "/info/get/{name}")
public Object getByName(@PathVariable("name") String name) {
    return InfoService.getInfo(name);
}
```

These two mapping takes an argument 'name' and will return all information about the projects with this name.



### Result Analysis

