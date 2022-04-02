package com.example.data.util;

import java.util.UUID;


public class UuidUtils {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
