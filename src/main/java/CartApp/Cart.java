package CartApp;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Cart {

    public static void main(String[] args) {
        System.out.println("Select a language: ");
        System.out.println("1. English");
        System.out.println("2. Finnish");
        System.out.println("3. Swedish");
        System.out.println("4. Japanese");

        Scanner in = new Scanner(System.in);
        Locale locale;
        int lang = in.nextInt();

        switch (lang) {
            case 1:
                locale = new Locale("en", "US");
                break;
            case 2:
                locale = new Locale("fi", "FI");
                break;
            case 3:
                locale = new Locale("sv", "SE");
                break;
            case 4:
                locale = new Locale("ja", "JP");
                break;
            default:
                locale = new Locale("en", "US");
                break;
        }

        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("messages", locale);
        } catch (Exception e) {
            rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        }

        System.out.println(rb.getString("items"));

        double total = 0;
        int items = in.nextInt();
        for (int i = 0; i < items; i++) {
            System.out.println(rb.getString("price") + " " + (i + 1) + ": ");
            double price = in.nextDouble();
            System.out.println(rb.getString("quantity") + (i + 1) + ": ");
            int quantity = in.nextInt();
            Calculator calc = new Calculator(price, quantity);
            total += calc.calculateTotal();
        }

        System.out.printf(rb.getString("total") + " %.2f " + rb.getString("euro") + "\n", total);
    }
}
