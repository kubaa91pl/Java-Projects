package messenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Admin on 2016-06-26.
 */

public class Registration extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l6, l7, l4, l5, l8;
    JTextField tf1, tf2, tf4, tf6, tf7;
    JButton btn1, btn2, regOff;
    JPasswordField p1, p2;

    Registration() {
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration Form in Java");

        l1 = new JLabel("Registration Form to fill in:");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Name:");
        l3 = new JLabel("Surname:");
        l4 = new JLabel("Login:");
        l5 = new JLabel("Age:");
        l6 = new JLabel("Create Passowrd:");
        l7 = new JLabel("Confirm Password:");
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf4 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        p1 = new JPasswordField();
        p2 = new JPasswordField();

        btn1 = new JButton("Submit");
        btn2 = new JButton("Clear");
        regOff = new JButton("Close the Registrator");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        regOff.addActionListener(this);


        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        l4.setBounds(80, 230, 200, 30);
        l5.setBounds(80, 270, 200, 30);
        l6.setBounds(80, 150, 200, 30);
        l7.setBounds(80, 190, 200, 30);

        tf1.setBounds(300, 70, 200, 30);
        tf2.setBounds(300, 110, 200, 30);

        p1.setBounds(300, 150, 200, 30);
        p2.setBounds(300, 190, 200, 30);

        tf4.setBounds(300, 230, 200, 30);
        tf6.setBounds(300, 270, 200, 30);

        btn1.setBounds(50, 350, 100, 30);
        btn2.setBounds(170, 350, 100, 30);
        regOff.setBounds(50, 420, 220, 30);

        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(tf2);
        add(l6);
        add(p1);

        add(l7);
        add(p2);
        add(l4);
        add(tf4);
        add(l5);
        add(tf6);
        add(tf7);
        add(btn1);
        add(btn2);
        add(regOff);

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regOff) {
            this.setVisible(false);
        } else if (e.getSource() == btn1) {
            int x = 0;
            String s1 = tf1.getText();
            String s2 = tf2.getText();

            char[] s3 = p1.getPassword();
            char[] s4 = p2.getPassword();

            String s8 = new String(s3);
            String s9 = new String(s4);

            String s5 = tf4.getText();
            String s6 = tf6.getText();

            if (s8.equals(s9)) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(DatabaseHelper.url,
                            DatabaseHelper.user,
                            DatabaseHelper.password);
                    PreparedStatement ps = con.prepareStatement("insert into user(Name, Surname, Age, Login, Password) values(?,?,?,?,?)");
                    ps.setString(1, s1);
                    ps.setString(2, s2);
                    ps.setString(3, s6);
                    ps.setString(4, s5);
                    ps.setString(5, s9);
                    System.out.println("wsadzam");
                    ps.executeUpdate();
                    //ResultSet rs = ps.executeQuery();
                    x++;
                    if (x > 0) {
                        JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
                    }
                } catch (Exception ex) {
                    System.out.println(ex + "Error - Can't save data");
                }
            } else {
                JOptionPane.showMessageDialog(btn1, "Password Does Not Match");
            }
        } else {
            tf1.setText("");
            tf2.setText("");
            p1.setText("");
            p2.setText("");
            tf4.setText("");
        }

    }
}

