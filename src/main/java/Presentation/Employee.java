package Presentation;

import BLL.DeliveryService;
import BLL.Order;
import Model.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Employee implements Observer {

    public JButton back = new JButton("Back");

    private JTable table;
    private JPanel tPanel;

    public Employee(){
        JFrame frame = new JFrame("Employee");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        JPanel p1 = new JPanel();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen backButton = new LoginScreen();
                frame.dispose();
            }
        });

        p1.add(back);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(p1);

        tPanel = new JPanel();
        List<String> columns = new ArrayList<>();
        List<String[]> values = new ArrayList<>();

        columns.add("Order ID");
        columns.add("Contents");

        for(Order o : DeliveryService.getInstance().getOrders().keySet()) {
            ArrayList<MenuItem> mi = DeliveryService.getInstance().getOrders().get(o);
            String orderContent = "";
            for(MenuItem m : mi) {
                orderContent  += m.getTitle() + ", ";
            }
            values.add(new String[] {o.getOrderId()+"", orderContent});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        table = new JTable(tableModel);
        tPanel.setLayout(new BorderLayout());
        tPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        tPanel.add(table.getTableHeader(), BorderLayout.NORTH);

        tPanel.setVisible(true);
        //
        tPanel.setPreferredSize(new Dimension(800,500));

        panel.add(tPanel);
        frame.setVisible(true);
        frame.setContentPane(panel);
    }

    @Override
    public void update(Observable o, Object arg) {
        List<String> columns = new ArrayList<>();
        List<String[]> values = new ArrayList<>();

        columns.add("Order ID");
        columns.add("Contents");

        for(Order order : DeliveryService.getInstance().getOrders().keySet()) {
            ArrayList<MenuItem> mi = DeliveryService.getInstance().getOrders().get(order);
            String orderContent = "";
            for(MenuItem m : mi) {
                orderContent  += m.getTitle() + ", ";
            }
            values.add(new String[] {order.getOrderId()+"", orderContent});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        table = new JTable(tableModel);

        tPanel.removeAll();
        tPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tPanel.add(table.getTableHeader(), BorderLayout.NORTH);
    }
}
