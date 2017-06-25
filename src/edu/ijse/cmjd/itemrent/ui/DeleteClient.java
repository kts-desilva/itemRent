package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.model.ClientM;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import org.jdesktop.swingx.JXDatePicker;
import java.text.*;
import java.util.*;
import java.net.MalformedURLException;
import java.io.*;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteClient extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JComboBox typeText;
    private JComboBox packageNameCombo;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JLabel id;
    private JLabel NIC;
    private JLabel name;
    private JLabel type;
    private JLabel tel;
    private JLabel address;
    private JLabel zip;
    private JLabel since;
    private JLabel packageLabel;
    private JLabel packageName;
    private JLabel fee;
    private JLabel types;
    private JLabel more;
    private JLabel charge;
    private JLabel result;
    private JComboBox idText;
    private JTextField NICText;
    private JTextField nameText;
    private JTextField telText;
    private JTextField addressText;
    private JTextField zipText;
    private JXDatePicker sinceText;
    private JTextField feeText;
    private JTextField typesText;
    private JTextField moreText;
    private JTextField chargeText;
    private JButton delButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel detailPanel;
    private JPanel fillPanel1;
    private JPanel fillPanel2;
    private JPanel titlePanel;
    private JPanel buttonCenterPanel;
    private JPanel detailCenterPanel;
    private JPanel fillPanel3;
    boolean r;
    String pre = " ";

    public DeleteClient() {
        initializeComponents();

        setFrameLayout();
        getContentPane().setBackground(new Color(255, 255, 255, 123));
        setUndecorated(true);
        setSize(800, 500);
        setLocationRelativeTo(null);
        pack();

    }

    private void initializeComponents() {

        /**
         * Initialize swing components Set the relevant listeners to the buttons
         */
        closeButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
                    serverConnection.releaseClient(idText.getSelectedItem().toString());
                    dispose();
                } catch (NotBoundException ex) {
                    Logger.getLogger(DeleteClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(DeleteClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(DeleteClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        header = new JLabel("Delete Client");
        header.setFont(new Font("Segoe UI Light", 0, 25));

        homeButton = new JLabel(new ImageIcon("./src/Images/homeBut.png"));
        homeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Home().setVisible(true);
                dispose();
            }
        });

        backButton = new JLabel(new ImageIcon("./src/Images/backBut.png"));
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Home().setVisible(true);
                dispose();
            }
        });


        sepLabel = new JLabel();
        sepLabel.setOpaque(true);
        sepLabel.setBackground(new Color(185, 205, 229));
        sepLabel.setPreferredSize(new Dimension(800, 10));

        sepLabel2 = new JLabel();
        sepLabel2.setOpaque(true);
        sepLabel2.setBackground(new Color(81, 131, 191));
        sepLabel2.setPreferredSize(new Dimension(800, 6));

        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(header);
        headerPanel.setBackground(new Color(255, 255, 255));

        closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closePanel.add(closeButton);
        closePanel.setBackground(new Color(255, 255, 255));

        homePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        homePanel.add(homeButton);
        homePanel.setBackground(new Color(255, 255, 255));

        backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.add(backButton);
        backPanel.setBackground(new Color(255, 255, 255));

        topPanel = new JPanel(new BorderLayout());
        topPanel.add("East", closePanel);
        topPanel.add("West", header);
        topPanel.setBackground(new Color(255, 255, 255));

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(255, 255, 255));
        setContent();

    }

    private void setContent() {

        /**
         * Initialize swing components Set the relevant listeners to the
         * JTextFields Validate the text of the JTextFields
         *
         */
        Font font = new Font("Segoe UI Light", Font.PLAIN, 18);

        id = new JLabel("ID", SwingConstants.LEFT);
        id.setFont(font);
        id.setPreferredSize(new Dimension(70, 20));

        NIC = new JLabel("NIC", SwingConstants.LEFT);
        NIC.setFont(font);
        NIC.setPreferredSize(new Dimension(65, 20));

        name = new JLabel("Name", SwingConstants.LEFT);
        name.setFont(font);
        name.setPreferredSize(new Dimension(75, 20));

        type = new JLabel("Type", SwingConstants.LEFT);
        type.setFont(font);
        type.setPreferredSize(new Dimension(75, 30));

        tel = new JLabel("Tel.no", SwingConstants.LEFT);
        tel.setFont(font);
        tel.setPreferredSize(new Dimension(75, 20));

        address = new JLabel("Address", SwingConstants.LEFT);
        address.setFont(font);
        address.setPreferredSize(new Dimension(75, 20));

        zip = new JLabel("Zip", SwingConstants.LEFT);
        zip.setFont(font);
        zip.setPreferredSize(new Dimension(75, 20));

        since = new JLabel("Since", SwingConstants.LEFT);
        since.setFont(font);
        since.setPreferredSize(new Dimension(75, 20));

        packageLabel = new JLabel("Package Info", SwingConstants.LEFT);
        packageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        packageLabel.setPreferredSize(new Dimension(90, 30));
        packageLabel.setVerticalTextPosition(JLabel.TOP);

        packageName = new JLabel("P.Name", SwingConstants.LEFT);
        packageName.setFont(font);
        packageName.setPreferredSize(new Dimension(75, 30));

        fee = new JLabel("Fee", SwingConstants.LEFT);
        fee.setFont(font);
        fee.setPreferredSize(new Dimension(75, 20));

        types = new JLabel("Types", SwingConstants.LEFT);
        types.setFont(font);
        types.setPreferredSize(new Dimension(75, 20));

        more = new JLabel("More", SwingConstants.LEFT);
        more.setFont(font);
        more.setPreferredSize(new Dimension(75, 20));

        charge = new JLabel("Charge", SwingConstants.LEFT);
        charge.setFont(font);
        charge.setPreferredSize(new Dimension(75, 20));

        idText = new JComboBox();
        idText.setPreferredSize(new Dimension(200, 25));
        idText.setToolTipText("Enter type here");

        idText.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton filterButton = new JButton(new ImageIcon("./src/Images/filterBut.png"));
                filterButton.setBackground(new Color(255, 255, 255));
                filterButton.setContentAreaFilled(false);
                filterButton.setFocusPainted(false);
                filterButton.setBorder(BorderFactory.createEmptyBorder());

                return filterButton;
            }
        });

        idText.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setPreferredSize(new Dimension(30, 25));
                setOpaque(true);
                setBackground(new Color(255, 255, 255, 123));
                setFont(new Font("Segoe UI", 0, 13));
                super.paint(g);
            }

            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);
                if (cellHasFocus || isSelected) {
                    renderer.setBorder(BorderFactory.createLineBorder(new Color(185, 205, 229)));
                } else {
                    renderer.setBackground(new Color(255, 255, 255));
                    renderer.setBorder(null);
                }

                renderer.setPreferredSize(new Dimension(25, 25));
                return renderer;
            }
        });

        idText.setOpaque(false);
        setID();

        final DeleteClient c = this;

        result = new JLabel();
        result.setPreferredSize(new Dimension(20, 25));

        idText.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                JComboBox cb = (JComboBox) evt.getSource();
                Object item = evt.getItem();

                if (evt.getStateChange() == ItemEvent.SELECTED) {

                    try {
                        ClientController controller = ServerConnector.serverConnection().getServerConnection();
                        ClientM model = controller.serachClient(idText.getSelectedItem().toString());
                        String p = idText.getSelectedItem().toString();
                        if (!pre.equals(p)) {
                            controller.releaseClient(pre);
                            pre = p;
                        }
                        if (controller.reserveClient(pre)) {

                            nameText.setEditable(true);
                            NICText.setEditable(true);
                            typeText.setEnabled(true);
                            telText.setEditable(true);
                            addressText.setEditable(true);
                            zipText.setEditable(true);
                            sinceText.setEditable(true);
                            feeText.setEditable(true);
                            typesText.setEditable(true);
                            moreText.setEditable(true);
                            chargeText.setEditable(true);
                            sinceText.setEnabled(true);
                            packageNameCombo.setEnabled(true);

                            nameText.setText(model.getName());
                            NICText.setText(model.getNIC());
                            typeText.setSelectedItem(model.getType());
                            telText.setText(model.getTel());
                            addressText.setText(model.getAddress());
                            zipText.setText(model.getZip());

                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE dd :MM : YYYY");
                            sinceText.setDate(dateFormat.parse(model.getSince()));

                            packageNameCombo.setSelectedItem(model.getPackageName());
                            feeText.setText(Double.toString(model.getFee()));
                            typesText.setText(model.getTypes());
                            moreText.setText(model.getMore());
                            chargeText.setText(Double.toString(model.getCharges()));
                            result.setIcon(new ImageIcon("./src/Images/trueBut.png"));

                        } else {
                            result.setIcon(new ImageIcon("./src/Images/falseBut.png"));

                            nameText.setText("");
                            NICText.setText("");
                            typeText.setSelectedItem("");
                            telText.setText("");
                            addressText.setText("");
                            zipText.setText("");
                            sinceText.setDate(null);
                            packageNameCombo.setSelectedItem(" ");
                            feeText.setText("");
                            typesText.setText("");
                            moreText.setText("");
                            chargeText.setText("");

                            nameText.setEditable(false);
                            NICText.setEditable(false);
                            typeText.setEnabled(false);
                            telText.setEditable(false);
                            addressText.setEditable(false);
                            zipText.setEditable(false);
                            sinceText.setEditable(false);
                            feeText.setEditable(false);
                            typesText.setEditable(false);
                            moreText.setEditable(false);
                            chargeText.setEditable(false);
                            sinceText.setEnabled(false);
                            packageNameCombo.setEnabled(false);
                            r = true;
                        }

                    } catch (NotBoundException | MalformedURLException | RemoteException | ClassNotFoundException | ParseException e) {
                    } catch (IOException e) {
                    }
                }


            }
        });

        result.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JPopupMenu popup = new JPopupMenu();
                JLabel messageLabel = new JLabel("<html>This customer is reserved<br><center>Please try again later !</center></html>", SwingConstants.CENTER);
                messageLabel.setOpaque(true);
                messageLabel.setFont(new Font("Segoe UI", 0, 13));
                messageLabel.setBackground(new Color(255, 247, 159));
                JPanel messagePanel = new JPanel(new FlowLayout());
                messagePanel.setBackground(new Color(255, 247, 159));

                JLabel icon = new JLabel(new ImageIcon("./src/Images/warningBut.png"));
                messagePanel.add(icon);
                messagePanel.add(messageLabel);

                popup.add(messagePanel);
                popup.setBorder(BorderFactory.createEmptyBorder());
                if (r) {
                    popup.show(result, result.getX() - 250, result.getY() + 20);
                }


            }
        });

        telText = new JTextField(15);
        telText.setPreferredSize(new Dimension(10, 25));
        telText.setToolTipText("Enter Tel.no here");


        NICText = new JTextField(10);
        NICText.setPreferredSize(new Dimension(10, 25));
        NICText.setToolTipText("Enter NIC here");

        nameText = new JTextField(20);
        nameText.setPreferredSize(new Dimension(10, 25));
        nameText.setToolTipText("Enter name here");

        typeText = new JComboBox();
        typeText.setPreferredSize(new Dimension(10, 25));
        typeText.setToolTipText("Enter type here");

        typeText.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton filterButton = new JButton(new ImageIcon("./src/Images/filterBut.png"));
                filterButton.setBackground(new Color(255, 255, 255));
                filterButton.setContentAreaFilled(false);
                filterButton.setFocusPainted(false);
                filterButton.setBorder(BorderFactory.createEmptyBorder());

                return filterButton;
            }
        });

        typeText.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setPreferredSize(new Dimension(30, 25));
                setOpaque(true);
                setBackground(new Color(255, 255, 255, 123));
                setFont(new Font("Segoe UI", 0, 13));
                super.paint(g);
            }

            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);
                if (cellHasFocus || isSelected) {
                    renderer.setBorder(BorderFactory.createLineBorder(new Color(185, 205, 229)));
                } else {
                    renderer.setBackground(new Color(255, 255, 255));
                    renderer.setBorder(null);
                }

                renderer.setPreferredSize(new Dimension(25, 25));
                return renderer;
            }
        });

        typeText.setOpaque(false);

        typeText.setFont(new Font("Segoe UI", 0, 18));
        typeText.addItem("Business");
        typeText.addItem("Individual");
        typeText.addItem("School");
        typeText.addItem("Organization");


        telText = new JTextField(15);
        telText.setPreferredSize(new Dimension(10, 25));
        telText.setToolTipText("Enter Tel.no here");

        addressText = new JTextField(15);
        addressText.setPreferredSize(new Dimension(10, 25));
        addressText.setToolTipText("Enter Tel.no here");

        zipText = new JTextField(15);
        zipText.setPreferredSize(new Dimension(10, 25));
        zipText.setToolTipText("Enter Tel.no here");

        sinceText = new JXDatePicker();
        sinceText.setDate(new Date());
        sinceText.setFont(new Font("Segoe UI", 0, 13));
        sinceText.setPreferredSize(new Dimension(10, 25));
        sinceText.setToolTipText("Enter Tel.no here");

        feeText = new JTextField(15);
        feeText.setPreferredSize(new Dimension(10, 25));
        feeText.setToolTipText("Enter Tel.no here");

        typesText = new JTextField(15);
        typesText.setPreferredSize(new Dimension(10, 25));
        typesText.setToolTipText("Enter Tel.no here");

        moreText = new JTextField(15);
        moreText.setPreferredSize(new Dimension(10, 25));
        moreText.setToolTipText("Enter Tel.no here");

        chargeText = new JTextField(15);
        chargeText.setPreferredSize(new Dimension(10, 25));
        chargeText.setToolTipText("Enter Tel.no here");

        packageNameCombo = new JComboBox();
        packageNameCombo.setPreferredSize(new Dimension(10, 25));
        packageNameCombo.setToolTipText("Select Package from here");

        packageNameCombo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton filterButton = new JButton(new ImageIcon("./src/Images/filterBut.png"));
                filterButton.setBackground(new Color(255, 255, 255));
                filterButton.setContentAreaFilled(false);
                filterButton.setFocusPainted(false);
                filterButton.setBorder(BorderFactory.createEmptyBorder());

                return filterButton;
            }
        });

        packageNameCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public void paint(Graphics g) {
                setPreferredSize(new Dimension(30, 25));
                setOpaque(true);
                setBackground(new Color(255, 255, 255, 123));
                setFont(new Font("Segoe UI", 0, 13));
                super.paint(g);
            }

            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);
                if (cellHasFocus || isSelected) {
                    renderer.setBorder(BorderFactory.createLineBorder(new Color(185, 205, 229)));
                } else {
                    renderer.setBackground(new Color(255, 255, 255));
                    renderer.setBorder(null);
                }

                renderer.setPreferredSize(new Dimension(25, 25));
                return renderer;
            }
        });

        packageNameCombo.setOpaque(false);

        packageNameCombo.setFont(new Font("Segoe UI", 0, 18));
        packageNameCombo.addItem("Starter");
        packageNameCombo.addItem("Midi User");
        packageNameCombo.addItem("Haevy User");
        packageNameCombo.addItem("Master");

        font = new Font("Segoe UI Light", Font.PLAIN, 16);

        delButton = new JButton("Delete");
        delButton.setFont(font);
        delButton.setContentAreaFilled(false);
        delButton.setOpaque(true);

        delButton.setBackground(new Color(185, 205, 229));

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE dd :MM : YYYY");
                try {
                    ClientController controller = ServerConnector.serverConnection().getServerConnection();
                    boolean r = controller.deleteClient(idText.getSelectedItem().toString());
                    if (r) {
                        new MessagePane(null, "Deleted Sucessfully !").setVisible(true);
                    } else {
                        new MessagePane(null, "Delete Faild !").setVisible(true);
                    }


                } catch (NotBoundException | MalformedURLException | RemoteException | ClassNotFoundException e) {
                } catch (IOException e) {
                }

            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(font);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setOpaque(true);
        cancelButton.setBackground(new Color(185, 205, 229));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
                    serverConnection.releaseClient(idText.getSelectedItem().toString());
                    dispose();
                } catch (NotBoundException ex) {
                    Logger.getLogger(DeleteClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(DeleteClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(DeleteClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        buttonPanel = new JPanel();
        detailPanel = new JPanel();

        fillPanel1 = new JPanel();
        fillPanel2 = new JPanel();

        detailPanel.setBackground(new Color(255, 255, 255));
        GridBagLayout gbl = new GridBagLayout();
        detailPanel.setLayout(gbl);

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(255, 255, 255));

        buttonCenterPanel = new JPanel();
        buttonCenterPanel.setBackground(new Color(255, 255, 255));

        detailCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        detailCenterPanel.setBackground(new Color(255, 255, 255));

        fillPanel3 = new JPanel();
        fillPanel3.setBackground(new Color(255, 255, 255));

        fillPanel1.setBackground(new Color(255, 255, 255));
        fillPanel2.setBackground(new Color(255, 255, 255));

        Component com[] = {id, idText, NIC, NICText, name, nameText, type, typeText, tel, telText};
        GridBagConstraints cons = new GridBagConstraints();

        cons.anchor = GridBagConstraints.WEST;
        cons.fill = GridBagConstraints.BOTH;
        cons.insets = new Insets(10, 10, 10, 10);


        cons.weightx = 1.0;
        cons.weighty = 3.0;

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(new Color(255, 255, 255));
        panel.add(idText);
        panel.add(result);

        cons.gridx = 0;
        cons.gridy = 0;
        detailPanel.add(id, cons);
        cons.gridx = 10;
        cons.gridy = 0;
        detailPanel.add(panel, cons);

        cons.gridx = 20;
        cons.gridy = 0;
        detailPanel.add(NIC, cons);
        cons.gridx = 30;
        cons.gridy = 0;
        detailPanel.add(NICText, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        detailPanel.add(name, cons);
        cons.gridx = 10;
        cons.gridy = 10;
        detailPanel.add(nameText, cons);

        cons.gridx = 20;
        cons.gridy = 10;
        detailPanel.add(type, cons);
        cons.gridx = 30;
        cons.gridy = 10;
        detailPanel.add(typeText, cons);

        cons.gridx = 0;
        cons.gridy = 20;
        detailPanel.add(tel, cons);
        cons.gridx = 10;
        cons.gridy = 20;
        detailPanel.add(telText, cons);

        cons.gridx = 20;
        cons.gridy = 20;
        detailPanel.add(address, cons);
        cons.gridx = 30;
        cons.gridy = 20;
        detailPanel.add(addressText, cons);

        cons.gridx = 0;
        cons.gridy = 30;
        detailPanel.add(zip, cons);
        cons.gridx = 10;
        cons.gridy = 30;
        detailPanel.add(zipText, cons);

        cons.gridx = 20;
        cons.gridy = 30;
        detailPanel.add(since, cons);
        cons.gridx = 30;
        cons.gridy = 30;
        detailPanel.add(sinceText, cons);

        cons.gridx = 0;
        cons.gridy = 40;
        cons.gridwidth = 15;
        cons.gridheight = 20;
        detailPanel.add(packageLabel, cons);
        cons.gridx = 15;
        cons.gridy = 40;
        cons.gridwidth = 20;
        cons.gridheight = 20;
        detailPanel.add(sepLabel, cons);

        cons.gridx = 0;
        cons.gridy = 70;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        detailPanel.add(packageName, cons);
        cons.gridx = 10;
        cons.gridy = 70;
        cons.gridwidth = 1;
        detailPanel.add(packageNameCombo, cons);

        cons.gridx = 20;
        cons.gridy = 70;
        cons.gridwidth = 1;
        detailPanel.add(fee, cons);
        cons.gridx = 30;
        cons.gridy = 70;
        detailPanel.add(feeText, cons);

        cons.gridx = 0;
        cons.gridy = 80;
        detailPanel.add(types, cons);
        cons.gridx = 10;
        cons.gridy = 80;
        detailPanel.add(typesText, cons);

        cons.gridx = 0;
        cons.gridy = 90;
        detailPanel.add(more, cons);
        cons.gridx = 10;
        cons.gridy = 90;
        detailPanel.add(moreText, cons);

        cons.gridx = 20;
        cons.gridy = 90;
        detailPanel.add(charge, cons);
        cons.gridx = 30;
        cons.gridy = 90;
        detailPanel.add(chargeText, cons);


        detailCenterPanel.add(detailPanel);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.add(delButton);
        buttonPanel.add(cancelButton);

        buttonCenterPanel.add(fillPanel3);
        buttonCenterPanel.add("Center", buttonPanel);

        contentPanel.add("Center", detailCenterPanel);
        contentPanel.add("South", buttonCenterPanel);

    }

    private void setFrameLayout() {

        /**
         * Set the frame Add the relevant components in required places
         *
         */
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.anchor = GridBagConstraints.WEST;
        cons.fill = GridBagConstraints.BOTH;
        cons.insets = new Insets(10, 10, 10, 10);

        cons.weightx = 1.0;
        cons.weighty = 1.0;

        cons.gridx = 0;
        cons.gridy = 0;
        add(topPanel, cons);

        cons.gridx = 0;
        cons.gridy = 3;
        add(sepLabel, cons);

        cons.gridx = 0;
        cons.gridy = 5;
        add(sepLabel2, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        add(contentPanel, cons);

    }

    private void setID() {
        
        /**
         * Set the new item id ;calculated by the IDCreator class
         *
         */
        
        try {
            ClientController controller = ServerConnector.serverConnection().getServerConnection();
            ArrayList<ClientM> clients = controller.getAllClients();
            System.out.println(clients);
            if (clients != null) {
                for (ClientM c : clients) {
                    idText.addItem(c.getId());

                }
            }

        } catch (NotBoundException | MalformedURLException | RemoteException | ClassNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public static void main(String args[]) {
        new DeleteClient().setVisible(true);
    }
}
