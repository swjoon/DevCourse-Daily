package util;

import java.util.Scanner;

public class InputUtils {
    public static String input(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.print(text);
        return sc.nextLine();
    }
}
