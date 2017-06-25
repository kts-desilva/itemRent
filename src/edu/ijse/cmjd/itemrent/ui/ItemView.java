package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.model.ItemM;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;

public class ItemView extends JFrame {

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
    private JPanel selectionPanel;
    private JPanel buttonPanel;
    private JPanel buttonCenterPanel;
    private JPanel fillPanel3;
    private JTable ItemViewTable;
    private JScrollPane scrollPane;
    private JTable orderItemTable;
    private JButton addButton;
    private JButton cancelButton;
    private JTextField itemNoText;
    private JTextField totalText;

    public ItemView(JTable orderItemTable, JTextField itemNoText, JTextField totalText) {
        this.orderItemTable = orderItemTable;
        this.itemNoText = itemNoText;
        this.totalText = totalText;

        initializeComponents();

        setFrameLayout();
        getContentPane().setBackground(new Color(255, 255, 255, 123));
        setUndecorated(true);
        //setOpacity(0.7f);
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

        header = new JLabel("Select Products");
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
        System.out.println(searchButton.getPreferredSize());
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
        //topPanel.add("Center",homePanel);
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

        JLabel text = new JLabel("Search : ");
        text.setFont(new Font("Segoe UI Light", 0, 24));

        naviPanel.add(text);
        naviPanel.add(searchText);
        naviPanel.add(searchButton);
        naviPanel.setBackground(new Color(255, 255, 255));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));

        fillPanel3 = new JPanel();
        fillPanel3.setBackground(new Color(255, 255, 255));

        buttonCenterPanel = new JPanel();
        buttonCenterPanel.setBackground(new Color(255, 255, 255));

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
        String columnNames[] = {"ID", "Desc", "Price", "Total", "Qty"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            boolean canEdit[] = {false, false, false, false, true};

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        };
        Object ar[] = {"001", "Lee", "Junho", "me", "li"};
        model.addRow(ar);
        model.addRow(ar);

        ItemViewTable = new JTable(model);
        setTable();

        ItemViewTable.setAutoCreateRowSorter(true);
        ItemViewTable.setOpaque(true);
        ItemViewTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        ItemViewTable.getTableHeader().setForeground(new Color(255, 255, 255));
        ItemViewTable.getTableHeader().setBackground(new Color(113, 153, 202));
        ItemViewTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 5; i++) {
            ItemViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        scrollPane = new JScrollPane(ItemViewTable);
        scrollPane.setPreferredSize(new Dimension(453, 150));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));
        ItemViewTable.setBackground(new Color(255, 255, 255));

        ItemViewTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                String toString = ItemViewTable.getValueAt(ItemViewTable.getSelectedRow(), 4).toString();
                if (toString.matches("^[0-9]*")) {
                    int tVal = Integer.parseInt(ItemViewTable.getValueAt(ItemViewTable.getSelectedRow(), 4).toString());
                    int curVal = Integer.parseInt(ItemViewTable.getValueAt(ItemViewTable.getSelectedRow(), 3).toString());
                    if (curVal < tVal) {
                        ItemViewTable.setValueAt(curVal, ItemViewTable.getSelectedRow(), 4);
                        new MessagePane(null, "Please enter a less value").setVisible(true);
                    }
                } else {
                    new MessagePane(null, "Please enter an integer value").setVisible(true);
                    ItemViewTable.setValueAt(null, ItemViewTable.getSelectedRow(), 4);
                }

            }
        });

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

        contentPanel.add(scrollPane);

        Font font = new Font("Segoe UI Light", Font.PLAIN, 16);

        addButton = new JButton("Add");
        addButton.setFont(font);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(true);

        addButton.setBackground(new Color(185, 205, 229));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DefaultTableModel model = (DefaultTableModel) orderItemTable.getModel();
                for (int i = 0; i < ItemViewTable.getRowCount(); i++) {
                    if (ItemViewTable.getValueAt(i, 4) != null) {
                        Object rowData[] = {ItemViewTable.getValueAt(i, 0),
                            ItemViewTable.getValueAt(i, 1),
                            ItemViewTable.getValueAt(i, 2),
                            ItemViewTable.getValueAt(i, 4),
                            Double.parseDouble(ItemViewTable.getValueAt(i, 2).toString())
                            * Double.parseDouble(ItemViewTable.getValueAt(i, 4).toString())};
                        model.addRow(rowData);

                        double tot = Double.parseDouble(ItemViewTable.getValueAt(i, 2).toString())
                                * Double.parseDouble(ItemViewTable.getValueAt(i, 4).toString());
                        totalText.setText(Double.toString(Double.parseDouble(totalText.getText()) + tot));
                        itemNoText.setText(Double.toString(Double.parseDouble(itemNoText.getText()) + 1));

                    }

                }
                dispose();
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

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        buttonCenterPanel.add(fillPanel3);
        buttonCenterPanel.add("Center", buttonPanel);

        contentPanel.add(buttonCenterPanel);


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
         * Set the Item table filled with data
         *
         */
        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            DefaultTableModel model = (DefaultTableModel) ItemViewTable.getModel();
            model.setRowCount(0);

            DefaultTableModel dtm = (DefaultTableModel) orderItemTable.getModel();

            for (ItemM itemM : allItems) {
                for (int i = 0; i < orderItemTable.getRowCount(); i++) {
                    String valueAt = orderItemTable.getValueAt(i, 0).toString();
                    if (itemM.getID().equals(valueAt)) {
                        int newQty = Integer.parseInt(itemM.getQty())
                                - Integer.parseInt(orderItemTable.getValueAt(i, 3).toString());
                        itemM.setQty(Integer.toString(newQty));
                    }
                }
                Object[] rowData = {itemM.getID(), itemM.getDes(), itemM.getPrice(), itemM.getQty()};
                model.addRow(rowData);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ItemView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ItemView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ItemView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
    }
}
