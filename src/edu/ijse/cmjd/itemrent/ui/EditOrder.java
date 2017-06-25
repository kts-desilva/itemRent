package edu.ijse.cmjd.itemrent.ui;

import harsh.p.raval.lightbox.LightBox;
import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.controller.OrderController;
import edu.ijse.cmjd.itemrent.controller.OrderDetailController;
import edu.ijse.cmjd.itemrent.model.ClientM;
import edu.ijse.cmjd.itemrent.model.OrderDetailM;
import edu.ijse.cmjd.itemrent.model.OrderM;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import org.jdesktop.swingx.JXDatePicker;

public class EditOrder extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JTextField clientText;
    private JTextField nicText;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JLabel id;
    private JLabel user;
    private JLabel client;
    private JLabel sport;
    private JLabel brand;
    private JLabel rDate;
    private JLabel nic;
    private JLabel since;
    private JLabel sizeLabel;
    private JLabel damage;
    private JLabel charge;
    private JComboBox idText;
    private JTextField userText;
    private JTextField brandText;
    private JTextField zipText;
    private JXDatePicker sinceText;
    private JXDatePicker rDateText;
    public static JTextField damageText;
    public static JTextField chargeText;
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
    private JTable itemTable;
    private JFrame frame;
    private int count = 0;

    public EditOrder() {
        initializeComponents();

        frame = this;

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
                dispose();
            }
        });

        header = new JLabel("Return Order");
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

        damage = new JLabel("Damage", SwingConstants.LEFT);
        damage.setFont(font);
        damage.setPreferredSize(new Dimension(75, 20));

        charge = new JLabel("Charge", SwingConstants.LEFT);
        charge.setFont(font);
        charge.setPreferredSize(new Dimension(75, 20));

        idText = new JComboBox();
        idText.setPreferredSize(new Dimension(10, 25));
        idText.setToolTipText("Enter sport here");

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
        idText.setFont(new Font("Segoe UI", 0, 18));

        setId();

        idText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        OrderController orderController = ServerConnector.serverConnection().getOrderController();
                        OrderM serachOrder = orderController.serachOrder(idText.getSelectedItem().toString());
                        count++;
                        if (!serachOrder.getState().equals("Payed")) {

                            userText.setText(serachOrder.getUser());
                            clientText.setText(serachOrder.getClient());
                            sinceText.setDate(new SimpleDateFormat("dd/MM/YYYY").parse(serachOrder.getDate()));
                            rDateText.setDate(new SimpleDateFormat("dd/MM/YYYY").parse(serachOrder.getrDate()));

                            ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
                            ClientM serachClient = serverConnection.serachClient(clientText.getText());
                            nicText.setText(serachClient.getNIC());


                            OrderDetailController orderDetailController = ServerConnector.serverConnection().getOrderDetailController();
                            ArrayList<OrderDetailM> searchOrderDetail = orderDetailController.searchOrderDetail(idText.getSelectedItem().toString());

                            //-------- //Write to add data into the table
                            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
                            model.setRowCount(0);
                            for (OrderDetailM orderDetailM : searchOrderDetail) {
                                Object[] rowData = {orderDetailM.getId(), orderDetailM.getDes(), orderDetailM.getPrice(),
                                    orderDetailM.getQty(), orderDetailM.getTotal(), false
                                };
                                model.addRow(rowData);
                            }
                        } else {
                            String txt = "<html><center>This Order has been settled already! Please settle another</center></html>";
                            LightBox box1 = new LightBox();
                            box1.createLightBoxEffect(frame, setLightBoxMessage(txt), true, true, false);
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NotBoundException ex) {
                        Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (IOException ex) {
                        Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        userText = new JTextField(15);

        userText.setPreferredSize(new Dimension(75, 25));
        userText.setToolTipText("Enter user here");

        clientText = new JTextField(10);

        clientText.setPreferredSize(new Dimension(10, 25));
        clientText.setToolTipText("Enter ID here");

        nicText = new JTextField(15);

        nicText.setPreferredSize(new Dimension(10, 25));
        nicText.setToolTipText("Enter Tel.no here");

        brandText = new JTextField(15);

        brandText.setPreferredSize(new Dimension(10, 25));
        brandText.setToolTipText("Enter Tel.no here");

        rDateText = new JXDatePicker();

        rDateText.setDate(new Date());
        rDateText.setFont(new Font("Segoe UI", 0, 13));
        rDateText.setPreferredSize(new Dimension(10, 25));
        rDateText.setToolTipText("Enter Tel.no here");


        zipText = new JTextField(15);

        zipText.setPreferredSize(new Dimension(10, 25));
        zipText.setToolTipText("Enter Tel.no here");

        sinceText = new JXDatePicker();

        sinceText.setDate(new Date());
        sinceText.setFont(new Font("Segoe UI", 0, 13));
        sinceText.setPreferredSize(new Dimension(10, 25));
        sinceText.setToolTipText("Enter Tel.no here");

        damageText = new JTextField(15);

        damageText.setPreferredSize(new Dimension(10, 35));
        damageText.setToolTipText("Enter Tel.no here");
        damageText.setFont(new Font("Segoe UI", 0, 18));
        damageText.setForeground(new Color(127, 127, 127));
        damageText.setText("0.0");
        damageText.setHorizontalAlignment(SwingConstants.CENTER);
        chargeText = new JTextField(15);

        chargeText.setPreferredSize(new Dimension(10, 35));
        chargeText.setToolTipText("Enter Tel.no here");
        chargeText.setFont(new Font("Segoe UI", 0, 20));
        chargeText.setForeground(new Color(113, 153, 202));
        chargeText.setText("0.0");
        chargeText.setHorizontalAlignment(SwingConstants.RIGHT);
        String columnNames[] = {"ID", "Desc", "Price", "Qty", "Total", "Checked"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 3);
        itemTable = new JTable(model) {
            private static final long serialVersionUID = 1L;

            public Class getColumnClass(int column) {

                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Double.class;
                    case 4:
                        return Integer.class;
                    case 5:
                        return Boolean.class;
                    default:
                        return null;
                }
            }
        };

        itemTable.setAutoCreateRowSorter(true);
        itemTable.setOpaque(true);
        itemTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        itemTable.getTableHeader().setForeground(new Color(255, 255, 255));
        itemTable.getTableHeader().setBackground(new Color(113, 153, 202));
        itemTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0;
                i < 5; i++) {
            itemTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(itemTable);

        scrollPane.setPreferredSize(new Dimension(453, 150));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));
        itemTable.setBackground(new Color(255, 255, 255));

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

        JPanel scrollPanel = new JPanel();
        scrollPanel.setBackground(new Color(255, 255, 255));
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.add(scrollPane);

        font = new Font("Segoe UI Light", Font.PLAIN, 16);

        addButton = new JButton("Returned");
        addButton.setFont(font);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(true);

        addButton.setBackground(new Color(185, 205, 229));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                    boolean rst = true;
                    for (int i = 0; i < itemTable.getRowCount(); i++) {
                        boolean valueAt = (boolean) itemTable.getValueAt(i, 5);
                        if (valueAt == false) {
                            rst = false;
                            break;
                        }
                    }
                    if (rst) {
                        OrderController orderController = ServerConnector.serverConnection().getOrderController();
                        boolean editOrder = orderController.editOrder(new OrderM(idText.getSelectedItem().toString(), sdf.format(sinceText.getDate()),
                                userText.getText(), clientText.getText(), sdf.format(rDateText.getDate()), "Returned"));
                        if (editOrder) {
                            new MessagePane(null, "Order returned Succesfully !").setVisible(true);
                        } else {
                            new MessagePane(null, "Order returning Faild !").setVisible(true);
                        }
                    } else {
                        String txt = "Please check the items before returning!";
                        LightBox box = new LightBox();
                        box.createLightBoxEffect(frame, setLightBoxMessage(txt));
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
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
        detailPanel.add(nicText, cons);

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
        detailPanel.add(scrollPanel, cons);

        cons.gridx = 0;
        cons.gridy = 45;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        detailPanel.add(damage, cons);
        cons.gridx = 10;
        cons.gridy = 45;
        detailPanel.add(damageText, cons);

        cons.gridx = 20;
        cons.gridy = 45;
        detailPanel.add(charge, cons);
        cons.gridx = 30;
        cons.gridy = 45;
        detailPanel.add(chargeText, cons);

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
         * Count the total of the items of the order selected
         *
         */
        new Thread() {
            public void run() {
                while (true) {
                    int count = 0;
                    double tot = 0;
                    for (int i = 0; i < itemTable.getRowCount(); i++) {
                        if ((itemTable.getValueAt(i, 3) != null) && (itemTable.getValueAt(i, 4) != null)) {
                            count += Integer.parseInt(itemTable.getValueAt(i, 3).toString());
                            tot += Double.parseDouble(itemTable.getValueAt(i, 4).toString());
                        }

                    }
                    damageText.setText(Integer.toString(count));
                    chargeText.setText(Double.toString(tot));

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
        final JPopupMenu popup = new JPopupMenu();
        JLabel messageLabel = new JLabel(text);
        messageLabel.setOpaque(true);
        messageLabel.setFont(new Font("Segoe UI", 0, 13));
        messageLabel.setBackground(new Color(185, 205, 229));
        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.setBackground(new Color(185, 205, 229));

        popup.setLightWeightPopupEnabled(false);

        JLabel ic = new JLabel(new ImageIcon("./src/Images/mistakeBut.png"));
        menuPanel.add(ic);
        menuPanel.add(messageLabel);
        popup.add(menuPanel);

        popup.show(lb, lb.getLocation().x - x, lb.getLocation().y - y);


        /**
         * To fade out the pop up
         *
         */
        
        final Window windowAncestor = SwingUtilities.getWindowAncestor(popup);

        final float opacity = 0.0f;

        new Thread() {
            @Override
            public void run() {
                for (int i = 10; i > 0; i--) {
                    try {
                        windowAncestor.setOpacity(i * 0.1f + opacity);
                        Thread.sleep(200);


                    } catch (InterruptedException ex) {
                        Logger.getLogger(EditOrder.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
                popup.setVisible(false);
            }
        }.start();

    }

    public static void main(String args[]) {
        new EditOrder().setVisible(true);
    }

    private void setId() {
        
        /**
         * Load all the order IDs into  the id combo
         *
         */
        
        try {
            OrderController orderController = ServerConnector.serverConnection().getOrderController();
            ArrayList<OrderM> allOrders = orderController.getAllOrders();
            for (OrderM orderM : allOrders) {
                idText.addItem(orderM.getiD());


            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JPanel setLightBoxMessage(String txt) {
        
        /**
         * Set the message box for invalid or incomplete contents of the frame 
         * Shows the incomplete fields in order to make them filled
         * Uses the light box effect which turns the current frame into black 
         * and the required message box will be appeared
         * 
         */
        
        final JPanel lightBoxPanel = new JPanel();
        lightBoxPanel.setBorder(BorderFactory.createLineBorder(new Color(113, 153, 202), 6));
        lightBoxPanel.setSize(650, 120);
        lightBoxPanel.setBackground(new Color(255, 255, 255));
        lightBoxPanel.setLayout(new BorderLayout());

        JLabel messageCloseButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        messageCloseButton.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        messageCloseButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                LightBox lightBox = new LightBox();
                lightBox.closeLightBox(frame, lightBoxPanel);
            }
        });

        JLabel messageWarningButton = new JLabel(new ImageIcon("./src/Images/warningBut3.png"));
        messageWarningButton.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));


        JLabel messageHeader = new JLabel("Please Check out");
        messageHeader.setForeground(new Color(81, 131, 191));
        messageHeader.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
        messageHeader.setBorder(BorderFactory.createEmptyBorder(3, 20, 3, 3));

        JLabel messageContent = new JLabel();
        messageContent.setForeground(new Color(0, 0, 0));
        messageContent.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));
        messageContent.setBorder(BorderFactory.createEmptyBorder(8, 20, 3, 3));

        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        messagePanel.add(messageHeader);
        messagePanel.add(messageWarningButton);
        messagePanel.setBackground(new Color(255, 255, 255));

        JPanel messageTopPanel = new JPanel(new BorderLayout());
        messageTopPanel.setBackground(new Color(255, 255, 255));
        messageTopPanel.add(messageCloseButton, BorderLayout.EAST);
        messageTopPanel.add(messagePanel, BorderLayout.WEST);

        JPanel southPanel = new JPanel();
        southPanel.setSize(new Dimension(650, 10));
        southPanel.setBackground(new Color(255, 255, 255));

        JPanel contPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        JPanel messageContentPanel = new JPanel();
        messageContentPanel.setBackground(new Color(255, 255, 255));
        contPanel.setBackground(new Color(255, 255, 255));
        contPanel.add(messageContent);
        contPanel.add(messageContentPanel);

        lightBoxPanel.add(messageTopPanel, BorderLayout.NORTH);
        lightBoxPanel.add(contPanel, BorderLayout.CENTER);
        lightBoxPanel.add(southPanel, BorderLayout.SOUTH);

        messageContent.setText(txt);

        return lightBoxPanel;
    }
}
