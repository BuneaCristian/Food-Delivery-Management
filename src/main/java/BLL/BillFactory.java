package BLL;

import Model.MenuItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BillFactory {
    private static int nrBills;

    public static void generateBill(Order o, ArrayList<MenuItem> cart) {
        try {
            PrintWriter writer = new PrintWriter("bill" + nrBills +".txt", StandardCharsets.UTF_8);

            writer.println("Bill no: " + nrBills);
            writer.println("Order ID: " + o.getOrderId());
            writer.println("Client ID: " + o.getClientId());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime orderTime = o.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            writer.println(dtf.format(orderTime));
            writer.println();

            int total = 0;
            for(MenuItem mi : cart) {
                writer.println("Product: " + mi.getTitle());
                writer.println("Price: " + mi.getPrice());
                writer.println("KCal/ Protein/ Fat/ Sodium");
                writer.println(mi.getCalories() + "/ " +
                        mi.getProtein() + "/ " +
                        mi.getFat() + "/ " +
                        mi.getSodium());
                writer.println();
                total += mi.getPrice();
            }

            writer.println();
            writer.println("Total bill: " + total);
            writer.close();
            nrBills++;
        } catch (IOException e) {
            System.out.println("Error generating bill");
        }
    }
}
