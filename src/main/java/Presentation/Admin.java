package Presentation;

import BLL.DeliveryService;
import BLL.ReportFactory;
import DAO.CsvReader;
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
import java.util.List;

public class Admin {

    public JButton imp = new JButton("Import");
    public JButton add = new JButton("Add");
    public JButton modify = new JButton("Modify");
    public JButton delete = new JButton("Delete");

    public JButton report1 = new JButton("Time interval");
    public JButton report2 = new JButton("Number of times");
    public JButton report3 = new JButton("Value higher than specified");
    public JButton report4 = new JButton("Day specified");
    public JButton create = new JButton("Create Menu");
    public JButton back = new JButton("Back");

    public JLabel nm = new JLabel("Name: ");
    public JLabel rt = new JLabel("Rating: ");
    public JLabel cal = new JLabel("Calories: ");
    public JLabel prot = new JLabel("Proteins: ");
    public JLabel fat = new JLabel("Fats: ");
    public JLabel sod = new JLabel("Sodium: ");
    public JLabel prc = new JLabel("Price: ");
    public JLabel sh = new JLabel("Start Hour: ");
    public JLabel eh = new JLabel("End Hour: ");
    public JLabel time = new JLabel("Times: ");
    public JLabel val = new JLabel("Value: ");
    public JLabel d = new JLabel("Day: ");
    public JLabel mn = new JLabel("Menu: ");

    public JTextField name = new JTextField("", 10);
    public JTextField rating = new JTextField("", 10);
    public JTextField calories = new JTextField("", 10);
    public JTextField proteins = new JTextField("", 10);
    public JTextField fats = new JTextField("", 10);
    public JTextField sodium = new JTextField("", 10);
    public JTextField price = new JTextField("", 10);

    public JTextField startHour = new JTextField("", 10);
    public JTextField endHour = new JTextField("", 10);
    public JTextField times = new JTextField("", 10);
    public JTextField value = new JTextField("", 10);
    public JTextField day = new JTextField("", 10);
    public JTextField menu = new JTextField("", 10);

    private JTable table;
    private JPanel tPanel;

    public Admin() {
        JFrame frame = new JFrame("Client");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        assignFunctionality(frame);

        JPanel panel = new JPanel();

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        p1.add(nm);
        p1.add(name);
        p1.add(rt);
        p1.add(rating);
        p1.add(cal);
        p1.add(calories);
        p1.add(prot);
        p1.add(proteins);
        p1.add(fat);
        p1.add(fats);
        p1.add(sod);
        p1.add(sodium);
        p1.add(prc);
        p1.add(price);

        p2.add(mn);
        p2.add(menu);
        p2.add(create);
        p2.add(imp);
        p2.add(add);
        p2.add(modify);
        p2.add(delete);

        p3.add(sh);
        p3.add(startHour);
        p3.add(eh);
        p3.add(endHour);
        p3.add(time);
        p3.add(times);
        p3.add(val);
        p3.add(value);
        p3.add(d);
        p3.add(day);

        p4.add(report1);
        p4.add(report2);
        p4.add(report3);
        p4.add(report4);
        p4.add(back);
        //
        tPanel = new JPanel();
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

        p5.add(tPanel);

        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.setContentPane(panel);
    }

    private void refreshTable() {
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

        for(MenuItem mi : DeliveryService.getInstance().getMenu()) {
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

    private void assignFunctionality(JFrame frame) {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen backButton = new LoginScreen();
                frame.dispose();
            }
        });

        imp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CsvReader.inputMenuItemsFromFile()) {
                    JOptionPane.showMessageDialog(null, "Import successful");
                    refreshTable();
                } else JOptionPane.showMessageDialog(null, "No file");
                Serializator.serialize(DeliveryService.getInstance());
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] deletion = table.getSelectedRows();
                for(int i = 0; i < deletion.length; i++) {
                    MenuItem mi = new BaseProduct();
                    mi.setTitle(table.getValueAt(deletion[i], 0).toString());
                    DeliveryService.getInstance().deleteProduct(mi);
                }
                refreshTable();
                Serializator.serialize(DeliveryService.getInstance());
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem mi = new BaseProduct(name.getText(),
                        calories.getText(),
                        proteins.getText(),
                        fats.getText(),
                        sodium.getText(),
                        price.getText());

                DeliveryService.getInstance().addNewProduct(mi);
                refreshTable();
                Serializator.serialize(DeliveryService.getInstance());
            }
        });

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuItem selected = new BaseProduct();
                selected.setTitle(table.getValueAt(table.getSelectedRow(), 0).toString());
                selected.setRating(Double.parseDouble(table.getValueAt(table.getSelectedRow(), 1).toString()));
                selected.setCalories(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString()));
                selected.setProtein(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString()));
                selected.setFat(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString()));
                selected.setSodium(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString()));
                selected.setPrice(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 6).toString()));

                MenuItem modified = new BaseProduct();
                modified.setTitle(selected.getTitle());

                if(!rating.getText().isEmpty()) {
                    modified.setRating(Double.parseDouble(rating.getText()));
                } else modified.setRating(selected.getRating());

                if(!calories.getText().isEmpty()) {
                    modified.setCalories(Integer.parseInt(calories.getText()));
                } else modified.setCalories(selected.getCalories());

                if(!proteins.getText().isEmpty()) {
                    modified.setProtein(Integer.parseInt(proteins.getText()));
                } else modified.setProtein(selected.getProtein());

                if(!fats.getText().isEmpty()) {
                    modified.setFat(Integer.parseInt(fats.getText()));
                } else modified.setFat(selected.getFat());

                if(!sodium.getText().isEmpty()) {
                    modified.setSodium(Integer.parseInt(sodium.getText()));
                } else modified.setSodium(selected.getSodium());

                if(!price.getText().isEmpty()) {
                    modified.setPrice(Integer.parseInt(price.getText()));
                } else modified.setPrice(selected.getPrice());

                DeliveryService.getInstance().changeProduct(modified);
                refreshTable();
                Serializator.serialize(DeliveryService.getInstance());
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem comp = new CompositeProduct();
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
                    ((CompositeProduct)comp).getProducts().add(mi);
                }
                comp.setTitle(menu.getText());
                ((CompositeProduct)comp).calculateContents();
                DeliveryService.getInstance().addNewProduct(comp);
                refreshTable();
                Serializator.serialize(DeliveryService.getInstance());
            }
        });

        report1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportFactory.generateReport(1, startHour.getText(), endHour.getText(), times.getText(), value.getText(), day.getText());
            }
        });

        report2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportFactory.generateReport(2, startHour.getText(), endHour.getText(), times.getText(), value.getText(), day.getText());
            }
        });

        report3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportFactory.generateReport(3, startHour.getText(), endHour.getText(), times.getText(), value.getText(), day.getText());
            }
        });

        report4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportFactory.generateReport(4, startHour.getText(), endHour.getText(), times.getText(), value.getText(), day.getText());
            }
        });
    }
}
