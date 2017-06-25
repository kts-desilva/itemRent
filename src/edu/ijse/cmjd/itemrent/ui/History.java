package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.controller.HistoryController;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.controller.OrderController;
import edu.ijse.cmjd.itemrent.controller.OrderDetailController;
import edu.ijse.cmjd.itemrent.model.ClientM;
import edu.ijse.cmjd.itemrent.model.HistoryM;
import edu.ijse.cmjd.itemrent.model.ItemM;
import edu.ijse.cmjd.itemrent.model.OrderDetailM;
import edu.ijse.cmjd.itemrent.model.OrderM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class History extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel clientCount;
    private JLabel stockCount;
    private JLabel rentedCount;
    private JLabel searchButton;
    private JLabel filterButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JTextField clientCountText;
    private JTextField stockCountText;
    private JTextField rentedCountText;
    private JComboBox searchText;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel naviPanel;
    private JPanel selectionPanel;
    private JTable HistoryTable;
    private JScrollPane scrollPane;
    private String userList[];

    public History() {
        initializeComponents();

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

        header = new JLabel("History");
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

        clientCount = new JLabel("Client count", new ImageIcon("./src/Images/clientBut.png"), 0);
        clientCount.setFont(new Font("Segoe UI Light", 0, 20));

        stockCount = new JLabel("Stock Count", new ImageIcon("./src/Images/stockBut.png"), 0);
        stockCount.setFont(new Font("Segoe UI Light", 0, 20));

        rentedCount = new JLabel("Rented Count", new ImageIcon("./src/Images/returnBut.png"), 0);
        rentedCount.setFont(new Font("Segoe UI Light", 0, 20));

        clientCountText = new JTextField(5);
        clientCountText.setPreferredSize(new Dimension(10, 25));
        clientCountText.setToolTipText("Enter user here");
        clientCountText.setFont(new Font("Segoe UI", 0, 15));
        clientCountText.setHorizontalAlignment(SwingConstants.CENTER);
        clientCountText.setEditable(false);
        setClientCount();

        stockCountText = new JTextField(5);
        stockCountText.setPreferredSize(new Dimension(10, 25));
        stockCountText.setFont(new Font("Segoe UI", 0, 15));
        stockCountText.setHorizontalAlignment(SwingConstants.CENTER);
        stockCountText.setToolTipText("Enter user here");
        stockCountText.setEditable(false);
        setStockCount();

        rentedCountText = new JTextField(5);
        rentedCountText.setPreferredSize(new Dimension(10, 25));
        rentedCountText.setFont(new Font("Segoe UI", 0, 15));
        rentedCountText.setHorizontalAlignment(SwingConstants.CENTER);
        rentedCountText.setToolTipText("Enter user here");
        rentedCountText.setEditable(false);
        setRentedCount();

        searchButton = new JLabel(new ImageIcon("./src/Images/searchBut2.png"));
        searchButton.setPreferredSize(new Dimension(55, 30));
        searchButton.setOpaque(true);
        searchButton.setBackground(new Color(81, 131, 191));
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
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
            HistoryController historyController = ServerConnector.serverConnection().getHistoryController();
            java.util.List<HistoryM> viewHistory = historyController.viewHistory();
            userList = new String[viewHistory.size()];
            int i = 0;
            for (HistoryM historyM : viewHistory) {
                userList[i] = historyM.getUser();
                i++;
            }
            System.out.println(userList[0]);

            searchText.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {

                    if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                        String text = searchText.getEditor().getItem().toString();
                        for (int i = 0; i < userList.length; i++) {
                            if (text.length() <= userList[i].length()) {
                                if (text.equals(userList[i].substring(0, text.length()))) {
                                    for (int j = 0; j < searchText.getItemCount(); j++) {
                                        if (!userList[i].equals(searchText.getItemAt(j).toString())) {
                                            searchText.addItem(userList[i]);
                                            System.out.println("added");
                                        }
                                    }

                                }
                                searchText.showPopup();
                            } else {
                                searchText.addItem("No such User found");
                            }
                        }

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


        naviPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JLabel text = new JLabel("Search : ");
        text.setFont(new Font("Segoe UI Light", 0, 24));

        naviPanel.add(text);
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
         * Initialize swing components Set the relevant listeners to the
         * JTextFields Validate the text in the JTextFields
         */
        String columnNames[] = {"Date", "Time", "User", "Activity"};
        Object[][] rowData = {{"001", "957151205V", "Lee Junho", "Athletic"}, {"002", "957151205V", "Ok Tacyeon", "Athletic"}, {"003", "957151205V", "Lee Minho", "Athletic"},};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        HistoryTable = new JTable(model) {
            private static final long serialVersionUID = 1L;

            /**
             * Returning the Class of each column will allow different renderers
             * to be used based on Class
             *
             */
            //  
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        HistoryTable.setAutoCreateRowSorter(true);
        HistoryTable.setOpaque(true);
        HistoryTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        HistoryTable.getTableHeader().setForeground(new Color(255, 255, 255));
        HistoryTable.getTableHeader().setBackground(new Color(113, 153, 202));
        HistoryTable.setRowHeight(30);
        setTable();


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 4; i++) {
            HistoryTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        scrollPane = new JScrollPane(HistoryTable);
        scrollPane.setPreferredSize(new Dimension(453, 150));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));
        HistoryTable.setBackground(new Color(255, 255, 255));

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


        selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        selectionPanel.setBackground(new Color(255, 255, 255));
        selectionPanel.add(clientCount);
        selectionPanel.add(clientCountText);
        selectionPanel.add(stockCount);
        selectionPanel.add(stockCountText);
        selectionPanel.add(rentedCount);
        selectionPanel.add(rentedCountText);

        contentPanel.add(scrollPane);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(new JSeparator());
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(selectionPanel);

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
         * Arrange the history table with the details from server
         *
         */
        try {
            HistoryController historyController = ServerConnector.serverConnection().getHistoryController();
            java.util.List<HistoryM> viewHistory = historyController.viewHistory();
            DefaultTableModel model = (DefaultTableModel) HistoryTable.getModel();

            for (HistoryM historyM : viewHistory) {
                Object rowData[] = {historyM.getDate(), historyM.getTime(), historyM.getUser(), historyM.getActivity()};
                model.addRow(rowData);
            }

        } catch (NotBoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMessage() {
        /**
         * Notify the user whenever a new client,item,order is added or
         * returning an order
         *
         */
        setTable();
        setClientCount();
        setRentedCount();
    }

    private void setClientCount() {

        /**
         * Count and the set the current client count of the company
         *
         */
        try {
            ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
            ArrayList<ClientM> allClients = serverConnection.getAllClients();
            clientCountText.setText(Integer.toString(allClients.size()));

        } catch (NotBoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setStockCount() {
        /**
         * Count and the set the current stock count of the company
         *
         */
        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            int count = 0;
            for (ItemM itemM : allItems) {
                count += Integer.parseInt(itemM.getQty());
            }
            stockCountText.setText(Integer.toString(count));

        } catch (NotBoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setRentedCount() {
        /**
         * Count and the set the current rented count of the company
         *
         */
        try {
            OrderController orderController = ServerConnector.serverConnection().getOrderController();
            OrderDetailController orderDetailController = ServerConnector.serverConnection().getOrderDetailController();
            ArrayList<OrderM> allOrders = orderController.getAllOrders();
            int tot = 0;
            for (OrderM orderM : allOrders) {
                if (orderM.getState().equals("Pending")) {
                    ArrayList<OrderDetailM> searchOrderDetail = orderDetailController.searchOrderDetail(orderM.getiD());
                    for (OrderDetailM orderDetailM : searchOrderDetail) {
                        tot += orderDetailM.getQty();
                    }
                }
            }
            rentedCountText.setText(Integer.toString(tot));

        } catch (NotBoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        new History().setVisible(true);
    }
}
