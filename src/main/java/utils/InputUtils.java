package utils;

import entity.dto.Page;
import entity.dto.Url;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputUtils {
    public static String input(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.print(text);
        return sc.nextLine();
    }

    public static Url url_parse(String url){
        String[] input = url.split("\\?");
        if(input.length == 1){
            return new Url(input[0],null);
        }
        Map<String, String> map = new HashMap<>();
        String[] queryString = input[1].split("&");
        for (String s : queryString) {
            String[] query = s.split("=");
            map.put(query[0], query[1]);
        }
        return new Url(input[0], map);
    }
}
