package com.spring.redduck.managebills.utils;

public class Utils {
    public static String returnMonth(String monthInNumber){
        String monthInLetters = "";
        switch(monthInNumber){
            case "1": monthInLetters = "Enero"; break;
            case "2": monthInLetters = "Febrero"; break;
            case "3": monthInLetters = "Marzo"; break;
            case "4": monthInLetters = "Abril"; break;
            case "5": monthInLetters = "Mayo"; break;
            case "6": monthInLetters = "Junio"; break;
            case "7": monthInLetters = "Julio"; break;
            case "8": monthInLetters = "Agosto"; break;
            case "9": monthInLetters = "Septiembre"; break;
            case "10": monthInLetters = "Octubre"; break;
            case "11": monthInLetters = "Noviembre"; break;
            case "12": monthInLetters = "Diciembre"; break;
        }
        return monthInLetters;
    }
}
