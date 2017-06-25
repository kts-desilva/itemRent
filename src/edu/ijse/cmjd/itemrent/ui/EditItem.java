package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.model.ItemM;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import org.jdesktop.swingx.JXDatePicker;
import java.util.*;
import java.net.MalformedURLException;
import java.io.*;
import java.rmi.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditItem extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JComboBox sportText;
    private JComboBox brandCombo;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JLabel id;
    private JLabel des;
    private JLabel price;
    private JLabel sport;
    private JLabel brand;
    private JLabel qty;
    private JLabel result;
    private JLabel since;
    private JLabel sizeLabel;
    private JLabel length;
    private JLabel width;
    private JLabel radius;
    private JLabel weight;
    private JComboBox idText;
    private JTextField desText;
    private JTextField priceText;
    private JTextField brandText;
    private JTextField qtyText;
    private JTextField zipText;
    private JXDatePicker sinceText;
    private JTextField lengthText;
    private JTextField widthText;
    private JTextField radiusText;
    private JTextField weightText;
    private JButton editButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel detailPanel;
    private JPanel fillPanel1;
    private JPanel fillPanel2;
    private JPanel titlePanel;
    private JPanel buttonCenterPanel;
    private JPanel detailCenterPanel;
    private JPanel fillPanel3;
    private String pre = " ";
    private boolean isReserved;

    public EditItem() {
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
                    ItemController itemController = ServerConnector.serverConnection().getItemController();
                    itemController.releaseItem(pre);
                    dispose();
                } catch (NotBoundException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        header = new JLabel("Edit Item");
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
         * JTextFields Validate the text in the JTextFields
         */
        Font font = new Font("Segoe UI Light", Font.PLAIN, 18);

        id = new JLabel("ID", SwingConstants.LEFT);
        id.setFont(font);
        id.setPreferredSize(new Dimension(70, 20));

        des = new JLabel("Des", SwingConstants.LEFT);
        des.setFont(font);
        des.setPreferredSize(new Dimension(65, 20));

        price = new JLabel("Price", SwingConstants.LEFT);
        price.setFont(font);
        price.setPreferredSize(new Dimension(75, 20));

        sport = new JLabel("Sport", SwingConstants.LEFT);
        sport.setFont(font);
        sport.setPreferredSize(new Dimension(75, 30));

        brand = new JLabel("Brand", SwingConstants.LEFT);
        brand.setFont(font);
        brand.setPreferredSize(new Dimension(75, 20));

        qty = new JLabel("Qty", SwingConstants.LEFT);
        qty.setFont(font);
        qty.setPreferredSize(new Dimension(75, 20));

        since = new JLabel("Since", SwingConstants.LEFT);
        since.setFont(font);
        since.setPreferredSize(new Dimension(75, 20));

        sizeLabel = new JLabel("Size Info", SwingConstants.LEFT);
        sizeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        sizeLabel.setForeground(new Color(113, 153, 202));
        sizeLabel.setPreferredSize(new Dimension(90, 30));
        sizeLabel.setVerticalTextPosition(JLabel.TOP);

        length = new JLabel("Length", SwingConstants.LEFT);
        length.setFont(font);
        length.setPreferredSize(new Dimension(75, 20));

        width = new JLabel("Width", SwingConstants.LEFT);
        width.setFont(font);
        width.setPreferredSize(new Dimension(75, 20));

        radius = new JLabel("Radius", SwingConstants.LEFT);
        radius.setFont(font);
        radius.setPreferredSize(new Dimension(75, 20));

        weight = new JLabel("Weight", SwingConstants.LEFT);
        weight.setFont(font);
        weight.setPreferredSize(new Dimension(75, 20));


        result = new JLabel();
        result.setPreferredSize(new Dimension(20, 25));

        idText = new JComboBox();
        idText.setPreferredSize(new Dimension(200, 25));
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

        setID();


        final EditItem c = this;

        idText.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                JComboBox cb = (JComboBox) evt.getSource();
                Object item = evt.getItem();

                if (evt.getStateChange() == ItemEvent.SELECTED) {

                    try {
                        ItemController itemController = ServerConnector.serverConnection().getItemController();
                        ItemM model = itemController.serachItem(idText.getSelectedItem().toString());
                        String p = idText.getSelectedItem().toString();
                        if (!pre.equals(p)) {
                            itemController.releaseItem(pre);
                            pre = p;
                        }
                        if (itemController.reserveItem(pre)) {

                            desText.setEditable(true);
                            priceText.setEditable(true);
                            sportText.setEditable(true);
                            brandCombo.setEnabled(true);
                            qtyText.setEditable(true);
                            sinceText.setEnabled(true);
                            lengthText.setEditable(true);
                            widthText.setEditable(true);
                            radiusText.setEditable(true);
                            weightText.setEditable(true);

                            lengthText.setText(model.getLength());
                            widthText.setText(model.getWidth());
                            radiusText.setText(model.getRadius());
                            weightText.setText(model.getWeight());
                            desText.setText(model.getDes());
                            priceText.setText(model.getPrice());
                            sportText.setSelectedItem(model.getSport());
                            brandCombo.setSelectedItem(model.getBrand());
                            qtyText.setText(model.getQty());
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE dd :MM : YYYY");
                            sinceText.setDate(dateFormat.parse(model.getSince()));

                            result.setIcon(new ImageIcon("./src/Images/trueBut.png"));

                        } else {
                            result.setIcon(new ImageIcon("./src/Images/falseBut.png"));

                            desText.setEditable(false);
                            priceText.setEditable(false);
                            sportText.setEditable(false);
                            brandCombo.setEnabled(false);
                            qtyText.setEditable(false);
                            sinceText.setEnabled(false);
                            lengthText.setEditable(false);
                            widthText.setEditable(false);
                            radiusText.setEditable(false);
                            weightText.setEditable(false);

                            desText.setText("");
                            priceText.setText("");
                            qtyText.setText("");
                            lengthText.setText("");
                            widthText.setText("");
                            radiusText.setText("");
                            weightText.setText("");
                            isReserved = true;
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
                JLabel messageLabel = new JLabel("<html>This item is reserved<br><center>Please try again later !</center></html>", SwingConstants.CENTER);
                messageLabel.setOpaque(true);
                messageLabel.setFont(new Font("Segoe UI", 0, 13));
                messageLabel.setBackground(new Color(255, 247, 159));
                JPanel messagePanel = new JPanel(new FlowLayout());
                messagePanel.setBackground(new Color(255, 247, 159));


                JLabel ic = new JLabel(new ImageIcon("./src/Images/warningBut.png"));
                messagePanel.add(ic);
                messagePanel.add(messageLabel);

                popup.add(messagePanel);
                popup.setBorder(BorderFactory.createEmptyBorder());
                if (isReserved) {
                    popup.show(result, result.getX() - 250, result.getY() + 20);
                }


            }
        });


        desText = new JTextField(10);
        desText.setPreferredSize(new Dimension(10, 25));
        desText.setToolTipText("Enter des here");

        priceText = new JTextField(20);
        priceText.setPreferredSize(new Dimension(10, 25));
        priceText.setToolTipText("Enter price here");

        sportText = new JComboBox();
        sportText.setPreferredSize(new Dimension(10, 25));
        sportText.setToolTipText("Enter sport here");

        sportText.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton b = new JButton(new ImageIcon("./src/Images/filterBut.png"));
                //JButton b=new JButton();
                b.setBackground(new Color(255, 255, 255));
                b.setContentAreaFilled(false);
                b.setFocusPainted(false);
                b.setBorder(BorderFactory.createEmptyBorder());

                return b;
            }
        });

        sportText.setRenderer(new DefaultListCellRenderer() {
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

        sportText.setOpaque(false);

        sportText.setFont(new Font("Segoe UI", 0, 18));
        sportText.addItem("Business");
        sportText.addItem("Individual");
        sportText.addItem("School");
        sportText.addItem("Organization");


        brandText = new JTextField(15);
        brandText.setPreferredSize(new Dimension(10, 25));
        brandText.setToolTipText("Enter Tel.no here");

        qtyText = new JTextField(15);
        qtyText.setPreferredSize(new Dimension(10, 25));
        qtyText.setToolTipText("Enter Tel.no here");

        zipText = new JTextField(15);
        zipText.setPreferredSize(new Dimension(10, 25));
        zipText.setToolTipText("Enter Tel.no here");

        sinceText = new JXDatePicker();
        sinceText.setDate(new Date());
        sinceText.setFont(new Font("Segoe UI", 0, 13));
        sinceText.setPreferredSize(new Dimension(10, 25));
        sinceText.setToolTipText("Enter Tel.no here");

        lengthText = new JTextField(15);
        lengthText.setPreferredSize(new Dimension(10, 25));
        lengthText.setToolTipText("Enter Tel.no here");

        widthText = new JTextField(15);
        widthText.setPreferredSize(new Dimension(10, 25));
        widthText.setToolTipText("Enter Tel.no here");

        radiusText = new JTextField(15);
        radiusText.setPreferredSize(new Dimension(10, 25));
        radiusText.setToolTipText("Enter Tel.no here");

        weightText = new JTextField(15);
        weightText.setPreferredSize(new Dimension(10, 25));
        weightText.setToolTipText("Enter Tel.no here");

        brandCombo = new JComboBox();
        brandCombo.setPreferredSize(new Dimension(10, 25));
        brandCombo.setToolTipText("Select Package from here");

        brandCombo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton filterButton = new JButton(new ImageIcon("./src/Images/filterBut.png"));
                //JButton b=new JButton();
                filterButton.setBackground(new Color(255, 255, 255));
                filterButton.setContentAreaFilled(false);
                filterButton.setFocusPainted(false);
                filterButton.setBorder(BorderFactory.createEmptyBorder());

                return filterButton;
            }
        });

        brandCombo.setRenderer(new DefaultListCellRenderer() {
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

        brandCombo.setOpaque(false);

        brandCombo.setFont(new Font("Segoe UI", 0, 18));
        brandCombo.addItem("Starter");
        brandCombo.addItem("Midi User");
        brandCombo.addItem("Haevy User");
        brandCombo.addItem("Master");

        font = new Font("Segoe UI Light", Font.PLAIN, 16);

        editButton = new JButton("Add");
        editButton.setFont(font);
        editButton.setContentAreaFilled(false);
        editButton.setOpaque(true);

        editButton.setBackground(new Color(185, 205, 229));
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    ItemController itemController = ServerConnector.serverConnection().getItemController();
                    boolean editItem = itemController.editItem(new ItemM(idText.getSelectedItem().toString(), desText.getText(),
                            priceText.getText(), sportText.getSelectedItem().toString(), brandCombo.getSelectedItem().toString(),
                            qtyText.getText(), new SimpleDateFormat("dd/MM/YYYY").format(sinceText.getDate()),
                            lengthText.getText(), widthText.getText(), radiusText.getText(), weightText.getText()));

                    if (editItem) {
                        new MessagePane(null, "Saved Changes !").setVisible(true);
                    } else {
                        new MessagePane(null, "Faild Saving Changes !").setVisible(true);
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
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
                    ItemController itemController = ServerConnector.serverConnection().getItemController();
                    itemController.releaseItem(pre);
                    dispose();
                } catch (NotBoundException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
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

        Component com[] = {id, idText, des, desText, price, priceText, sport, sportText, brand, brandText};
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
        detailPanel.add(des, cons);
        cons.gridx = 30;
        cons.gridy = 0;
        detailPanel.add(desText, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        detailPanel.add(price, cons);
        cons.gridx = 10;
        cons.gridy = 10;
        detailPanel.add(priceText, cons);

        cons.gridx = 20;
        cons.gridy = 10;
        detailPanel.add(sport, cons);
        cons.gridx = 30;
        cons.gridy = 10;
        detailPanel.add(sportText, cons);

        cons.gridx = 0;
        cons.gridy = 20;
        detailPanel.add(brand, cons);
        cons.gridx = 10;
        cons.gridy = 20;
        detailPanel.add(brandCombo, cons);

        cons.gridx = 20;
        cons.gridy = 20;
        detailPanel.add(qty, cons);
        cons.gridx = 30;
        cons.gridy = 20;
        detailPanel.add(qtyText, cons);

        cons.gridx = 0;
        cons.gridy = 30;
        detailPanel.add(since, cons);
        cons.gridx = 10;
        cons.gridy = 30;
        detailPanel.add(sinceText, cons);

        JLabel lineLabel = new JLabel();
        lineLabel.setOpaque(true);
        lineLabel.setBackground(new Color(113, 153, 202));
        lineLabel.setPreferredSize(new Dimension(500, 8));
        JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linePanel.setBackground(new Color(255, 255, 255));
        linePanel.add(lineLabel);

        cons.gridx = 0;
        cons.gridy = 40;
        cons.gridwidth = 15;
        cons.gridheight = 20;
        detailPanel.add(sizeLabel, cons);

        cons.gridx = 10;
        cons.gridy = 40;
        cons.gridwidth = 30;
        cons.gridheight = 1;
        detailPanel.add(linePanel, cons);

        cons.gridx = 0;
        cons.gridy = 70;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        detailPanel.add(length, cons);
        cons.gridx = 10;
        cons.gridy = 70;
        detailPanel.add(lengthText, cons);

        cons.gridx = 20;
        cons.gridy = 70;
        detailPanel.add(width, cons);
        cons.gridx = 30;
        cons.gridy = 70;
        detailPanel.add(widthText, cons);

        cons.gridx = 0;
        cons.gridy = 80;
        detailPanel.add(radius, cons);
        cons.gridx = 10;
        cons.gridy = 80;
        detailPanel.add(radiusText, cons);

        cons.gridx = 20;
        cons.gridy = 80;
        detailPanel.add(weight, cons);
        cons.gridx = 30;
        cons.gridy = 80;
        detailPanel.add(weightText, cons);


        detailCenterPanel.add(detailPanel);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.add(editButton);
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
            ItemController controller = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = controller.getAllItems();
            System.out.println(allItems);
            for (ItemM itemM : allItems) {
                idText.addItem(itemM.getID());
            }

        } catch (NotBoundException | MalformedURLException | RemoteException | ClassNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public static void main(String args[]) {
        new EditItem().setVisible(true);
    }
}
