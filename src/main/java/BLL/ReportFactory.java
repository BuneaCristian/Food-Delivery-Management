package BLL;

import DAO.AccountManagement;
import DAO.User;
import Model.MenuItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class ReportFactory {

    public static void generateReport(int type, String startHour, String endHour, String times, String value, String day) {
        int iStartHour = (!startHour.isEmpty())? Integer.parseInt(startHour) : 0;
        int iEndHour = (!endHour.isEmpty())? Integer.parseInt(endHour) : 0;
        int iTimes = (!times.isEmpty())? Integer.parseInt(times) : 0;
        int iValue = (!value.isEmpty())? Integer.parseInt(value) : 0;
        int iDay = (!day.isEmpty())? Integer.parseInt(day) : 0;

        try {
            PrintWriter writer = new PrintWriter("report" +
                    (type == 1? "tInterval" :
                    type == 2? "popular" :
                    type == 3? "loyal" :
                            "dayProducts") +".txt", StandardCharsets.UTF_8);
            if(type == 1) {
                writer.println(report1(iStartHour, iEndHour));
            }
            if(type == 2) {
                writer.println(report2(iTimes));
            }
            if(type == 3) {
                writer.println(report3(iTimes, iValue));
            }
            if(type == 4) {
                writer.println(report4(iDay));
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error generating report");
        }
    }

    private static String report1(int startHour, int endHour) {
        String result = "";
        List<Order> orders = DeliveryService.getInstance().getOrders().keySet().stream().filter(o -> o.getOrderDate().getHours() >= startHour && o.getOrderDate().getHours() <= endHour).collect(Collectors.toList());
        for (Order o : orders) {
            result += o.toString() + "\n";
        }
        return result;
    }

    private static String report2(int times) {
        long freq = 0;

        String result = "";
        for (MenuItem item : DeliveryService.getInstance().getMenu()) {
            freq = DeliveryService.getInstance().getOrders().entrySet().stream().filter(p -> p.getValue().contains(item)).count();
            if (freq >= times)
                result += item.getTitle() + "\n";
        }
        return result;
    }

    private static String report3(int times, int value) {
        String result = "";
        for (User u : AccountManagement.users) {
            long nb = DeliveryService.getInstance().getOrders().entrySet().stream().filter(p -> p.getKey().getClientId() == u.getUserId()).count();
            if (nb >= times) {
                int val = 0;
                List<Order> filtered = DeliveryService.getInstance().getOrders().keySet().stream().filter(p -> p.getClientId() == u.getUserId()).collect(Collectors.toList());
                for (Order o : filtered) {
                    List<MenuItem> list = DeliveryService.getInstance().getOrders().get(o);
                    for(MenuItem item:list){
                        val+=item.computePrice();
                    }
                }
                if (val >= value) {
                    result += u.getUsername() + "\n";
                }
            }
        }
        return result;
    }

    private static String report4(int day) {
        String result = "";
        List<MenuItem> products = DeliveryService.getInstance().getOrders().entrySet().stream().filter(p -> p.getKey().getOrderDate().getDay() == day).flatMap(p -> p.getValue().stream()).collect(Collectors.toList());
        for (MenuItem itm : products) {
            long times = DeliveryService.getInstance().getOrders().entrySet().stream().filter(p -> p.getValue().contains(itm)).count();
            result += itm.getTitle() + " ,times: " + times + "\n";
        }
        return result;
    }
}
