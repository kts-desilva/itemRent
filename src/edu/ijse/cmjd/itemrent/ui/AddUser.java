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
import javax.swing.ImageIcon;
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
public class AddUser extends JFrame {

    private JPanel titilePanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JTextField useNameText;
    private JPasswordField passwordText;
    private JPasswordField reEnterPasswordText;
    private JButton loginButton;

    public AddUser() {
        initializeComponents();

        setFrameLayout();
        setSize(450, 400);
    }

    private void initializeComponents() {
        
        /**
         *Initialize swing components
         * Set the relevant listeners to the buttons
         */
        
        titilePanel = new JPanel();
        titilePanel.setBackground(new Color(113, 153, 202));

        titleLabel = new JLabel("Create a account");
        titleLabel.setFont(new Font("Segoe UI Light", 0, 30));
        titleLabel.setForeground(new Color(255, 255, 255));
        titilePanel.add(titleLabel);

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        loginButton = new JButton("Create");
        loginButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setPreferredSize(new Dimension(376, 30));
        loginButton.setEnabled(false);

        final JFrame frame = this;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserController userController = ServerConnector.serverConnection().getUserController();
                    String passW = new String(reEnterPasswordText.getPassword());
                    boolean addUser = userController.addUser(useNameText.getText(), passW);
                    if (addUser) {
                        new MessagePane(frame, "Account created !").setVisible(true);
                    }

                } catch (RemoteException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                loginButton.requestFocus(true);
                e.getWindow().removeWindowListener(this);
            }
        });

        useNameText = new JTextField(25);
        useNameText.setText("Enteryour Username");
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
        useNameText.setPreferredSize(new Dimension(250, 50));
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

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 255, 255));

        JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredPanel.setBackground(new Color(255, 255, 255));

        passwordText = new JPasswordField(25);
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
                char c = 0;
                char[] cA = {};
                if (Arrays.equals(passwordText.getPassword(), cA)) {
                    passwordText.setText("Enter your Password");
                    passwordText.setEchoChar(c);
                } else {
                    passwordText.setEchoChar('•');
                }


            }
        });

        char firstChar = 0;
        passwordText.setText("Enter your Password");
        passwordText.setEchoChar(firstChar);
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

        passwordText.setPreferredSize(new Dimension(300, 50));


        reEnterPasswordText = new JPasswordField(25);
        reEnterPasswordText.setOpaque(true);
        reEnterPasswordText.setFont(new Font("Segoe UI Light", 0, 16));
        reEnterPasswordText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                reEnterPasswordText.setText("");
                reEnterPasswordText.setEchoChar('•');

            }

            @Override
            public void focusLost(FocusEvent e) {
                char c = 0;
                reEnterPasswordText.setEchoChar(c);
                char[] cA = {};
                if (Arrays.equals(reEnterPasswordText.getPassword(), cA)) {
                    reEnterPasswordText.setText("Re enter your Password");
                } else {
                    reEnterPasswordText.setEchoChar('•');
                }


            }
        });

        char firstReEnterChar = 0;
        reEnterPasswordText.setText("Re enter your Password");
        reEnterPasswordText.setEchoChar(firstReEnterChar);
        reEnterPasswordText.setBorder(new AbstractBorder() {
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

        reEnterPasswordText.setPreferredSize(new Dimension(336, 50));

        final JLabel userConFirmLabel = new JLabel();
        userConFirmLabel.setBackground(new Color(255, 255, 255));

        useNameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String password = ServerConnector.serverConnection().getUserController().getPassword(userConFirmLabel.getText());
                    if (password == null) {
                        userConFirmLabel.setIcon(new ImageIcon("./src/Images/trueBut.png"));
                    } else {
                        userConFirmLabel.setIcon(new ImageIcon("./src/Images/falseBut.png"));
                    }
                } catch (NotBoundException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JPanel userPanel = new JPanel(new FlowLayout());

        userPanel.setBackground(new Color(255, 255, 255));
        userPanel.add(useNameText);

        userPanel.add(userConFirmLabel);
        final JLabel passwordConFirmLabel = new JLabel();

        passwordConFirmLabel.setBackground(new Color(255, 255, 255));
        reEnterPasswordText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char[] password = passwordText.getPassword();
                char[] reEnter = reEnterPasswordText.getPassword();
                if (Arrays.equals(password, reEnter)) {
                    passwordConFirmLabel.setIcon(new ImageIcon("./src/Images/trueBut.png"));
                    loginButton.setBackground(new Color(113, 153, 202));
                    loginButton.setForeground(new Color(255, 255, 255));
                    loginButton.setEnabled(true);
                } else {
                    passwordConFirmLabel.setIcon(new ImageIcon("./src/Images/falseBut.png"));
                }
            }
        });


        JPanel passwordPanel = new JPanel(new FlowLayout());

        passwordPanel.setBackground(new Color(255, 255, 255));
        passwordPanel.add(reEnterPasswordText);

        passwordPanel.add(passwordConFirmLabel);
        JPanel passwordEnterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

        passwordEnterPanel.setBackground(new Color(255, 255, 255));
        passwordEnterPanel.add(passwordText);

        centerPanel.add(userPanel);

        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordEnterPanel);

        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordPanel);

        centeredPanel.add(centerPanel);

        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(centeredPanel);

        contentPanel.add(Box.createVerticalStrut(5));

        buttonPanel = new JPanel();

        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new FlowLayout());

        JPanel loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButtonPanel.setBackground(new Color(255, 255, 255));
        loginButtonPanel.add(loginButton);
        contentPanel.add(loginButtonPanel);


    }

    private void setFrameLayout() {
        
        /**
         * Set the frame 
         * Add the relevant components in required places
         * 
         */
        
        add("North", titilePanel);
        add("Center", contentPanel);
        add("South", buttonPanel);
    }

    public static void main(String[] args) {
        new AddUser().setVisible(true);
    }
}
