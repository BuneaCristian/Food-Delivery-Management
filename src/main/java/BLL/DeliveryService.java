package BLL;

import DAO.Serializator;
import Model.MenuItem;

import java.io.Serializable;
import java.util.*;

public class DeliveryService extends Observable implements IDeliveryProcessing, Serializable {
    private Map<Order, ArrayList<MenuItem>> orders;
    private Set<MenuItem> menu;
    private int clientId;

    private static DeliveryService ds;

    static {
        ds = null;
    }

    private DeliveryService() {
        orders = new HashMap<>();
        menu = new TreeSet<>();
    }

    public static DeliveryService getInstance() {
        if(ds == null) {
            ds = Serializator.deserialize();
            if(ds == null) {
                ds = new DeliveryService();
            }
            return ds;
        } else return ds;
    }

    public Map<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public Set<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(Set<MenuItem> menu) {
        this.menu = menu;
    }

    public void addNewProduct(MenuItem mi) {
        ds.menu.add(mi);
    }

    public void deleteProduct(MenuItem mi) {
        ds.menu.remove(mi);
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void changeProduct(MenuItem mi) {
        deleteProduct(mi);
        addNewProduct(mi);
    }

    public void addNewOrder(Order o, ArrayList<MenuItem> shoppingList) {
        ds.orders.put(o, shoppingList);
        BillFactory.generateBill(o, shoppingList);
        setChanged();
        notifyObservers();
    }
}
