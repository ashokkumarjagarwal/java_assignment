package com.test.util;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonUtil {

    public boolean isValidEmail(String emailId){
        if (Objects.isNull(emailId))
            return false;
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }

    public String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public boolean isNullOrEmpty(String str){
        return Objects.isNull(str) || str.isEmpty();
    }
}
