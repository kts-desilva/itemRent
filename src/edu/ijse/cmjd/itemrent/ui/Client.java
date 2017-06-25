package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.model.OrderM;
import edu.ijse.cmjd.itemrent.model.ClientM;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.observer.impl.ClientObserverImpl;
import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.util.*;
import java.net.MalformedURLException;
import java.io.*;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel addButton;
    private JLabel delButton;
    private JLabel editButton;
    private JLabel searchButton;
    private JLabel filterButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JComboBox searchText;
    private JComboBox criteriaText;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel naviPanel;
    private JPanel selectionPanel;
    private JTable clientTable;
    private JScrollPane scrollPane;
    private String[] cid;
    private String[] cName;
    private String[] address;
    private String[] pack;

    public Client() {
        initializeComponents();
        try {
            ClientObserverImpl observer = new ClientObserverImpl(this);
            ClientController con = ServerConnector.serverConnection().getServerConnection();
            con.addClientObserver(observer);

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }



        setFrameLayout();
        getContentPane().setBackground(new Color(255, 255, 255, 123));
        setUndecorated(true);
        setSize(800, 600);
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
                dispose();
            }
        });

        header = new JLabel("Client");
        header.setFont(new Font("Segoe UI Light", 0, 35));

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

        addButton = new JLabel("Add", new ImageIcon("./src/Images/addBut.png"), 0);
        addButton.setFont(new Font("Segoe UI Light", 0, 20));
        addButton.setHorizontalTextPosition(JLabel.CENTER);
        addButton.setVerticalTextPosition(JLabel.BOTTOM);
        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new AddClient().setVisible(true);
            }
        });

        delButton = new JLabel("Delete", new ImageIcon("./src/Images/delBut.png"), 0);
        delButton.setFont(new Font("Segoe UI Light", 0, 20));
        delButton.setHorizontalTextPosition(JLabel.CENTER);
        delButton.setVerticalTextPosition(JLabel.BOTTOM);
        delButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new DeleteClient().setVisible(true);
            }
        });

        editButton = new JLabel("Edit", new ImageIcon("./src/Images/editBut.png"), 0);
        editButton.setFont(new Font("Segoe UI Light", 0, 20));
        editButton.setHorizontalTextPosition(JLabel.CENTER);
        editButton.setVerticalTextPosition(JLabel.BOTTOM);
        editButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new EditClient().setVisible(true);
            }
        });

        searchButton = new JLabel(new ImageIcon("./src/Images/searchBut2.png"));
        searchButton.setPreferredSize(new Dimension(55, 30));
        searchButton.setOpaque(true);
        searchButton.setBackground(new Color(81, 131, 191));
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    setTableUsingCriteria(criteriaText.getSelectedItem().toString(),
                            searchText.getEditor().getItem().toString());
                } catch (NotBoundException ex) {
                    Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        filterButton = new JLabel(new ImageIcon("./src/Images/filterBut.png"));
        filterButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
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

        searchText = new JComboBox();
        searchText.setPreferredSize(new Dimension(550, 25));
        searchText.setEditable(true);
        searchText.setBackground(new Color(255, 255, 255));
        searchText.setUI(new BasicComboBoxUI() {
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

        searchText.setRenderer(new DefaultListCellRenderer() {
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

        searchText.setOpaque(false);

        searchText.setFont(new Font("Segoe UI", 0, 18));

        try {
            setData();
            searchText.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {

                    if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {

                        String cr = criteriaText.getSelectedItem().toString();
                        String text = searchText.getEditor().getItem().toString();
                        System.out.println(cid[0]);
                        switch (cr) {
                            case "CID":
                                for (int i = 0; i < cid.length; i++) {
                                    if (text.length() <= cid[i].length()) {
                                        if (text.equals(cid[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(cid[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (cid[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(cid[i]);
                                            }
                                        }

                                    } else {
                                    }
                                    searchText.showPopup();
                                }
                                break;

                            case "Cl.Name":
                                for (int i = 0; i < cName.length; i++) {
                                    if (text.length() <= cName[i].length()) {
                                        if (text.equals(cName[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(cName[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (cName[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(cName[i]);
                                            }
                                        }

                                    } else {
                                    }
                                    searchText.showPopup();
                                }
                                break;
                            case "Address":
                                for (int i = 0; i < address.length; i++) {
                                    if (text.length() <= address[i].length()) {
                                        if (text.equals(address[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(address[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (address[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(address[i]);
                                            }
                                        }

                                    } else {
                                        //searchText.addItem("No such Or.Date found");
                                    }
                                    searchText.showPopup();
                                }
                                break;
                            case "Package":
                                for (int i = 0; i < pack.length; i++) {
                                    if (text.length() <= pack[i].length()) {
                                        if (text.equals(pack[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(pack[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (pack[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(pack[i]);
                                            }
                                        }

                                    } else {
                                        //searchText.addItem("No such Or.Date found");
                                    }
                                    searchText.showPopup();
                                }
                                break;
                            default:
                                new MessagePane(null, "No such Client found !").setVisible(true);
                        }
                    } else {
                        searchText.removeAllItems();
                    }
                }
            });


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }


        criteriaText = new JComboBox();
        criteriaText.setPreferredSize(new Dimension(100, 25));
        criteriaText.setEditable(false);
        criteriaText.setBackground(new Color(255, 255, 255));
        criteriaText.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton dropButton = new JButton(new ImageIcon("./src/Images/dropBut.png"));
                dropButton.setVerticalAlignment(JButton.BOTTOM);
                dropButton.setBackground(new Color(255, 255, 255));
                dropButton.setContentAreaFilled(false);
                dropButton.setFocusPainted(false);
                dropButton.setBorder(BorderFactory.createEmptyBorder());

                return dropButton;
            }
        });

        criteriaText.setRenderer(new DefaultListCellRenderer() {
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

        criteriaText.setOpaque(false);
        criteriaText.setFont(new Font("Segoe UI", 0, 18));
        criteriaText.addItem("All");
        criteriaText.addItem("CID");
        criteriaText.addItem("Cl.Name");
        criteriaText.addItem("Address");
        criteriaText.addItem("Package");


        criteriaText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                criteriaText.showPopup();
            }
        });
        criteriaText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (criteriaText.getSelectedItem().toString().equals("All")) {
                    searchText.getEditor().setItem(" ");
                }
            }
        });


        naviPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JLabel text = new JLabel("Search : ");
        text.setFont(new Font("Segoe UI Light", 0, 24));

        naviPanel.add(text);
        naviPanel.add(criteriaText);
        naviPanel.add(searchText);
        naviPanel.add(searchButton);
        naviPanel.setBackground(new Color(255, 255, 255));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(255, 255, 255));
        setContent();

    }

    private void setContent() {
        /**
         * Set the table with all client details
         */

        String columnNames[] = {"ID", "Name", "NIC", "Type", "Tel", "Address", "Zip", "Since", "Package", "Fees", "Types", "More", "Charge", "Rating"};
        Object[][] rowData = {{"001", "957151205V", "Lee Junho", "Athletic", "+9415353535", "S.Korea", "424", "2015/02/03", "Gold", "$45", "Bla", "Bla", "Bla", new ImageIcon("./src/Images/star3.png")}, {"002", "957151205V", "Ok Tacyeon", "Athletic", "+9415353535", "S.Korea", "424", "2015/02/03", "Gold", "$45", "Bla", "Bla", "Bla", new ImageIcon("./src/Images/star3.png")}, {"003", "957151205V", "Lee Minho", "Athletic", "+9415353535", "S.Korea", "424", "2015/02/03", "Gold", "$45", "Bla", "Bla", "Bla", new ImageIcon("./src/Images/star3.png")},};
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        clientTable = new JTable(model) {
            private static final long serialVersionUID = 1L;
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        clientTable.setAutoCreateRowSorter(true);
        clientTable.setOpaque(true);
        clientTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        clientTable.getTableHeader().setForeground(new Color(255, 255, 255));
        clientTable.getTableHeader().setBackground(new Color(113, 153, 202));
        clientTable.setRowHeight(30);
        clientTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 13; i++) {
            clientTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        scrollPane = new JScrollPane(clientTable);
        scrollPane.setPreferredSize(new Dimension(453, 250));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));
        clientTable.setBackground(new Color(255, 255, 255));
        
        
        /**
         * Set the scroll bars with flat design
         */

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

                g.translate(trackBounds.x, trackBounds.y);
                g.setColor(new Color(211, 211, 211));
                g.fillRect(0, 0, trackBounds.width, trackBounds.height);
                g.translate(-trackBounds.x, -trackBounds.y);

            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

                g.translate(thumbBounds.x, thumbBounds.y);
                g.setColor(new Color(169, 169, 169));
                g.fillRect(0, 0, thumbBounds.width - 12, thumbBounds.height - 12);

            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });

        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

                g.translate(trackBounds.x, trackBounds.y);
                g.setColor(new Color(211, 211, 211));
                g.fillRect(0, 0, trackBounds.width, trackBounds.height);
                g.translate(-trackBounds.x, -trackBounds.y);

            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

                g.translate(thumbBounds.x, thumbBounds.y);
                g.setColor(new Color(169, 169, 169));
                g.fillRect(0, 0, thumbBounds.width - 12, thumbBounds.height - 12);

            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });


        setTable();

        selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        selectionPanel.setBackground(new Color(255, 255, 255));
        selectionPanel.add(addButton);
        selectionPanel.add(delButton);
        selectionPanel.add(editButton);

        contentPanel.add(scrollPane);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(new JSeparator());
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(selectionPanel);

    }

    private void setFrameLayout() {
        
        /**
         * Set the frame 
         * Add the relevant components in required places
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
        cons.gridy = 4;
        add(naviPanel, cons);

        cons.gridx = 0;
        cons.gridy = 5;
        add(sepLabel2, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        add(contentPanel, cons);

    }

    private void setTable() {
        
        /**
         * Set the table with all client details
         * 
         */
        
        try {
            ClientController controller = ServerConnector.serverConnection().getServerConnection();
            ArrayList<ClientM> clients = controller.getAllClients();
            DefaultTableModel model = (DefaultTableModel) clientTable.getModel();
            model.setRowCount(0);
            if (clients != null) {
                for (ClientM c : clients) {
                    int returns = 0;
                    int orders = 0;
                    ArrayList<OrderM> ordersFilteredClient =
                            ServerConnector.serverConnection()
                            .getOrderController().getOrdersFilteredClient(c.getId());
                    orders = ordersFilteredClient.size();
                    for (OrderM orderM : ordersFilteredClient) {
                        if (orderM.getState().equals("Payed")) {
                            returns++;
                        }
                    }
                    double rating = ((double) returns) / orders;

                    ImageIcon icon = null;

                    if (rating > 0.6) {
                        icon = new ImageIcon("./src/Images/star3.png");

                    } else if (rating > 0.3) {
                        icon = new ImageIcon("./src/Images/star2.png");
                    } else {
                        icon = new ImageIcon("./src/Images/star1.png");
                    }

                    Object rowData[] = {c.getId(), c.getName(), c.getNIC(), c.getType(), c.getTel(), c.getAddress(),
                        c.getZip(), c.getSince(), c.getPackageName(), c.getFee(), c.getTypes(), c.getMore(), c.getCharges(), icon
                    };
                    model.addRow(rowData);
                }
            }


        } catch (NotBoundException | MalformedURLException | RemoteException | ClassNotFoundException e) {
        } catch (IOException e) {
        }

    }

    public void setMessage(String msg) {
        
        /**
         * Informs the user when a new client is added
         * 
         */

        new MessagePane(this, msg).setVisible(true);
        setTable();
    }

    private void setData() throws NotBoundException, MalformedURLException, RemoteException, ClassNotFoundException, IOException {
        ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
        ArrayList<ClientM> allClients = serverConnection.getAllClients();

        cid = new String[allClients.size()];
        cName = new String[allClients.size()];
        address = new String[allClients.size()];
        pack = new String[allClients.size()];


        int index = 0;
        for (ClientM clientM : allClients) {
            cid[index] = clientM.getId();
            cName[index] = clientM.getName();
            address[index] = clientM.getAddress();
            pack[index] = clientM.getPackageName();
            index++;
        }
    }

    private void setTableUsingCriteria(String criteria, String element) throws NotBoundException,
            MalformedURLException, RemoteException, ClassNotFoundException, IOException {

        /**
         * Search using different criteria of client information
         * Set the client table according to the given criteria
         * 
         */
        
        ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
        DefaultTableModel dtm = (DefaultTableModel) clientTable.getModel();
        dtm.setRowCount(0);
        ArrayList<OrderM> ordersFilteredClient = null;
        int returns, orders;
        double rating;
        ImageIcon icon = null;
        switch (criteria) {
            case "CID":
                ClientM c = serverConnection.serachClient(element);

                returns = 0;
                orders = 0;
                ordersFilteredClient = ServerConnector.serverConnection()
                        .getOrderController().getOrdersFilteredClient(c.getId());
                orders = ordersFilteredClient.size();
                for (OrderM orderM : ordersFilteredClient) {
                    if (orderM.getState().equals("Payed")) {
                        returns++;
                    }
                }
                rating = ((double) returns) / orders;

                if (rating > 0.6) {
                    icon = new ImageIcon("./src/Images/star3.png");

                } else if (rating > 0.3) {
                    icon = new ImageIcon("./src/Images/star2.png");
                } else {
                    icon = new ImageIcon("./src/Images/star1.png");
                }
                Object rowData[] = {c.getId(), c.getName(), c.getNIC(), c.getType(), c.getTel(), c.getAddress(),
                    c.getZip(), c.getSince(), c.getPackageName(), c.getFee(), c.getTypes(), c.getMore(), c.getCharges(), icon
                };

                dtm.addRow(rowData);
                break;
            case "Cl.Name":
                ArrayList<ClientM> clientsFilteredName = serverConnection.getClientsFilteredName(element);
                for (ClientM c1 : clientsFilteredName) {
                    returns = 0;
                    orders = 0;
                    ordersFilteredClient =
                            ServerConnector.serverConnection()
                            .getOrderController().getOrdersFilteredClient(c1.getId());
                    orders = ordersFilteredClient.size();
                    for (OrderM orderM : ordersFilteredClient) {
                        if (orderM.getState().equals("Payed")) {
                            returns++;
                        }
                    }
                    rating = ((double) returns) / orders;

                    if (rating > 0.6) {
                        icon = new ImageIcon("./src/Images/star3.png");

                    } else if (rating > 0.3) {
                        icon = new ImageIcon("./src/Images/star2.png");
                    } else {
                        icon = new ImageIcon("./src/Images/star1.png");
                    }
                    Object rowData1[] = {c1.getId(), c1.getName(), c1.getNIC(), c1.getType(), c1.getTel(), c1.getAddress(),
                        c1.getZip(), c1.getSince(), c1.getPackageName(), c1.getFee(), c1.getTypes(), c1.getMore(), c1.getCharges(), icon
                    };

                    dtm.addRow(rowData1);
                }
                break;

            case "Address":
                ArrayList<ClientM> clientsFilteredAddress = serverConnection.getClientsFilteredAddress(element);
                for (ClientM c1 : clientsFilteredAddress) {
                    returns = 0;
                    orders = 0;
                    ordersFilteredClient =
                            ServerConnector.serverConnection()
                            .getOrderController().getOrdersFilteredClient(c1.getId());
                    orders = ordersFilteredClient.size();
                    for (OrderM orderM : ordersFilteredClient) {
                        if (orderM.getState().equals("Payed")) {
                            returns++;
                        }
                    }
                    rating = ((double) returns) / orders;

                    if (rating > 0.6) {
                        icon = new ImageIcon("./src/Images/star3.png");

                    } else if (rating > 0.3) {
                        icon = new ImageIcon("./src/Images/star2.png");
                    } else {
                        icon = new ImageIcon("./src/Images/star1.png");
                    }
                    Object rowData1[] = {c1.getId(), c1.getName(), c1.getNIC(), c1.getType(), c1.getTel(), c1.getAddress(),
                        c1.getZip(), c1.getSince(), c1.getPackageName(), c1.getFee(), c1.getTypes(), c1.getMore(), c1.getCharges(), icon
                    };

                    dtm.addRow(rowData1);
                }
                break;
            case "Package":
                ArrayList<ClientM> clientsFilteredPackage = serverConnection.getClientsFilteredPackage(element);
                for (ClientM c1 : clientsFilteredPackage) {
                    returns = 0;
                    orders = 0;
                    ordersFilteredClient =
                            ServerConnector.serverConnection()
                            .getOrderController().getOrdersFilteredClient(c1.getId());
                    orders = ordersFilteredClient.size();
                    for (OrderM orderM : ordersFilteredClient) {
                        if (orderM.getState().equals("Payed")) {
                            returns++;
                        }
                    }
                    rating = ((double) returns) / orders;

                    if (rating > 0.6) {
                        icon = new ImageIcon("./src/Images/star3.png");

                    } else if (rating > 0.3) {
                        icon = new ImageIcon("./src/Images/star2.png");
                    } else {
                        icon = new ImageIcon("./src/Images/star1.png");
                    }
                    Object rowData1[] = {c1.getId(), c1.getName(), c1.getNIC(), c1.getType(), c1.getTel(), c1.getAddress(),
                        c1.getZip(), c1.getSince(), c1.getPackageName(), c1.getFee(), c1.getTypes(), c1.getMore(), c1.getCharges(), icon
                    };

                    dtm.addRow(rowData1);
                }
                break;
            case "All":
                setTable();
                break;
            default:

        }

    }

    public static void main(String args[]) {
        new Client().setVisible(true);
    }
}
