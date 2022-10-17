package BLL;

import Model.MenuItem;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface IDeliveryProcessing {
    Map<Order, ArrayList<MenuItem>> getOrders();

    void setOrders(Map<Order, ArrayList<MenuItem>> orders);

    Set<MenuItem> getMenu();

    void setMenu(Set<MenuItem> menu);

    void addNewProduct(MenuItem mi);

    void deleteProduct(MenuItem mi);

    void changeProduct(MenuItem mi);

    void addNewOrder(Order o, ArrayList<MenuItem> shoppingList);
}
