package DAO;

import BLL.DeliveryService;
import Model.BaseProduct;
import Model.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CsvReader {
    public static boolean inputMenuItemsFromFile() {
        try {
            File file = new File("products.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] productCsv = line.split(",");
                MenuItem menuItem = new BaseProduct();

                menuItem.setTitle(productCsv[0]);
                menuItem.setRating(Double.parseDouble(productCsv[1]));
                menuItem.setCalories(Integer.parseInt(productCsv[2]));
                menuItem.setProtein(Integer.parseInt(productCsv[3]));
                menuItem.setFat(Integer.parseInt(productCsv[4]));
                menuItem.setSodium(Integer.parseInt(productCsv[5]));
                menuItem.setPrice(Integer.parseInt(productCsv[6]));
                DeliveryService.getInstance().addNewProduct(menuItem);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
