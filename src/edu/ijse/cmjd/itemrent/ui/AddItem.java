package edu.ijse.cmjd.itemrent.ui;

import harsh.p.raval.lightbox.LightBox;
import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.idcreator.IDCreator;
import edu.ijse.cmjd.itemrent.controller.HistoryController;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.exception.DuplicateKeyException;
import edu.ijse.cmjd.itemrent.model.HistoryM;
import edu.ijse.cmjd.itemrent.model.ItemM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import javax.swing.plaf.basic.BasicComboBoxUI;
import org.jdesktop.swingx.JXDatePicker;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddItem extends JFrame {

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
    private JLabel since;
    private JLabel sizeLabel;
    private JLabel length;
    private JLabel width;
    private JLabel radius;
    private JLabel weight;
    private JTextField idText;
    private JTextField desText;
    private JTextField priceText;
    private JTextField brandText;
    private JTextField qtyText;
    private JXDatePicker sinceText;
    private JTextField lengthText;
    private JTextField widthText;
    private JTextField radiusText;
    private JTextField weightText;
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
    private JButton lengthCMButton;
    private JButton lengthINButton;
    private JButton widthCMButton;
    private JButton widthINButton;
    private JButton radiusCMButton;
    private JButton radiusINButton;
    private JButton weightKgButton;
    private JButton weightPoButton;

    public AddItem() {
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
         * Initialize swing components
         * Set the relevant listeners to the buttons
         */
        
        closeButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
        });

        header = new JLabel("Add Item");
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
         * Initialize swing components
         * Set the relevant listeners to the JTextFields
         * Validate the text of the JTextFields
         * 
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

        idText = new JTextField(10);
        idText.setPreferredSize(new Dimension(10, 25));
        idText.setToolTipText("Enter ID here");
        idText.setFont(new Font("Segoe UI", 0, 15));
        setIDText();
        idText.setHorizontalAlignment(SwingConstants.CENTER);

        desText = new JTextField(10);
        desText.setPreferredSize(new Dimension(10, 25));
        desText.setToolTipText("Enter des here");
        desText.setFont(new Font("Segoe UI", 0, 15));

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                desText.requestFocus(true);
            }
        });

        desText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    desText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = desText.getText().matches("^[A-Za-z]*");
                    if (!matches) {
                        desText.setText(desText.getText().substring(0, desText.getText().length() - 1));
                        setPopup("It should be a letter", desText, 400, 0);
                        desText.requestFocus(true);
                    }

                }
            }
        });

        desText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desText.setHorizontalAlignment(SwingConstants.CENTER);
                priceText.requestFocus(true);
            }
        });


        priceText = new JTextField(10);
        priceText.setPreferredSize(new Dimension(10, 25));
        priceText.setToolTipText("Enter price here");
        priceText.setFont(new Font("Segoe UI", 0, 15));
        priceText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    priceText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = priceText.getText().matches("^[0-9]*");
                    if (!matches) {
                        priceText.setText(priceText.getText().substring(0, priceText.getText().length() - 1));
                        setPopup("It should be an integer", priceText, 0, 50);
                        priceText.requestFocus(true);
                    }

                }
            }
        });

        priceText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                priceText.setHorizontalAlignment(SwingConstants.CENTER);
                sportText.requestFocus(true);
            }
        });

        brandText = new JTextField(10);
        brandText.setPreferredSize(new Dimension(10, 25));
        brandText.setToolTipText("Enter Tel.no here");
        brandText.setFont(new Font("Segoe UI", 0, 15));

        qtyText = new JTextField(10);
        qtyText.setPreferredSize(new Dimension(10, 25));
        qtyText.setToolTipText("Enter Tel.no here");
        qtyText.setFont(new Font("Segoe UI", 0, 15));
        qtyText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    qtyText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = qtyText.getText().matches("^[0-9]*");
                    if (!matches) {
                        qtyText.setText(qtyText.getText().substring(0, qtyText.getText().length() - 1));
                        setPopup("It should be an integer", qtyText, 400, 100);
                        qtyText.requestFocus(true);
                    }

                }
            }
        });
        qtyText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qtyText.setHorizontalAlignment(SwingConstants.CENTER);
                brandCombo.requestFocus(true);
            }
        });



        sportText = new JComboBox();
        sportText.setPreferredSize(new Dimension(10, 25));
        sportText.setToolTipText("Enter sport here");

        sportText.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {

                JButton b = new JButton(new ImageIcon("./src/Images/filterBut.png"));
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

        sportText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    qtyText.requestFocus(true);
                }
            }
        });

        sinceText = new JXDatePicker();
        sinceText.setDate(new Date());
        sinceText.setFont(new Font("Segoe UI", 0, 13));
        sinceText.setPreferredSize(new Dimension(10, 25));
        sinceText.setToolTipText("Enter Tel.no here");
        sinceText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lengthText.requestFocus(true);
            }
        });

        lengthText = new JTextField(5);
        lengthText.setPreferredSize(new Dimension(10, 25));
        lengthText.setToolTipText("Enter Tel.no here");
        lengthText.setFont(new Font("Segoe UI", 0, 15));
        lengthText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    lengthText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = lengthText.getText().matches("^[0-9]*");
                    if (!matches) {
                        lengthText.setText(lengthText.getText().substring(0, lengthText.getText().length() - 1));
                        setPopup("It should be an integer", lengthText, 0, 40);
                        lengthText.requestFocus(true);
                    }

                }
            }
        });
        lengthText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lengthText.setHorizontalAlignment(SwingConstants.CENTER);
                widthText.requestFocus(true);
            }
        });
        System.out.println("lengthText : " + lengthText.getPreferredSize());


        widthText = new JTextField(5);
        widthText.setPreferredSize(new Dimension(10, 25));
        widthText.setToolTipText("Enter Tel.no here");
        widthText.setFont(new Font("Segoe UI", 0, 15));
        widthText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    widthText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = widthText.getText().matches("^[0-9]*");
                    if (!matches) {
                        widthText.setText(widthText.getText().substring(0, widthText.getText().length() - 1));
                        setPopup("It should be an integer", widthText, 0, 50);
                        widthText.requestFocus(true);
                    }

                }
            }
        });
        widthText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                widthText.setHorizontalAlignment(SwingConstants.CENTER);
                radiusText.requestFocus(true);
            }
        });


        radiusText = new JTextField(5);
        radiusText.setPreferredSize(new Dimension(10, 25));
        radiusText.setToolTipText("Enter Tel.no here");
        radiusText.setFont(new Font("Segoe UI", 0, 15));
        radiusText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    radiusText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = radiusText.getText().matches("^[0-9]*");
                    if (!matches) {
                        radiusText.setText(radiusText.getText().substring(0, radiusText.getText().length() - 1));
                        setPopup("It should be an integer", radiusText, 0, 50);
                        radiusText.requestFocus(true);
                    }

                }
            }
        });
        radiusText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radiusText.setHorizontalAlignment(SwingConstants.CENTER);
                weightText.requestFocus(true);
            }
        });


        weightText = new JTextField(5);
        weightText.setPreferredSize(new Dimension(10, 25));
        weightText.setToolTipText("Enter Tel.no here");
        weightText.setFont(new Font("Segoe UI", 0, 15));
        weightText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    weightText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = weightText.getText().matches("^[0-9]*");
                    if (!matches) {
                        weightText.setText(weightText.getText().substring(0, weightText.getText().length() - 1));
                        setPopup("It should be an integer", weightText, 0, 50);
                        weightText.requestFocus(true);
                    }

                }
            }
        });
        weightText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightText.setHorizontalAlignment(SwingConstants.CENTER);
                addButton.requestFocus(true);
            }
        });



        brandCombo = new JComboBox();
        brandCombo.setPreferredSize(new Dimension(10, 25));
        brandCombo.setToolTipText("Select Package from here");

        brandCombo.setUI(new BasicComboBoxUI() {
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

        brandCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    sinceText.requestFocus(true);
                }
            }
        });

        brandCombo.setOpaque(false);

        brandCombo.setFont(new Font("Segoe UI", 0, 18));
        brandCombo.addItem("Starter");
        brandCombo.addItem("Midi User");
        brandCombo.addItem("Haevy User");
        brandCombo.addItem("Master");

        font = new Font("Segoe UI Light", Font.PLAIN, 16);

        addButton = new JButton("Add");
        addButton.setFont(font);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(true);

        addButton.setBackground(new Color(185, 205, 229));

        final JFrame frame = this;
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {

                    JTextField text[] = {idText, desText, priceText, qtyText, lengthText, widthText, radiusText, weightText};
                    JLabel labels[] = {id, des, price, qty, length, width, radius, weight};
                    boolean fill[] = new boolean[10];
                    for (int i = 0; i < 8; i++) {
                        fill[i] = !text[i].getText().equals("");
                    }
                    if (fill[0] && fill[1] && fill[2] && fill[3] && fill[4] && fill[5] && fill[6] && fill[7]) {
                        ItemController itemController = ServerConnector.serverConnection().getItemController();
                        boolean addItem = itemController.addItem(new ItemM(idText.getText(), desText.getText(),
                                priceText.getText(), sportText.getSelectedItem().toString(), brandCombo.getSelectedItem().toString(),
                                qtyText.getText(), new SimpleDateFormat("dd/MM/YYYY").format(sinceText.getDate()),
                                lengthText.getText(), widthText.getText(), radiusText.getText(), weightText.getText()));
                        if (addItem) {
                            new MessagePane(null, "Added Sucess !").setVisible(true);

                            HistoryController historyController = ServerConnector.serverConnection().getHistoryController();
                            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
                            SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
                            Date date = new Date();
                            historyController.addHistory(new HistoryM(formatDate.format(date), formatTime.format(date),
                                    "Lee", "Added a new Item : " + idText.getText() + " : " + desText.getText()));

                        } else {
                            new MessagePane(null, "Added Faild !").setVisible(true);
                        }
                    } else {
                        String txt = "<html><center> Opps ! It seems you have forgotten to fill \" ";
                        int j = 0;
                        for (int i = 0; i < 8; i++) {
                            if (!fill[i]) {
                                text[i].setBackground(new Color(211, 224, 239));
                                if (i < 7) {
                                    txt += labels[i].getText() + " , ";
                                } else {
                                    txt += labels[i].getText() + "  ";
                                }

                                j++;
                                if (j == 6) {
                                    txt += "<br>";
                                }
                            }
                        }
                        txt += "\" text fields.  Please make sure to get them done .</center></html>";

                        LightBox box = new LightBox();
                        box.createLightBoxEffect(frame, setLightBoxMessage(txt));

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

        lengthCMButton = new JButton("cm");
        lengthCMButton.setFont(font);
        lengthCMButton.setContentAreaFilled(false);
        lengthCMButton.setOpaque(true);
        lengthCMButton.setBackground(new Color(113, 153, 202));
        lengthCMButton.setForeground(new Color(255, 255, 255));

        lengthCMButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                lengthINButton.setForeground(new Color(81, 131, 191));
                lengthINButton.setBackground(new Color(255, 255, 255));

                lengthCMButton.setBackground(new Color(113, 153, 202));
                lengthCMButton.setForeground(new Color(255, 255, 255));


            }
        });

        lengthINButton = new JButton("inch");
        lengthINButton.setFont(font);
        lengthINButton.setContentAreaFilled(false);
        lengthINButton.setOpaque(true);
        lengthINButton.setBackground(new Color(255, 255, 255));
        lengthINButton.setForeground(new Color(81, 131, 191));

        lengthINButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                lengthCMButton.setForeground(new Color(81, 131, 191));
                lengthCMButton.setBackground(new Color(255, 255, 255));

                lengthINButton.setBackground(new Color(113, 153, 202));
                lengthINButton.setForeground(new Color(255, 255, 255));

            }
        });

        widthCMButton = new JButton("cm");
        widthCMButton.setFont(font);
        widthCMButton.setContentAreaFilled(false);
        widthCMButton.setOpaque(true);
        widthCMButton.setBackground(new Color(113, 153, 202));
        widthCMButton.setForeground(new Color(255, 255, 255));

        widthCMButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                widthINButton.setForeground(new Color(81, 131, 191));
                widthINButton.setBackground(new Color(255, 255, 255));

                widthCMButton.setBackground(new Color(113, 153, 202));
                widthCMButton.setForeground(new Color(255, 255, 255));


            }
        });

        widthINButton = new JButton("inch");
        widthINButton.setFont(font);
        widthINButton.setContentAreaFilled(false);
        widthINButton.setOpaque(true);
        widthINButton.setBackground(new Color(255, 255, 255));
        widthINButton.setForeground(new Color(81, 131, 191));

        widthINButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                widthCMButton.setForeground(new Color(81, 131, 191));
                widthCMButton.setBackground(new Color(255, 255, 255));

                widthINButton.setBackground(new Color(113, 153, 202));
                widthINButton.setForeground(new Color(255, 255, 255));

            }
        });


        radiusCMButton = new JButton("cm");
        radiusCMButton.setFont(font);
        radiusCMButton.setContentAreaFilled(false);
        radiusCMButton.setOpaque(true);
        radiusCMButton.setBackground(new Color(113, 153, 202));
        radiusCMButton.setForeground(new Color(255, 255, 255));

        radiusCMButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radiusINButton.setForeground(new Color(81, 131, 191));
                radiusINButton.setBackground(new Color(255, 255, 255));

                radiusCMButton.setBackground(new Color(113, 153, 202));
                radiusCMButton.setForeground(new Color(255, 255, 255));


            }
        });

        radiusINButton = new JButton("inch");
        radiusINButton.setFont(font);
        radiusINButton.setContentAreaFilled(false);
        radiusINButton.setOpaque(true);
        radiusINButton.setBackground(new Color(255, 255, 255));
        radiusINButton.setForeground(new Color(81, 131, 191));

        radiusINButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                radiusCMButton.setForeground(new Color(81, 131, 191));
                radiusCMButton.setBackground(new Color(255, 255, 255));

                radiusINButton.setBackground(new Color(113, 153, 202));
                radiusINButton.setForeground(new Color(255, 255, 255));

            }
        });

        weightKgButton = new JButton("kg");
        weightKgButton.setFont(font);
        weightKgButton.setContentAreaFilled(false);
        weightKgButton.setOpaque(true);
        weightKgButton.setBackground(new Color(113, 153, 202));
        weightKgButton.setForeground(new Color(255, 255, 255));

        weightKgButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                weightPoButton.setForeground(new Color(81, 131, 191));
                weightPoButton.setBackground(new Color(255, 255, 255));

                weightKgButton.setBackground(new Color(113, 153, 202));
                weightKgButton.setForeground(new Color(255, 255, 255));


            }
        });

        weightPoButton = new JButton("Pods");
        weightPoButton.setFont(font);
        weightPoButton.setContentAreaFilled(false);
        weightPoButton.setOpaque(true);
        weightPoButton.setBackground(new Color(255, 255, 255));
        weightPoButton.setForeground(new Color(81, 131, 191));

        weightPoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                weightKgButton.setForeground(new Color(81, 131, 191));
                weightKgButton.setBackground(new Color(255, 255, 255));

                weightPoButton.setBackground(new Color(113, 153, 202));
                weightPoButton.setForeground(new Color(255, 255, 255));
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

        cons.gridx = 0;
        cons.gridy = 0;
        detailPanel.add(id, cons);
        cons.gridx = 10;
        cons.gridy = 0;
        detailPanel.add(idText, cons);

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

        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
        lengthPanel.setBackground(new Color(255, 255, 255));
        lengthPanel.add(lengthText);
        lengthPanel.add(lengthCMButton);
        lengthPanel.add(lengthINButton);
        lengthPanel.setPreferredSize(new Dimension(70, 32));

        cons.gridx = 10;
        cons.gridy = 70;
        detailPanel.add(lengthPanel, cons);

        cons.gridx = 20;
        cons.gridy = 70;
        detailPanel.add(width, cons);

        JPanel widthPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
        widthPanel.setBackground(new Color(255, 255, 255));
        widthPanel.add(widthText);
        widthPanel.add(widthCMButton);
        widthPanel.add(widthINButton);

        cons.gridx = 30;
        cons.gridy = 70;
        detailPanel.add(widthPanel, cons);

        cons.gridx = 0;
        cons.gridy = 80;
        detailPanel.add(radius, cons);

        JPanel radiusPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
        radiusPanel.setBackground(new Color(255, 255, 255));
        radiusPanel.add(radiusText);
        radiusPanel.add(radiusCMButton);
        radiusPanel.add(radiusINButton);

        cons.gridx = 10;
        cons.gridy = 80;
        detailPanel.add(radiusPanel, cons);

        cons.gridx = 20;
        cons.gridy = 80;
        detailPanel.add(weight, cons);

        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));
        weightPanel.setBackground(new Color(255, 255, 255));
        weightPanel.add(weightText);
        weightPanel.add(weightKgButton);
        weightPanel.add(weightPoButton);

        cons.gridx = 30;
        cons.gridy = 80;
        detailPanel.add(weightPanel, cons);


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
        cons.gridy = 5;
        add(sepLabel2, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        add(contentPanel, cons);

    }

    private void setPopup(String text, JTextField tf, int x, int y) {
        
         /**
         * Set the pop up window for the incorrect inputs
         * The pop up will be visible for 0.2 seconds and faded out
         * 
         */

        final JPopupMenu menu = new JPopupMenu();
        JLabel textLabel = new JLabel(text);
        textLabel.setOpaque(true);
        textLabel.setFont(new Font("Segoe UI", 0, 13));
        textLabel.setBackground(new Color(185, 205, 229));
        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.setBackground(new Color(185, 205, 229));

        menu.setLightWeightPopupEnabled(false);

        JLabel icon = new JLabel(new ImageIcon("./src/Images/mistakeBut.png"));
        menuPanel.add(icon);
        menuPanel.add(textLabel);
        menu.add(menuPanel);

        menu.show(tf, tf.getLocation().x - x, tf.getLocation().y - y);

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
                        Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                menu.setVisible(false);
            }
        }.start();

    }

    private void setIDText() {

         /**
         * Set the new item id ;calculated by the IDCreator class
         * 
         */
        
        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            String createID = new IDCreator(itemController).createID();
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

    private JPanel setLightBoxMessage(String txt) {
        
         /**
         * Set the message box for invalid or incomplete contents of the frame 
         * Shows the incomplete fields in order to make them filled
         * Uses the light box effect which turns the current frame into black 
         * and the required message box will be appeared
         * 
         */
        
        final JFrame parentFrame = this;
        final JPanel lightBoxPanel = new JPanel();
        lightBoxPanel.setBorder(BorderFactory.createLineBorder(new Color(113, 153, 202), 6));
        lightBoxPanel.setSize(700, 150);
        lightBoxPanel.setBackground(new Color(255, 255, 255));
        lightBoxPanel.setLayout(new BorderLayout());

        JLabel messageCloseButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        messageCloseButton.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        messageCloseButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                LightBox lightBox = new LightBox();
                lightBox.closeLightBox(parentFrame, lightBoxPanel);
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

        JPanel messsagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        messsagePanel.add(messageHeader);
        messsagePanel.add(messageWarningButton);
        messsagePanel.setBackground(new Color(255, 255, 255));

        JPanel messageTopPanel = new JPanel(new BorderLayout());
        messageTopPanel.setBackground(new Color(255, 255, 255));
        messageTopPanel.add(messageCloseButton, BorderLayout.EAST);
        messageTopPanel.add(messsagePanel, BorderLayout.WEST);

        JPanel southPanel = new JPanel();
        southPanel.setSize(new Dimension(650, 10));
        southPanel.setBackground(new Color(255, 255, 255));

        JPanel contPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        JPanel fillPanel = new JPanel();
        fillPanel.setBackground(new Color(255, 255, 255));
        contPanel.setBackground(new Color(255, 255, 255));
        contPanel.add(messageContent);
        contPanel.add(fillPanel);

        lightBoxPanel.add(messageTopPanel, BorderLayout.NORTH);
        lightBoxPanel.add(contPanel, BorderLayout.CENTER);
        lightBoxPanel.add(southPanel, BorderLayout.SOUTH);

        messageContent.setText(txt);

        return lightBoxPanel;
    }

    public static void main(String args[]) {
        new AddItem().setVisible(true);
    }
}
