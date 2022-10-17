package Presentation;

import BLL.DeliveryService;
import BLL.Order;
import DAO.Serializator;
import Model.BaseProduct;
import Model.CompositeProduct;
import Model.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Client {
    public JLabel name = new JLabel("Keyword");
    public JLabel rating = new JLabel("Rating");
    public JLabel proteins = new JLabel("Proteins");
    public JLabel calories = new JLabel("Calories");
    public JLabel fats = new JLabel("Fats");
    public JLabel sodium = new JLabel("Sodium");
    public JLabel price = new JLabel("Price");

    public JButton back = new JButton("Back");
    public JButton filters = new JButton("Apply filters");
    public JButton create = new JButton("Place order");

    public JTextField nm = new JTextField("", 20);
    public JTextField rat = new JTextField("", 10);
    public JTextField cal = new JTextField("", 10);
    public JTextField prot = new JTextField("", 10);
    public JTextField fat = new JTextField("", 10);
    public JTextField sod = new JTextField("", 10);
    public JTextField prc = new JTextField("", 10);

    private JTable table;
    private JPanel tPanel;

    public Client() {
        JFrame frame = new JFrame("Client");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        addFunctionality(frame);

        JPanel panel = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();
        JPanel p9 = new JPanel();

        tPanel = new JPanel();
        java.util.List<String> columns = new ArrayList<>();
        List<String[]> values = new ArrayList<>();

        columns.add("Title");
        columns.add("Rating");
        columns.add("Calories");
        columns.add("Protein");
        columns.add("Fat");
        columns.add("Sodium");
        columns.add("Price");
        columns.add("Contents");

        for(MenuItem mi : DeliveryService.getInstance().getMenu()) {
            if(mi instanceof CompositeProduct) {
                values.add(new String[] {mi.getTitle(), mi.getRating()+"", mi.getCalories()+"", mi.getProtein()+"", mi.getFat()+"", mi.getSodium()+"", mi.getPrice()+"", ((CompositeProduct)mi).getProductNames()});
            } else values.add(new String[] {mi.getTitle(), mi.getRating()+"", mi.getCalories()+"", mi.getProtein()+"", mi.getFat()+"", mi.getSodium()+"", mi.getPrice()+"", "Base Product"});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        table = new JTable(tableModel);
        tPanel.setLayout(new BorderLayout());
        tPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        tPanel.add(table.getTableHeader(), BorderLayout.NORTH);

        tPanel.setVisible(true);
        //
        tPanel.setPreferredSize(new Dimension(1200,500));

        p1.add(name);
        p1.add(nm);
        p2.add(rating);
        p2.add(rat);
        p3.add(calories);
        p3.add(cal);
        p4.add(proteins);
        p4.add(prot);
        p5.add(fats);
        p5.add(fat);
        p6.add(sodium);
        p6.add(sod);
        p7.add(price);
        p7.add(prc);
        p8.add(filters);
        p8.add(create);
        p8.add(back);
        p9.add(tPanel);

        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);
        panel.add(p6);
        panel.add(p7);
        panel.add(p8);
        panel.add(p9);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.setContentPane(panel);
    }

    private void refreshTable(List<MenuItem> menu) {
        List<String> columns = new ArrayList<>();
        List<String[]> values = new ArrayList<>();

        columns.add("Title");
        columns.add("Rating");
        columns.add("Calories");
        columns.add("Protein");
        columns.add("Fat");
        columns.add("Sodium");
        columns.add("Price");
        columns.add("Contents");

        for(MenuItem mi : menu) {
            if(mi instanceof CompositeProduct) {
                values.add(new String[] {mi.getTitle(), mi.getRating()+"", mi.getCalories()+"", mi.getProtein()+"", mi.getFat()+"", mi.getSodium()+"", mi.getPrice()+"", ((CompositeProduct)mi).getProductNames()});
            } else values.add(new String[] {mi.getTitle(), mi.getRating()+"", mi.getCalories()+"", mi.getProtein()+"", mi.getFat()+"", mi.getSodium()+"", mi.getPrice()+"", "Base Product"});
        }

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        table = new JTable(tableModel);

        tPanel.removeAll();
        tPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tPanel.add(table.getTableHeader(), BorderLayout.NORTH);
    }

    private void addFunctionality(JFrame frame) {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen backButton = new LoginScreen();
                frame.dispose();
            }
        });

        filters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MenuItem> filtered = DeliveryService.getInstance().getMenu().stream()
                        .filter(p -> (nm.getText().isEmpty() || p.getTitle().toLowerCase(Locale.ROOT).contains(nm.getText().toLowerCase(Locale.ROOT)))
                                && (rat.getText().isEmpty() || p.getRating() == Double.parseDouble(rat.getText()))
                                && (cal.getText().isEmpty() || p.getCalories() == Integer.parseInt(cal.getText()))
                                && (prot.getText().isEmpty() || p.getProtein() == Integer.parseInt(prot.getText()))
                                && (fat.getText().isEmpty() || p.getFat() == Integer.parseInt(fat.getText()))
                                && (sod.getText().isEmpty() || p.getSodium() == Integer.parseInt(sod.getText()))
                                && (prc.getText().isEmpty() || p.getPrice() == Integer.parseInt(prc.getText())))
                        .collect(Collectors.toList());
                refreshTable(filtered);
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuItem> cart = new ArrayList<>();
                int[] selection = table.getSelectedRows();
                for(int i = 0; i < selection.length; i++) {
                    MenuItem mi = new BaseProduct();
                    mi.setTitle(table.getValueAt(selection[i], 0).toString());
                    mi.setRating(Double.parseDouble(table.getValueAt(selection[i], 1).toString()));
                    mi.setCalories(Integer.parseInt(table.getValueAt(selection[i], 2).toString()));
                    mi.setProtein(Integer.parseInt(table.getValueAt(selection[i], 3).toString()));
                    mi.setFat(Integer.parseInt(table.getValueAt(selection[i], 4).toString()));
                    mi.setSodium(Integer.parseInt(table.getValueAt(selection[i], 5).toString()));
                    mi.setPrice(Integer.parseInt(table.getValueAt(selection[i], 6).toString()));
                    cart.add(mi);
                }
                Order newOrder = new Order(DeliveryService.getInstance().getOrders().size(), DeliveryService.getInstance().getClientId(), new Date());
                DeliveryService.getInstance().addNewOrder(newOrder, cart);
                JOptionPane.showMessageDialog(null, "Order placed");
                Serializator.serialize(DeliveryService.getInstance());
            }
        });
    }
}
