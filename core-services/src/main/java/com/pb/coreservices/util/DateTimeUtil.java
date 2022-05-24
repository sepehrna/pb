package com.pb.coreservices.util;

import java.sql.Timestamp;

public class DateTimeUtil {

    public static Timestamp getCurrentTime(){
        return Timestamp.valueOf(java.time.LocalDateTime.now());
    }
}
