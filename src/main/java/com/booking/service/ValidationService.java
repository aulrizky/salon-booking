package com.booking.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan
    public static boolean validateInput(String input, String regexPattern){
       Pattern pattern = Pattern.compile(regexPattern);
       Matcher matcher = pattern.matcher(input);
       return matcher.matches();

    }
    public static String inputStringFunction(String regexPattern,String question,String errorQuestion){
        Scanner scanner = new Scanner(System.in);
        String input;
        Boolean isLoop = true;

        do {
            System.out.print(question);
            input = scanner.nextLine();
            if (validateInput(input, regexPattern)) {
                isLoop = false;
            } else {
                System.out.println(errorQuestion);
            }
        } while (isLoop);

        return input;
    }
    public static void validateCustomerId(){

    }

}
