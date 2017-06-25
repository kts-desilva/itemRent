package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.idcreator.IDCreator;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.controller.OrderController;
import edu.ijse.cmjd.itemrent.exception.DuplicateKeyException;
import edu.ijse.cmjd.itemrent.model.ClientM;
import edu.ijse.cmjd.itemrent.model.ItemM;
import edu.ijse.cmjd.itemrent.model.OrderDetailM;
import edu.ijse.cmjd.itemrent.model.OrderM;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import org.jdesktop.swingx.JXDatePicker;

public class AddOrder extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JComboBox clientText;
    private JComboBox nicCombo;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel selectionPanel;
    private JLabel id;
    private JLabel user;
    private JLabel client;
    private JLabel sport;
    private JLabel brand;
    private JLabel rDate;
    private JLabel nic;
    private JLabel since;
    private JLabel sizeLabel;
    private JLabel itemNo;
    private JLabel total;
    private JLabel addItemButton;
    private JLabel delItemButton;
    private JTextField idText;
    private JTextField userText;
    private JTextField brandText;
    private JTextField zipText;
    private JXDatePicker sinceText;
    private JXDatePicker rDateText;
    public static JTextField itemNoText;
    public static JTextField totalText;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel detailPanel;
    private JPanel fillPanel1;
    private JPanel fillPanel2;
    private JPanel titlePanel;
    private JPanel buttonCenterPanel;
    private JPanel detailCenterPanel;
    private JPanel fillPanel3;
    public static JTable OrderTable;

    public AddOrder() {
        initializeComponents();
        UIManager.put("JXMontView.unselectableDayForeground", new ColorUIResource(Color.GREEN));

        setFrameLayout();
        getContentPane().setBackground(new Color(255, 255, 255, 123));
        setUndecorated(true);
        //setOpacity(0.7f);
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
                dispose();
            }
        });

        header = new JLabel("Add Order");
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

        Font font = new Font("Segoe UI Light", Font.PLAIN, 18);

        id = new JLabel("ID", SwingConstants.LEFT);
        id.setFont(font);
        id.setPreferredSize(new Dimension(70, 20));

        user = new JLabel("User", SwingConstants.LEFT);
        user.setFont(font);
        user.setPreferredSize(new Dimension(65, 20));

        client = new JLabel("Client", SwingConstants.LEFT);
        client.setFont(font);
        client.setPreferredSize(new Dimension(75, 20));

        nic = new JLabel("NIC no", SwingConstants.LEFT);
        nic.setFont(font);
        nic.setPreferredSize(new Dimension(75, 20));

        rDate = new JLabel("R.Date", SwingConstants.LEFT);
        rDate.setFont(font);
        rDate.setPreferredSize(new Dimension(75, 20));

        since = new JLabel("Since", SwingConstants.LEFT);
        since.setFont(font);
        since.setPreferredSize(new Dimension(75, 20));

        sizeLabel = new JLabel("Size Info", SwingConstants.LEFT);
        sizeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        sizeLabel.setForeground(new Color(113, 153, 202));
        sizeLabel.setPreferredSize(new Dimension(90, 30));
        sizeLabel.setVerticalTextPosition(JLabel.TOP);

        itemNo = new JLabel("Item no.", SwingConstants.LEFT);
        itemNo.setFont(font);
        itemNo.setPreferredSize(new Dimension(75, 20));

        total = new JLabel("Total", SwingConstants.LEFT);
        total.setFont(font);
        total.setPreferredSize(new Dimension(75, 20));

        idText = new JTextField(15);
        idText.setPreferredSize(new Dimension(75, 25));
        idText.setToolTipText("Enter ID here");
        setIDText();

        userText = new JTextField(15);
        userText.setPreferredSize(new Dimension(75, 25));
        userText.setToolTipText("Enter user here");

        clientText = new JComboBox();
        clientText.setPreferredSize(new Dimension(20, 25));
        clientText.setToolTipText("Enter sport here");

        clientText.setUI(new BasicComboBoxUI() {
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

        clientText.setRenderer(new DefaultListCellRenderer() {
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

        clientText.setOpaque(false);

        clientText.setFont(new Font("Segoe UI", 0, 18));
        setClients();

        clientText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
                    ClientM serachClient = serverConnection.serachClient(clientText.getSelectedItem().toString());
                    nicCombo.setSelectedItem(serachClient.getNIC());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        brandText = new JTextField(15);
        brandText.setPreferredSize(new Dimension(10, 25));
        brandText.setToolTipText("Enter Tel.no here");


        //Set the unused dates color in to grey
        UIManager.put("JXMonthView.unselectableDayForeground", new ColorUIResource(new Color(185, 205, 229)));

        rDateText = new JXDatePicker();
        rDateText.setDate(new Date());
        rDateText.setFont(new Font("Segoe UI", 0, 13));
        rDateText.setPreferredSize(new Dimension(10, 25));
        rDateText.setToolTipText("Enter Tel.no here");


        zipText = new JTextField(15);
        zipText.setPreferredSize(new Dimension(10, 25));
        zipText.setToolTipText("Enter Tel.no here");


        sinceText = new JXDatePicker();
        sinceText.getMonthView().setLowerBound(new Date());


        sinceText.setDate(new Date());
        sinceText.setFont(new Font("Segoe UI", 0, 13));
        sinceText.setPreferredSize(new Dimension(10, 25));
        sinceText.setToolTipText("Enter Tel.no here");



        itemNoText = new JTextField(12);
        itemNoText.setPreferredSize(new Dimension(10, 35));
        itemNoText.setToolTipText("Enter Tel.no here");
        itemNoText.setFont(new Font("Segoe UI", 0, 18));
        itemNoText.setForeground(new Color(127, 127, 127));
        itemNoText.setText("0.0");
        itemNoText.setHorizontalAlignment(SwingConstants.CENTER);

        totalText = new JTextField(12);
        totalText.setPreferredSize(new Dimension(10, 35));
        totalText.setToolTipText("Enter Tel.no here");
        totalText.setFont(new Font("Segoe UI", 0, 26));
        totalText.setForeground(new Color(113, 153, 202));
        totalText.setText("0.0");
        totalText.setHorizontalAlignment(SwingConstants.RIGHT);

        nicCombo = new JComboBox();
        nicCombo.setPreferredSize(new Dimension(10, 25));
        nicCombo.setToolTipText("Select Package from here");

        //Change the filter button into an image
        nicCombo.setUI(new BasicComboBoxUI() {
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

        //Set the selection preferances of the NIC Combo
        nicCombo.setRenderer(new DefaultListCellRenderer() {
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

        nicCombo.setOpaque(false);

        nicCombo.setFont(new Font("Segoe UI", 0, 18));
        nicCombo.addItem("Starter");
        nicCombo.addItem("Midi User");
        nicCombo.addItem("Haevy User");
        nicCombo.addItem("Master");

        setNICs();

        addItemButton = new JLabel("Add", new ImageIcon("./src/Images/addBut1.png"), 0);
        addItemButton.setFont(new Font("Segoe UI Light", 0, 16));
        addItemButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new ItemView(OrderTable, itemNoText, totalText).setVisible(true);


            }
        });

        delItemButton = new JLabel("Delete", new ImageIcon("./src/Images/delBut1.png"), 0);
        delItemButton.setFont(new Font("Segoe UI Light", 0, 16));

        delItemButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                DefaultTableModel model = (DefaultTableModel) OrderTable.getModel();
                if (OrderTable.getSelectedRowCount() > 0) {
                    model.removeRow(OrderTable.getSelectedRow());
                } else {
                    setPopup("Please select a row", delItemButton, 20, 0);
                }


            }
        });

        String columnNames[] = {"ID", "Desc", "Price", "Qty", "Total"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        OrderTable = new JTable(model);
        OrderTable.setAutoCreateRowSorter(true);
        OrderTable.setOpaque(true);
        OrderTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        OrderTable.getTableHeader().setForeground(new Color(255, 255, 255));
        OrderTable.getTableHeader().setBackground(new Color(113, 153, 202));
        OrderTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 5; i++) {
            OrderTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        JScrollPane scrollPane = new JScrollPane(OrderTable);
        scrollPane.setPreferredSize(new Dimension(453, 150));
        System.out.println("Scroll Pane : " + scrollPane.getPreferredSize());
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
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });

        JLabel selectionLabel = new JLabel("Select  Items", SwingConstants.LEFT);
        selectionLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 23));
        selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        selectionPanel.setBackground(new Color(255, 255, 255));
        selectionPanel.add(addItemButton);
        selectionPanel.add(delItemButton);

        JPanel sp = new JPanel(new BorderLayout());
        sp.setBackground(new Color(255, 255, 255));
        sp.add("West", selectionLabel);
        sp.add("East", selectionPanel);

        JPanel p = new JPanel();
        p.setBackground(new Color(255, 255, 255));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(sp);
        p.add(Box.createVerticalStrut(10));
        p.add(scrollPane);

        font = new Font("Segoe UI Light", Font.PLAIN, 16);

        addButton = new JButton("Add");
        addButton.setFont(font);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(true);

        addButton.setBackground(new Color(185, 205, 229));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                try {
                    ItemController itemController = ServerConnector.serverConnection().getItemController();
                    ArrayList<OrderDetailM> itemList = new ArrayList<>();
                    OrderDetailM orderItem = null;

                    OrderController orderController = ServerConnector.serverConnection().getOrderController();
                    boolean addOrder = orderController.addOrder(new OrderM(idText.getText(), new SimpleDateFormat("dd/MM/YYYY").format(sinceText.getDate()),
                            userText.getText(), clientText.getSelectedItem().toString(), new SimpleDateFormat("dd/MM/YYYY").format(rDateText.getDate()),
                            "Pending"), itemList);
                    if (addOrder) {
                        new MessagePane(null, "Added Sucess !").setVisible(true);

                        for (int i = 0; i < OrderTable.getRowCount(); i++) {
                            orderItem = new OrderDetailM(idText.getText(), OrderTable.getValueAt(i, 0).toString(),
                                    OrderTable.getValueAt(i, 1).toString(),
                                    Integer.parseInt(OrderTable.getValueAt(i, 3).toString()),
                                    Double.parseDouble(OrderTable.getValueAt(i, 2).toString()),
                                    Double.parseDouble(OrderTable.getValueAt(i, 4).toString()));
                            itemList.add(orderItem);

                            ItemM serachItem = itemController.serachItem(OrderTable.getValueAt(i, 0).toString());

                            int val = Integer.parseInt(serachItem.getQty()) - Integer.parseInt(OrderTable.getValueAt(i, 3).toString());

                            itemController.editItem(new ItemM(serachItem.getID(), serachItem.getDes(),
                                    serachItem.getPrice(), serachItem.getSport(), serachItem.getBrand(),
                                    Integer.toString(val), serachItem.getSince(), serachItem.getLength(),
                                    serachItem.getWidth(), serachItem.getRadius(), serachItem.getWeight()));

                        }

                    } else {
                        new MessagePane(null, "Added Faild !").setVisible(true);
                    }
                } catch (DuplicateKeyException ex) {
                    new MessagePane(null, ex.getMessage()).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    new MessagePane(null, ex.getMessage()).setVisible(true);
                } catch (RemoteException ex) {
                    new MessagePane(null, ex.getMessage()).setVisible(true);
                } catch (NotBoundException ex) {
                    new MessagePane(null, ex.getMessage()).setVisible(true);
                } catch (MalformedURLException ex) {
                    new MessagePane(null, ex.getMessage()).setVisible(true);
                } catch (IOException ex) {
                    new MessagePane(null, ex.getMessage()).setVisible(true);
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
                dispose();
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

        Component com[] = {id, idText, user, userText, client, clientText, sport, clientText, brand, brandText};
        GridBagConstraints cons = new GridBagConstraints();

        cons.anchor = GridBagConstraints.WEST;
        cons.fill = GridBagConstraints.BOTH;
        cons.insets = new Insets(10, 15, 20, 10);

        cons.weightx = 3.0;
        cons.weighty = 3.0;

        cons.gridx = 0;
        cons.gridy = 0;
        detailPanel.add(id, cons);
        cons.gridx = 10;
        cons.gridy = 0;
        detailPanel.add(idText, cons);

        cons.gridx = 20;
        cons.gridy = 0;
        detailPanel.add(user, cons);
        cons.gridx = 30;
        cons.gridy = 0;
        detailPanel.add(userText, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        detailPanel.add(client, cons);
        cons.gridx = 10;
        cons.gridy = 10;
        detailPanel.add(clientText, cons);

        cons.gridx = 20;
        cons.gridy = 10;
        detailPanel.add(rDate, cons);
        cons.gridx = 30;
        cons.gridy = 10;
        detailPanel.add(rDateText, cons);

        cons.gridx = 0;
        cons.gridy = 20;
        detailPanel.add(nic, cons);
        cons.gridx = 10;
        cons.gridy = 20;
        detailPanel.add(nicCombo, cons);

        cons.gridx = 20;
        cons.gridy = 20;
        detailPanel.add(since, cons);
        cons.gridx = 30;
        cons.gridy = 20;
        detailPanel.add(sinceText, cons);

        cons.insets = new Insets(10, 10, 10, 10);

        cons.gridx = 0;
        cons.gridy = 40;
        cons.gridwidth = 40;
        cons.gridheight = 1;
        detailPanel.add(p, cons);

        cons.gridx = 0;
        cons.gridy = 45;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        detailPanel.add(itemNo, cons);
        cons.gridx = 10;
        cons.gridy = 45;
        detailPanel.add(itemNoText, cons);

        cons.gridx = 20;
        cons.gridy = 45;
        detailPanel.add(total, cons);
        cons.gridx = 30;
        cons.gridy = 45;
        detailPanel.add(totalText, cons);

        detailCenterPanel.add(detailPanel);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.add(addButton);
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

    private void setCountTotal() {

        /**
         * Updates the total text when a new order item is added
         *
         */
        new Thread() {
            public void run() {
                while (true) {
                    int count = 0;
                    double tot = 0;
                    for (int i = 0; i < OrderTable.getRowCount(); i++) {
                        if ((OrderTable.getValueAt(i, 3) != null) && (OrderTable.getValueAt(i, 4) != null)) {
                            count += Integer.parseInt(OrderTable.getValueAt(i, 3).toString());
                            tot += Double.parseDouble(OrderTable.getValueAt(i, 4).toString());
                        }

                    }
                    itemNoText.setText(Integer.toString(count));
                    totalText.setText(Double.toString(tot));

                }
            }
        }.start();
    }

    private void setPopup(String text, JLabel lb, int x, int y) {

        /**
         * Set the pop up window for the incorrect inputs The pop up will be
         * visible for 0.2 seconds and faded out
         *
         */
        final JPopupMenu menu = new JPopupMenu();
        JLabel popupLabel = new JLabel(text);
        popupLabel.setOpaque(true);
        popupLabel.setFont(new Font("Segoe UI", 0, 13));
        popupLabel.setBackground(new Color(185, 205, 229));
        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.setBackground(new Color(185, 205, 229));

        menu.setLightWeightPopupEnabled(false);

        JLabel icon = new JLabel(new ImageIcon("./src/Images/mistakeBut.png"));
        menuPanel.add(icon);
        menuPanel.add(popupLabel);
        menu.add(menuPanel);

        menu.show(lb, lb.getLocation().x - x, lb.getLocation().y - y);

        /**
         * To fade the pop up window
         *
         */
        final Window windowAncestor = SwingUtilities.getWindowAncestor(menu);

        final float opacity = 0.0f;

        new Thread() {
            @Override
            public void run() {
                for (int i = 10; i > 0; i--) {
                    try {
                        windowAncestor.setOpacity(i * 0.1f + opacity);
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                menu.setVisible(false);
            }
        }.start();

    }

    private void setClients() {
        
        /**
         * Load the current clients into the client combo
         * 
         */
        
        try {
            ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
            ArrayList<ClientM> allClients = serverConnection.getAllClients();
            for (ClientM clientM : allClients) {
                clientText.addItem(clientM.getId());
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setNICs() {
        try {
            ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
            ArrayList<ClientM> allClients = serverConnection.getAllClients();
            for (ClientM clientM : allClients) {
                nicCombo.addItem(clientM.getNIC());
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setIDText() {
        
        /**
         * Set the new item id ;calculated by the IDCreator class
         * 
         */

        try {
            OrderController orderController = ServerConnector.serverConnection().getOrderController();
            String createID = new IDCreator(orderController).createID();
            idText.setText(createID);

        } catch (NotBoundException ex) {
            Logger.getLogger(AddClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AddClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String args[]) {
        new AddOrder().setVisible(true);
    }
}
