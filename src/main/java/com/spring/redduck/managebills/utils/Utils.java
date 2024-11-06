package com.spring.redduck.managebills.utils;

public class Utils {
    public static String returnMonth(String monthInNumber){
        String monthInLetters = "";
        switch(monthInNumber){
            case "01": monthInLetters = "Enero"; break;
            case "02": monthInLetters = "Febrero"; break;
            case "03": monthInLetters = "Marzo"; break;
            case "04": monthInLetters = "Abril"; break;
            case "05": monthInLetters = "Mayo"; break;
            case "06": monthInLetters = "Junio"; break;
            case "07": monthInLetters = "Julio"; break;
            case "08": monthInLetters = "Agosto"; break;
            case "09": monthInLetters = "Septiembre"; break;
            case "10": monthInLetters = "Octubre"; break;
            case "11": monthInLetters = "Noviembre"; break;
            case "12": monthInLetters = "Diciembre"; break;
        }
        return monthInLetters;
    }
}
