package com.ryoma2pick.sandbox.concurrency.url_shortener;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class UrlShortener {

    public static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/";
    public static final String PREFIX = "https://short/";
    private int counter = 0;
    private final Lock lock = new ReentrantLock();
    private final Map<String, Integer> idMap = new HashMap<>();
    private final Map<String, String> dnsMap = new HashMap<>();

    public int issueUniqueId(String s) {
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    int temp = counter;
                    idMap.put(s, temp);
                    dnsMap.put(s, baseConvert(temp));
                    counter++;
                    return counter;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("couldn't acquire lock");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return counter;
    }

    public String shortenedUrl(String originalUrl) {
        return PREFIX + dnsMap.get(originalUrl);
    }

    int id(String originalUrl) {
        return idMap.get(originalUrl);
    }

    String baseConvert(int number) {
        char[] charsArr = CHARS.toCharArray();

        if (number == 0) return String.valueOf(charsArr[0]);

        StringBuilder sb = new StringBuilder();
        int scale = (int) (Math.log(number) / Math.log(charsArr.length));
        int remainder = number;
        int intDivision = 0;

        for (int i = scale; i >= 0; i--) {
            intDivision = (int) (remainder / Math.pow(charsArr.length, i));
            sb.append(charsArr[(int) intDivision]);
            remainder = (int) (remainder - intDivision * Math.pow(charsArr.length, i));
        }
        return sb.toString();
    }

}
