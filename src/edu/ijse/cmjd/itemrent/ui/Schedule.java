package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.controller.OrderController;
import edu.ijse.cmjd.itemrent.controller.OrderDetailController;
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
import org.jdesktop.swingx.border.DropShadowBorder;

public class Schedule extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel searchButton;
    private JLabel filterButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JComboBox searchText;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel naviPanel;
    private JTable ScheduleTable;
    private JScrollPane scrollPane;
    private JPanel datePanel;
    private Map<String, JPanel> dateMap = new HashMap<>();

    public Schedule() {
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

        header = new JLabel("Schedule");
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

        searchButton = new JLabel(new ImageIcon("./src/Images/searchBut2.png"));
        searchButton.setPreferredSize(new Dimension(55, 30));
        searchButton.setOpaque(true);
        searchButton.setBackground(new Color(81, 131, 191));
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    ArrayList<OrderM> allOrders = ServerConnector.serverConnection().getOrderController().getAllOrders();
                    boolean fount = false;
                    for (OrderM orderM : allOrders) {
                        if (orderM.getrDate().equals(searchText.getEditor().getItem().toString())) {
                            final JPanel get = dateMap.get(orderM.getrDate());
                            scrollPane.getViewport().setViewPosition(new Point(get.getLocation().x, 0));
                            new Thread() {
                                @Override
                                public void run() {
                                    for (int i = 0; i < 11; i++) {
                                        if (i % 2 == 0) {
                                            get.setBorder(BorderFactory.createLineBorder(new Color(185, 205, 229), 2));
                                        } else {
                                            get.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
                                        }
                                        try {
                                            Thread.sleep(500);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }.start();
                            break;
                        }
                    }

                } catch (NotBoundException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        filterButton = new JLabel(new ImageIcon("./src/Images/filterBut.png"));

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

        naviPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JLabel searchTextLabel = new JLabel("Search : ");
        searchTextLabel.setFont(new Font("Segoe UI Light", 0, 24));

        naviPanel.add(searchTextLabel);
        naviPanel.add(searchText);
        naviPanel.add(searchButton);
        naviPanel.setBackground(new Color(255, 255, 255));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(255, 255, 255));
        setContent();

    }

    private void setContent() {

        String columnNames[] = {" ", "00.00", "01.00", "02.00", "03.00", "04.00", "05.00", "06.00", "07.00", "08.00",
            "00.09", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00",
            "18.00", "19.00", "20.00", "21.00", "22.00", "23.00"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ScheduleTable = new JTable(model);

        ScheduleTable.setAutoCreateRowSorter(true);
        ScheduleTable.setOpaque(true);
        ScheduleTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        ScheduleTable.getTableHeader().setForeground(new Color(255, 255, 255));
        ScheduleTable.getTableHeader().setBackground(new Color(113, 153, 202));
        ScheduleTable.setRowHeight(30);
        ScheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 24; i++) {
            ScheduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        datePanel = new JPanel();
        datePanel.setBackground(new Color(255, 255, 255));
        setDates();

        scrollPane = new JScrollPane(datePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(453, 250));
        System.out.println("Scroll Pane : " + scrollPane.getPreferredSize());
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));

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
                g.fillRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 12);

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

        ScheduleTable.setBackground(new Color(255, 255, 255));

        contentPanel.add(scrollPane);
        contentPanel.add(Box.createVerticalStrut(25));
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

    private void setDates() {
        /**
         * Load all order dates into the jLabels
         *
         */
        try {
            OrderController orderController = ServerConnector.serverConnection().getOrderController();
            ArrayList<OrderM> allOrders = orderController.getAllOrders();
            ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();

            datePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

            for (OrderM orderM : allOrders) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date parse = format.parse(orderM.getrDate());
                SimpleDateFormat format1 = new SimpleDateFormat("EEE/dd/MM/YYYY");
                JLabel dataLabel = new JLabel(format1.format(parse));
                dataLabel.setOpaque(true);
                dataLabel.setBackground(new Color(113, 153, 202));
                dataLabel.setForeground(new Color(255, 255, 255));
                dataLabel.setFont(new Font("Segoe UI Light", 0, 15));
                dataLabel.setPreferredSize(new Dimension(130, 40));
                dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel dataCenterLabel = new JLabel(orderM.getClient());
                dataCenterLabel.setIcon(new ImageIcon("./src/Images/idBut.png"));
                dataCenterLabel.setOpaque(true);
                dataCenterLabel.setBackground(new Color(255, 255, 255));
                dataCenterLabel.setFont(new Font("Segoe UI Light", 0, 15));
                dataCenterLabel.setPreferredSize(new Dimension(130, 40));
                dataCenterLabel.setHorizontalAlignment(SwingConstants.LEADING);

                JLabel dataBottomLabel = new JLabel(serverConnection.serachClient(orderM.getClient()).getName());
                dataBottomLabel.setIcon(new ImageIcon("./src/Images/client1But.png"));
                dataBottomLabel.setOpaque(true);
                dataBottomLabel.setBackground(new Color(255, 255, 255));
                dataBottomLabel.setFont(new Font("Segoe UI Light", 0, 15));
                dataBottomLabel.setPreferredSize(new Dimension(130, 50));
                dataBottomLabel.setHorizontalAlignment(SwingConstants.LEADING);

                final JLabel dataItemlabel = new JLabel("Items");
                dataItemlabel.setIcon(new ImageIcon("./src/Images/itemBut.png"));
                dataItemlabel.setOpaque(true);
                dataItemlabel.setBackground(new Color(255, 255, 255));
                dataItemlabel.setFont(new Font("Segoe UI Light", 0, 15));
                dataItemlabel.setPreferredSize(new Dimension(130, 50));
                dataItemlabel.setHorizontalAlignment(SwingConstants.LEADING);

                final String id = orderM.getiD();

                dataItemlabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        setPopUp(id, dataItemlabel.getLocation().x, dataItemlabel.getLocation().x, dataItemlabel);
                    }
                });

                JPanel dataPanel = new JPanel();
                dataPanel.setBackground(new Color(255, 255, 255));
                dataPanel.setPreferredSize(new Dimension(140, 210));
                dataPanel.setBorder(BorderFactory.createLineBorder(new Color(113, 153, 202), 2));
                dataPanel.add(dataLabel);
                dataPanel.add(dataCenterLabel);
                dataPanel.add(dataBottomLabel);
                dataPanel.add(dataItemlabel);

                dateMap.put(orderM.getrDate(), dataPanel);

                datePanel.add(dataPanel);
                datePanel.repaint();
            }

        } catch (ParseException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setPopUp(String id, int x, int y, JLabel parent) {

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
                    JButton jbutton = new JButton();
                    jbutton.setPreferredSize(new Dimension(0, 0));
                    jbutton.setMinimumSize(new Dimension(0, 0));
                    jbutton.setMaximumSize(new Dimension(0, 0));
                    return jbutton;
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

            menu.show(parent, x, y);

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

    public static void main(String args[]) {
        new Schedule().setVisible(true);
    }
}
