/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.controller.UserController;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author User
 */
public class Login extends JFrame {

    private JPanel titilePanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JTextField useNameText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JLabel newLabel;
    private JButton newButton;

    public Login() {
        
        initializeComponents();

        setUndecorated(true);
        setSize(450, 350);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {

        /**
         * Initialize swing components Set the relevant listeners to the buttons
         */
        titilePanel = new JPanel();
        titilePanel.setBackground(new Color(113, 153, 202));

        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Segoe UI Light", 0, 30));
        titleLabel.setForeground(new Color(255, 255, 255));
        titilePanel.add(titleLabel);

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setPreferredSize(new Dimension(376, 30));
        loginButton.setEnabled(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loginButton.requestFocus(true);
                e.getWindow().removeWindowListener(this);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home().setVisible(true);
                dispose();
            }
        });

        useNameText = new JTextField(30);
        useNameText.setText("Username");
        useNameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                useNameText.setText("");

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (useNameText.getText().equals("")) {
                    useNameText.setText("Username");
                }
            }
        });
        useNameText.setFont(new Font("Segoe UI Light", 0, 16));
        useNameText.setPreferredSize(new Dimension(400, 50));
        useNameText.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(185, 205, 229));
                g2.setStroke(new BasicStroke(10));
                g2.draw(new Line2D.Float(x, y + height - 3, width - 3, height - 3));

            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(10, 10, 10, 10);
            }

            @Override
            public Insets getBorderInsets(Component c, Insets insets) {
                insets.left = insets.right = 10;
                insets.top = insets.bottom = 10;
                return insets;
            }
        });
        useNameText.setOpaque(true);
        useNameText.setForeground(new Color(WIDTH));
        useNameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserController userController = ServerConnector.serverConnection().getUserController();
                    String password = userController.getPassword(useNameText.getText());
                    if (password == null) {
                        new MessagePane(null, "Usch user doesn't exist !").setVisible(true);
                    } else {
                        passwordText.requestFocus(true);
                    }

                } catch (NotBoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 255, 255));

        JPanel doubleCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        doubleCenterPanel.setBackground(new Color(255, 255, 255));

        passwordText = new JPasswordField(10);
        passwordText.setOpaque(true);
        passwordText.setFont(new Font("Segoe UI Light", 0, 16));
        passwordText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                passwordText.setText("");
                passwordText.setEchoChar('•');

            }

            @Override
            public void focusLost(FocusEvent e) {
                char firstChar = 0;
                char[] setChat = {};
                if (Arrays.equals(passwordText.getPassword(), setChat)) {
                    passwordText.setText("Enter your Password");
                    passwordText.setEchoChar(firstChar);
                } else {
                    passwordText.setEchoChar('•');
                }

            }
        });

        char firstPasswordChar = 0;
        passwordText.setText("Password");
        passwordText.setEchoChar(firstPasswordChar);
        passwordText.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(185, 205, 229));
                g2.setStroke(new BasicStroke(10));
                g2.draw(new Line2D.Float(x, y + height - 3, width - 3, height - 3));

            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(10, 10, 10, 10);
            }

            @Override
            public Insets getBorderInsets(Component c, Insets insets) {
                insets.left = insets.right = 10;
                insets.top = insets.bottom = 10;
                return insets;
            }
        });

        passwordText.setPreferredSize(new Dimension(336, 50));

        passwordText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserController userController = ServerConnector.serverConnection().getUserController();
                    String password = userController.getPassword(useNameText.getText());
                    char[] passwordToCharArray = password.toCharArray();
                    char[] typedPassword = passwordText.getPassword();
                    if (!Arrays.equals(typedPassword, passwordToCharArray)) {
                        new MessagePane(null, "Incorrect Password!").setVisible(true);
                    } else {

                        loginButton.setBackground(new Color(113, 153, 202));
                        loginButton.setForeground(new Color(255, 255, 255));
                        loginButton.setEnabled(true);
                    }

                } catch (NotBoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        centerPanel.add(useNameText);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordText);

        doubleCenterPanel.add(centerPanel);

        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(doubleCenterPanel);
        contentPanel.add(Box.createVerticalStrut(5));

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new FlowLayout());

        newLabel = new JLabel("New ?");
        newLabel.setFont(new Font("Segoe UI Light", 0, 15));
        newLabel.setOpaque(true);
        newLabel.setBackground(new Color(255, 255, 255));

        newButton = new JButton("Create a account");
        newButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        newButton.setContentAreaFilled(false);
        newButton.setForeground(new Color(79, 129, 189));
        
        setFrameLayout();

    }

    private void setFrameLayout() {

        /**
         * Set the frame Add the relevant components in required places
         *
         */
        
        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        newPanel.setBackground(new Color(255, 255, 255));
        newPanel.add(newLabel);
        newPanel.add(newButton);

        newPanel.setBackground(new Color(255, 255, 255));


        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginPanel.setBackground(new Color(255, 255, 255));

        loginPanel.add(loginButton);
        contentPanel.add(loginPanel);
        buttonPanel.add(newPanel);

        add("North", titilePanel);
        add("Center", contentPanel);
        add("South", buttonPanel);
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
