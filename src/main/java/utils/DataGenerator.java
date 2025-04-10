package utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DataGenerator {
    public String generateName() {

        return "TA_" + new SimpleDateFormat("dd-MM_HH-mm-ss-SSS").format(new Date());
    }

    public String generateEmail() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder email = new StringBuilder(20);
        for (int i = 0; i <= 5; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            email.append(AlphaNumericString.charAt(index));
        }
        return "Email_" + email + "_" + new SimpleDateFormat("dd-MM_HH-mm-ss-SSS").format(new Date()) + "@TA.com";

    }

    public String generateMobileNumber() {

        String AlphaNumericString = "0123456789";
        StringBuilder mobile = new StringBuilder(7);
        for (int i = 0; i <= 7; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            mobile.append(AlphaNumericString.charAt(index));
        }
        return "011" + mobile;
    }
    public int generateBigNumber() {
        SecureRandom random = new SecureRandom();

        BigInteger bigInteger = new BigInteger(100, random);
        return bigInteger.intValue();
    }

    public String generateNumber(int digits) {

        String AlphaNumericString = "123456789";
        StringBuilder Number = new StringBuilder(4);
        for (int i = 0; i < digits; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            Number.append(AlphaNumericString.charAt(index));
        }
        return "" + Number;
    }

    public String generatePassword() {

        String AlphaNumericString = "Aa1*";
        StringBuilder password = new StringBuilder(30);
        for (int i = 0; i <= 30; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            password.append(AlphaNumericString.charAt(index));
        }
        return password.toString();
    }
    public static String generateRandomPrice() {
        Random random = new Random();
        // Generate a random integer between 1 and 99 (inclusive)
        int discountInt = random.nextInt(9900) + 100;
        double discount = discountInt / 100.0;

        // Create a DecimalFormat instance with the desired pattern
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Convert the double to a formatted string
        String formattedDiscount = decimalFormat.format(discount);

        // Remove trailing zeros and unnecessary decimal point
        formattedDiscount = formattedDiscount.replaceAll("\\.?0*$", "");

        return formattedDiscount;
    }

    public static String generateRandomDiscount() {
        Random random = new Random();
        // Generate a random integer between 1 and 99 (inclusive)
        int discountInt = random.nextInt(9900) + 100;
        double discount = discountInt / 100.0;
        // Convert the double to a string
        String discountString = Double.toString(discount);
        // Remove trailing zeros and unnecessary decimal point
        discountString = discountString.replaceAll("\\.?0*$", "");
        return discountString;
    }

    public static int generateRandomStock() {
        Random random = new Random();
        // Generate a random integer between 0 and 10000 (inclusive)
        return random.nextInt(10001);
    }
    public static int generateRandomQuota() {
        Random random = new Random();
        // Generate a random integer between 0 and 100 (inclusive)
        return random.nextInt(101);
    }
}
