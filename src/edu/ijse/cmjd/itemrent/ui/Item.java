package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.model.ItemM;
import edu.ijse.cmjd.itemrent.observer.ItemObserver;
import edu.ijse.cmjd.itemrent.observer.impl.ItemObserverImpl;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;

public class Item extends JFrame {

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
    private JTable ItemTable;
    private JScrollPane scrollPane;
    private ItemObserver itemObserver;
    private String id[];
    private String desc[];
    private String brand[];
    private String sport[];

    public Item() {
        initializeComponents();
        try {
            itemObserver = new ItemObserverImpl(this);
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            itemController.addItemObserver(itemObserver);
        } catch (NotBoundException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
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
         * Initialize swing components
         * Set the relevant listeners to the buttons
         */

        closeButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
        });

        header = new JLabel("Product");
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
                new AddItem().setVisible(true);
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
                new DeleteItem().setVisible(true);
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

        editButton = new JLabel("Edit", new ImageIcon("./src/Images/editBut.png"), 0);
        editButton.setFont(new Font("Segoe UI Light", 0, 20));
        editButton.setHorizontalTextPosition(JLabel.CENTER);
        editButton.setVerticalTextPosition(JLabel.BOTTOM);
        editButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new EditItem().setVisible(true);
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
                setFont(new Font("Segoe UI", 0, 15));
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
                            case "ID":
                                for (int i = 0; i < id.length; i++) {
                                    if (text.length() <= id[i].length()) {
                                        if (text.equals(id[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(id[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (id[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(id[i]);
                                            }
                                        }

                                    } else {
                                    }
                                    searchText.showPopup();
                                }
                                break;

                            case "Desc":
                                for (int i = 0; i < desc.length; i++) {
                                    if (text.length() <= desc[i].length()) {
                                        if (text.equals(desc[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(desc[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (desc[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(desc[i]);
                                            }
                                        }

                                    } else {
                                    }
                                    searchText.showPopup();
                                }
                                break;
                            case "Brand":
                                for (int i = 0; i < brand.length; i++) {
                                    if (text.length() <= brand[i].length()) {
                                        if (text.equals(brand[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(brand[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (brand[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(brand[i]);
                                            }
                                        }

                                    } else {
                                    }
                                    searchText.showPopup();
                                }
                                break;
                            case "Sport":
                                for (int i = 0; i < sport.length; i++) {
                                    if (text.length() <= sport[i].length()) {
                                        if (text.equals(sport[i].substring(0, text.length()))) {
                                            if (searchText.getItemCount() == 0) {
                                                searchText.addItem(sport[i]);
                                            }
                                            boolean has = false;
                                            for (int j = 0; j < searchText.getItemCount(); j++) {
                                                if (sport[i].equals(searchText.getItemAt(j).toString())) {
                                                    has = true;
                                                    break;
                                                }
                                            }
                                            if (!has) {
                                                searchText.addItem(sport[i]);
                                            }
                                        }

                                    } else {
                                    }
                                    searchText.showPopup();
                                }
                                break;
                            default:
                                new MessagePane(null,"No such Item found !").setVisible(true);

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
        criteriaText.addItem("ID");
        criteriaText.addItem("Desc");
        criteriaText.addItem("Brand");
        criteriaText.addItem("Sport");


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
         * Initialize swing components
         * Set the relevant listeners to the JTextFields
         * Validate the text in the JTextFields
         */

        String columnNames[] = {"ID", "Desc", "Price", "Sport", "Brand", "Qty", "Since", "Length", "Width", "Radius", "Weight"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ItemTable = new JTable(model) {
            private static final long serialVersionUID = 1L;
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        setTable();

        ItemTable.setAutoCreateRowSorter(true);
        ItemTable.setOpaque(true);
        ItemTable.getTableHeader().setFont(new Font("Segoe UI Light", 0, 16));
        ItemTable.getTableHeader().setForeground(new Color(255, 255, 255));
        ItemTable.getTableHeader().setBackground(new Color(113, 153, 202));
        ItemTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 11; i++) {
            ItemTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        scrollPane = new JScrollPane(ItemTable);
        scrollPane.setPreferredSize(new Dimension(453, 250));
        scrollPane.getViewport().setBackground(new Color(255, 255, 255));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(255, 255, 255));
        ItemTable.setBackground(new Color(255, 255, 255));

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
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();
            model.setRowCount(0);
            for (ItemM itemM : allItems) {
                Object[] rowData = {itemM.getID(), itemM.getDes(), itemM.getPrice(), itemM.getSport(),
                    itemM.getBrand(), itemM.getQty(), itemM.getSince(), itemM.getLength(), itemM.getWidth(),
                    itemM.getRadius(), itemM.getWeight()
                };
                model.addRow(rowData);


            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMessage(String message) {
        //JOptionPane.showMessageDialog(this, message);
        new MessagePane(this,message).setVisible(true);
        setTable();
    }

    private void setData() throws NotBoundException, MalformedURLException, RemoteException, ClassNotFoundException, IOException {
        ItemController itemController = ServerConnector.serverConnection().getItemController();
        ArrayList<ItemM> allItems = itemController.getAllItems();

        id = new String[allItems.size()];
        desc = new String[allItems.size()];
        brand = new String[allItems.size()];
        sport = new String[allItems.size()];


        int i = 0;
        for (ItemM itemM : allItems) {
            id[i] = itemM.getID();
            desc[i] = itemM.getDes();
            brand[i] = itemM.getBrand();
            sport[i] = itemM.getSport();
            i++;
        }
    }

    private void setTableUsingCriteria(String criteria, String element) throws NotBoundException,
            MalformedURLException, RemoteException, ClassNotFoundException, IOException {

        ItemController itemController = ServerConnector.serverConnection().getItemController();
        DefaultTableModel model = (DefaultTableModel) ItemTable.getModel();
        model.setRowCount(0);
///{"ID", "Desc", "Price", "Sport", "Brand", "Qty", "Since", "Size", "Weight"};
        switch (criteria) {
            case "ID":
                ItemM serachItem = itemController.serachItem(element);
                Object rowData[] = {serachItem.getID(), serachItem.getDes(), serachItem.getPrice(),
                    serachItem.getSport(), serachItem.getBrand(), serachItem.getQty(),
                    serachItem.getSince(), serachItem.getLength(), serachItem.getWidth(),
                    serachItem.getRadius(), serachItem.getWeight()
                };
                model.addRow(rowData);
                break;

            case "Desc":
                ArrayList<ItemM> itemFilteredDesc = itemController.getItemFilteredDesc(element);
                for (ItemM it : itemFilteredDesc) {
                    Object rowData1[] = {it.getID(), it.getDes(), it.getPrice(),
                        it.getSport(), it.getBrand(), it.getQty(),
                        it.getSince(), it.getLength(), it.getWidth(),
                        it.getRadius(), it.getWeight()
                    };
                    model.addRow(rowData1);
                }
                break;

            case "Brand":
                ArrayList<ItemM> itemFilteredBrand = itemController.getItemFilteredBrand(element);
                for (ItemM it : itemFilteredBrand) {
                    Object rowData1[] = {it.getID(), it.getDes(), it.getPrice(),
                        it.getSport(), it.getBrand(), it.getQty(),
                        it.getSince(), it.getLength(), it.getWidth(),
                        it.getRadius(), it.getWeight()
                    };
                    model.addRow(rowData1);
                }
                break;
                
            case "Sport":
                ArrayList<ItemM> itemFilteredSport = itemController.getItemFilteredSport(element);
                for (ItemM it : itemFilteredSport) {
                    Object rowData1[] = {it.getID(), it.getDes(), it.getPrice(),
                        it.getSport(), it.getBrand(), it.getQty(),
                        it.getSince(), it.getLength(), it.getWidth(),
                        it.getRadius(), it.getWeight()
                    };
                    model.addRow(rowData1);
                }
                break;
                
            case "All":
                setTable();
                break;
                
            default :

        }
    }

    public static void main(String args[]) {
        new Item().setVisible(true);
    }
}
