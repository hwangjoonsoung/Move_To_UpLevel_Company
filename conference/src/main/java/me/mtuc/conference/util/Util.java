package me.mtuc.conference.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    public static LocalDate stringToLocalDate(String birth){
        return LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
