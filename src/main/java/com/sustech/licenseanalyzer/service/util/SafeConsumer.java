package com.sustech.licenseanalyzer.service.util;

import java.sql.SQLException;

@FunctionalInterface
public interface SafeConsumer<T> {
    void accept(T t) throws SQLException;
}