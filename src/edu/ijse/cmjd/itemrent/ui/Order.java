package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.observer.impl.OrderObserverImpl;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.controller.OrderController;
import edu.ijse.cmjd.itemrent.controller.OrderDetailController;
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

public class Order extends JFrame {

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
    private JTable OrderTable;
    private JScrollPane scrollPane;
    private OrderObserverImpl impl;
    private String[] oid = null;
    private String[] orDate = null;
    private String[] cid = null;
    private String[] cName = null;

    public Order() {
        initializeComponents();
        try {
            impl = new OrderObserverImpl(this);
            OrderController orderController = ServerConnector.serverConnection().getOrderController();
            orderController.addOrderObserver(impl);
        } catch (NotBoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);

        } catch (RemoteException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }

        setFrameLayout();
        getContentPane().setBackground(new Color(255, 255, 255, 123));
        setUndecorated(true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        pack();

    }

    private void initializeComponents() {

        closeButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
        });

        header = new JLabel("Service");
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
                new AddOrder().setVisible(true);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                addButton.setForeground(new Color(113, 153, 202));
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                addButton.setForeground(new Color(0, 0, 0));
            }
        });


        delButton = new JLabel("Delete", new ImageIcon("./src/Images/delBut.png"), 0);
        delButton.setFont(new Font("Segoe UI Light", 0, 20));
        delButton.setHorizontalTextPosition(JLabel.CENTER);
        delButton.setVerticalTextPosition(JLabel.BOTTOM);
        delButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                //new DeleteOrder().setVisible(true);
            }
        });
        delButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                delButton.setForeground(new Color(113, 153, 202));
            }
        });
        delButton.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                delButton.setForeground(new Color(0, 0, 0));
            }
        });

        editButton = new JLabel("Return", new ImageIcon("./src/Images/returnBut.png"), 0);
        editButton.setFont(new Font("Segoe UI Light", 0, 20));
        editButton.setHorizontalTextPosition(JLabel.CENTER);
        editButton.setVerticalTextPosition(JLabel.BOTTOM);
        editButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new EditOrder().setVisible(true);
            }
        });
        editButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                editButton.setForeground(new Color(113, 153, 202));
            }
        });
        editButton.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                editButton.setForeground(new Color(0, 0, 0));
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
        searchText.setPreferredSize(new Dimension(450, 25));
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

        Object searchTextContainer = searchText.getUI().getAccessibleChild(searchText, 0);
        Component co = ((Container) searchTextContainer).getComponent(0);

        if (co instanceof JScrollPane) {
            JScrollPane scrollpane1 = (JScrollPane) co;
            JScrollBar bar = new JScrollBar(JScrollBar.VERTICAL);
            scrollpane1.setVerticalScrollBar(bar);
            bar.setUI(new BasicScrollBarUI() {
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
                    g.fillRect(4, 0, thumbBounds.width - 10, thumbBounds.height - 12);

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
                    JButton noButton = new JButton();
                    noButton.setPreferredSize(new Dimension(0, 0));
                    noButton.setMinimumSize(new Dimension(0, 0));
                    noButton.setMaximumSize(new Dimension(0, 0));
                    return noButton;
                }
            });

        }


        searchText.setRenderer(
                new DefaultListCellRenderer() {
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
                        switch (cr) {
                            case "OID":
                                for (int i = 0; i < oid.length; i++) {
                                    if (text.length() <= oid[i].length()) {
                                        if (text.equals(oid[i].substring(0, text.length()))) {
                                            searchText.addItem(oid[i]);
                                        }
                                        searchText.showPopup();
                                    } else {
                                        searchText.addItem("No such OID found");
                                    }
                                }
                                break;

                            case "Or.Date":
                                for (int i = 0; i < orDate.length; i++) {
                                    if (text.length() <= orDate[i].length()) {
                                        if (text.equals(orDate[i].substring(0, text.length()))) {
                                            searchText.addItem(orDate[i]);
                                        }
                                        searchText.showPopup();
                                    } else {
                                        searchText.addItem("No such Or.Date found");
                                    }
                                }
                                break;
                            case "CID":
                                for (int i = 0; i < cid.length; i++) {
                                    if (text.length() <= cid[i].length()) {
                                        if (text.equals(cid[i].substring(0, text.length()))) {
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (!cid[i].equals(searchText.getItemAt(j).toString())) {
                                                    searchText.addItem(cid[i]);
                                                }
                                            }

                                        }
                                        searchText.showPopup();
                                    } else {
                                        searchText.addItem("No such Customer found");
                                    }
                                }
                                break;
                            case "Cl.Name":
                                for (int i = 0; i < cName.length; i++) {
                                    if (text.length() <= cName[i].length()) {
                                        if (text.equals(cName[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                System.out.println("Item count 0");
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
                            default:
                                new MessagePane(null, "No such item found !").setVisible(true);

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

                JButton filterButton = new JButton(new ImageIcon("./src/Images/dropBut.png"));
                filterButton.setVerticalAlignment(JButton.BOTTOM);
                filterButton.setBackground(new Color(255, 255, 255));
                filterButton.setContentAreaFilled(false);
                filterButton.setFocusPainted(false);
                filterButton.setBorder(BorderFactory.createEmptyBorder());

                return filterButton;
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
        criteriaText.addItem("OID");
        criteriaText.addItem("Or.Date");
        criteriaText.addItem("CID");
        criteriaText.addItem("Cl.Name");

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
         * Initialize swing components Set the relevant listeners to the
         * JTextFields Validate the text in the JTextFields
         */
        String columnNames[] = {"ID", "Date", "User", "Client", "NIC No", "Name", "R.Date", "Item no.", "Total", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        OrderTable = new JTable(model) {
            private static final long serialVersionUID = 1L;
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        setTable();

        OrderTable.setAutoCreateRowSorter(true);
        OrderTable.setOpaque(true);
        OrderTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        OrderTable.getTableHeader().setForeground(new Color(255, 255, 255));
        OrderTable.getTableHeader().setBackground(new Color(113, 153, 202));
        OrderTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 10; i++) {
            OrderTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        OrderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (OrderTable.getSelectedRowCount() != 0) {
                    setPopUp(OrderTable.getValueAt(OrderTable.getSelectedRow(), 0).toString(),
                            e.getX(), e.getY());
                }
            }
        });


        scrollPane = new JScrollPane(OrderTable);
        scrollPane.setPreferredSize(new Dimension(453, 250));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));
        OrderTable.setBackground(new Color(255, 255, 255));

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
                JButton noButton = new JButton();
                noButton.setPreferredSize(new Dimension(0, 0));
                noButton.setMinimumSize(new Dimension(0, 0));
                noButton.setMaximumSize(new Dimension(0, 0));
                return noButton;
            }
        });

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
        //cons.gridwidth = 2;
        add(contentPanel, cons);

    }

    private void setTable() {
        try {
            OrderController orderController = ServerConnector.serverConnection().getOrderController();
            ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
            OrderDetailController orderDetailController = ServerConnector.serverConnection().getOrderDetailController();
            ArrayList<OrderM> allOrders = orderController.getAllOrders();

            DefaultTableModel model = (DefaultTableModel) OrderTable.getModel();
            model.setRowCount(0);
            if (allOrders != null) {
                for (OrderM orderM : allOrders) {
                    ArrayList<OrderDetailM> searchOrderDetail = orderDetailController.searchOrderDetail(orderM.getiD());
                    double tot = 0;
                    for (OrderDetailM orderDetailM : searchOrderDetail) {
                        tot += orderDetailM.getTotal();
                    }

                    Object[] rowData = {orderM.getiD(), orderM.getDate(), orderM.getUser(), orderM.getClient(),
                        serverConnection.serachClient(orderM.getClient()).getNIC(),
                        serverConnection.serachClient(orderM.getClient()).getName(),
                        orderM.getrDate(),
                        orderDetailController.searchOrderDetail(orderM.getiD()).size(),
                        tot, orderM.getState()
                    };

                    model.addRow(rowData);
                }
            } else {
                new MessagePane(null, "No entries yet").setVisible(true);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        new Order().setVisible(true);
    }

    public void setMessage(String message) {
        /**
         * Set the message whenever a new order is added
         *
         */
        new MessagePane(this, message).setVisible(true);

        setTable();
        try {
            setData();

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

    private void setData() throws NotBoundException, MalformedURLException, RemoteException, ClassNotFoundException, IOException {

        /**
         * Set the data arrays to use when a searching is being done
         *
         */
        OrderController orderController = ServerConnector.serverConnection().getOrderController();
        ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
        ArrayList<OrderM> allOrders = orderController.getAllOrders();

        oid = new String[allOrders.size()];
        orDate = new String[allOrders.size()];
        cid = new String[allOrders.size()];
        cName = new String[allOrders.size()];

        int i = 0;
        for (OrderM om : allOrders) {
            oid[i] = om.getiD();
            orDate[i] = om.getDate();
            cid[i] = om.getClient();
            cName[i] = serverConnection.serachClient(om.getClient()).getName();
            i++;

        }
    }

    private void setTableUsingCriteria(String criteria, String element) throws NotBoundException,
            MalformedURLException, RemoteException, ClassNotFoundException, IOException {

        /**
         * Set the order table according to the criteria
         *
         */
        OrderM serachOrder = ServerConnector.serverConnection().
                getOrderController().serachOrder(element);
        ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
        OrderDetailController orderDetailController = ServerConnector.serverConnection().getOrderDetailController();
        OrderController orderController = ServerConnector.serverConnection().getOrderController();

        ArrayList<OrderM> ordersFiltered = null;

        switch (criteria) {
            case "OID":
                double tot = 0;

                ArrayList<OrderDetailM> searchOrderDetail = orderDetailController.searchOrderDetail(serachOrder.getiD());

                for (OrderDetailM orderDetailM : searchOrderDetail) {
                    tot += orderDetailM.getTotal();
                }
                DefaultTableModel dtm = (DefaultTableModel) OrderTable.getModel();
                dtm.setRowCount(0);
                Object[] rowData = {serachOrder.getiD(), serachOrder.getDate(), serachOrder.getUser(),
                    serachOrder.getClient(), serverConnection.serachClient(serachOrder.getClient()).getNIC(),
                    serverConnection.serachClient(serachOrder.getClient()).getName(),
                    serachOrder.getrDate(), searchOrderDetail.size(),
                    tot, serachOrder.getState()
                };
                dtm.addRow(rowData);
                break;
            case "Or.Date":
                ordersFiltered = orderController.getOrdersFilteredDate(element);
                break;

            case "CID":
                ordersFiltered = orderController.getOrdersFilteredClient(element);
                break;
            case "Cl.Name":
                ordersFiltered = orderController.getOrdersFilteredClientName(element);
                break;
            case "All":
                setTable();
                break;
            default:

        }
        if (ordersFiltered != null) {
            DefaultTableModel dtm = (DefaultTableModel) OrderTable.getModel();
            dtm.setRowCount(0);
            for (OrderM orderM : ordersFiltered) {
                double tot = 0;

                ArrayList<OrderDetailM> searchOrderDetail = orderDetailController.searchOrderDetail(orderM.getiD());

                for (OrderDetailM orderDetailM : searchOrderDetail) {
                    tot += orderDetailM.getTotal();
                }
                Object[] rowData = {orderM.getiD(), orderM.getDate(), orderM.getUser(),
                    orderM.getClient(), serverConnection.serachClient(orderM.getClient()).getNIC(),
                    serverConnection.serachClient(orderM.getClient()).getName(),
                    orderM.getrDate(), searchOrderDetail.size(),
                    tot, orderM.getState()
                };
                dtm.addRow(rowData);
            }
        }

    }

    private void setPopUp(String id, int x, int y) {

        /**
         * Set the pop up with the item list of the selected order id
         *
         */
        
        try {
            JPopupMenu menu = new JPopupMenu();
            menu.setLightWeightPopupEnabled(false);
            Object[] cName = {"Des", "Price", "Qty", "Total"};
            JPanel tPanel = new JPanel();
            menu.setOpaque(true);
            tPanel.setBackground(new Color(255, 255, 255));
            menu.setBackground(new Color(255, 255, 255));
            DefaultTableModel model = new DefaultTableModel(cName, 0);
            JTable table = new JTable(model);

            table.setOpaque(true);
            table.getTableHeader().setFont(new Font("Segoe UI Light", 0, 12));
            table.getTableHeader().setForeground(new Color(255, 255, 255));
            table.getTableHeader().setBackground(new Color(113, 153, 202));
            table.setRowHeight(20);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < 4; i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            table.getColumnModel().getColumn(0).setPreferredWidth(100);

            JScrollPane pane = new JScrollPane(table);
            pane.setOpaque(true);
            pane.setBackground(new Color(255, 255, 255));
            pane.setPreferredSize(new Dimension(350, 75));
            pane.getViewport().setBackground(new Color(255, 255, 255));
            pane.setBorder(BorderFactory.createEmptyBorder());

            pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
                    g.fillRect(3, 0, thumbBounds.width - 10, thumbBounds.height - 12);

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
                    JButton noButton = new JButton();
                    noButton.setPreferredSize(new Dimension(0, 0));
                    noButton.setMinimumSize(new Dimension(0, 0));
                    noButton.setMaximumSize(new Dimension(0, 0));
                    return noButton;
                }
            });

            OrderDetailController orderDetailController = ServerConnector.serverConnection().getOrderDetailController();
            ArrayList<OrderDetailM> searchOrderDetail = orderDetailController.searchOrderDetail(id);
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            for (OrderDetailM orderDetailM : searchOrderDetail) {
                Object rowData[] = {orderDetailM.getDes(), orderDetailM.getPrice(), orderDetailM.getQty(), orderDetailM.getTotal()
                };
                dtm.addRow(rowData);
            }
            tPanel.add(pane);
            menu.add(tPanel);

            menu.show(OrderTable, x, y);

            Window windowAncestor = SwingUtilities.getWindowAncestor(menu);
            windowAncestor.setOpacity(0.9f);

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
}
