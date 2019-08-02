package com.college.medicare.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    public static String readString(String error) {
        String str = readStringWithCheck();
        if (str.isEmpty())
            do {
                if (error == null)
                    System.out.println("Please enter valid string(minimum char: 1, maximum char: 30)");
                str = readStringWithCheck();
            } while (str.isEmpty());
        return str;
    }

    public static int readInt(int maxValue) {
        int str = (int) readIntWithCheck();
        if (str > maxValue)
            do {
                System.out.println("Please enter only mentioned number");
                str = (int) readIntWithCheck();
            } while (str > maxValue);
        return str;
    }


    public static String readMobile() {
        String str = readMobileWithCheck();
        if (str.length() != 10)
            do {
                System.out.println("Please enter only 10 digit number");
                str = readMobileWithCheck();
            } while (str.length() != 10);
        return str;
    }

    public static Date readDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(readStringWithCheck());
        } catch (ParseException e) {
            System.out.println("Please enter valid date");
            date = readDate();
        }
        if (date == null) {
            System.out.println("Please enter valid date");
            date = readDate();
        }
        return date;
    }

    private static long readIntWithCheck() {
        Scanner scanner = new Scanner(System.in);
        long str = 0;
        try {
            str = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter number");
            str = readIntWithCheck();
        }
        return str;
    }

    private static String readStringWithCheck() {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        try {
            str = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter string");
            str = readStringWithCheck();
        }
        return str;
    }

    private static String readMobileWithCheck() {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        try {
            str = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Please enter valid number");
            str = readStringWithCheck();
        }
        return str;
    }
}
