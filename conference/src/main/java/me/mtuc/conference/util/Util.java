package me.mtuc.conference.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class Util {

    private final ObjectMapper objectMapper;

    public static LocalDate stringToLocalDate(String birth){
        return LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public JsonNode stringToJson(String jsonObject) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonObject);
        return jsonNode;
    }
}
