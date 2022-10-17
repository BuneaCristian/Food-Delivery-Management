package Presentation;

import BLL.DeliveryService;
import DAO.AccountManagement;
import DAO.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    public JLabel name = new JLabel("Name: ");
    public JLabel password = new JLabel("Password: ");
    public JTextField nm = new JTextField("", 10);
    public JTextField pswd = new JTextField("", 10);

    public JButton logIn = new JButton("LogIn");
    public JButton register = new JButton("Register");

    public JPanel panel;

    public LoginScreen() {
        JFrame frame = new JFrame("Food Delivery");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        panel = new JPanel();

        JPanel p1 = new JPanel();
        p1.add(name);
        p1.add(nm);
        p1.add(password);
        p1.add(pswd);

        JPanel p2 = new JPanel();
        p2.add(logIn);
        p2.add(register);

        JPanel p3 = new JPanel();

        panel.add(p1);
        panel.add(p2);
        panel.add(p3);

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User logon = AccountManagement.login(nm.getText(), pswd.getText());
                if(logon != null) {
                    if(logon.getPermissions().equals("client")) {
                        Client cl = new Client();
                    } else if(logon.getPermissions().equals("admin")) {
                        Admin a = new Admin();
                    } else {
                        Employee emp = new Employee();
                    }
                    DeliveryService.getInstance().setClientId(logon.getUserId());
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No user with given credentials");
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountManagement.createNewAccount(nm.getText(), pswd.getText());
                JOptionPane.showMessageDialog(null, "Register successful");
            }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.setContentPane(panel);
    }
}
