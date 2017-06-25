package edu.ijse.cmjd.itemrent.ui;

import edu.ijse.cmjd.itemrent.model.HistoryM;
import edu.ijse.cmjd.itemrent.model.ClientM;
import edu.ijse.cmjd.itemrent.controller.HistoryController;
import edu.ijse.cmjd.itemrent.controller.ClientController;
import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import harsh.p.raval.lightbox.LightBox;
import edu.ijse.cmjd.itemrent.idcreator.IDCreator;
import edu.ijse.cmjd.itemrent.exception.DuplicateKeyException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import org.jdesktop.swingx.JXDatePicker;
import java.rmi.*;
import java.net.MalformedURLException;
import java.util.*;
import java.text.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddClient extends JFrame {

    private JLabel closeButton;
    private JLabel homeButton;
    private JLabel backButton;
    private JLabel header;
    private JLabel sepLabel;
    private JLabel sepLabel2;
    private JComboBox typeText;
    private JComboBox packageNameCombo;
    private JPanel headerPanel;
    private JPanel closePanel;
    private JPanel homePanel;
    private JPanel backPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JLabel id;
    private JLabel NIC;
    private JLabel name;
    private JLabel type;
    private JLabel tel;
    private JLabel address;
    private JLabel zip;
    private JLabel since;
    private JLabel packageLabel;
    private JLabel packageName;
    private JLabel fee;
    private JLabel types;
    private JLabel more;
    private JLabel charge;
    private JTextField idText;
    private JTextField NICText;
    private JTextField nameText;
    private JTextField telText;
    private JTextField addressText;
    private JTextField zipText;
    private JXDatePicker sinceText;
    private JTextField feeText;
    private JTextField typesText;
    private JTextField moreText;
    private JTextField chargeText;
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

    public AddClient() {
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
         *
         */
        closeButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
        });

        header = new JLabel("Add Client");
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

        NIC = new JLabel("NIC", SwingConstants.LEFT);
        NIC.setFont(font);
        NIC.setPreferredSize(new Dimension(65, 20));

        name = new JLabel("Name", SwingConstants.LEFT);
        name.setFont(font);
        name.setPreferredSize(new Dimension(75, 20));

        type = new JLabel("Type", SwingConstants.LEFT);
        type.setFont(font);
        type.setPreferredSize(new Dimension(75, 30));

        tel = new JLabel("Tel.no", SwingConstants.LEFT);
        tel.setFont(font);
        tel.setPreferredSize(new Dimension(75, 20));

        address = new JLabel("Address", SwingConstants.LEFT);
        address.setFont(font);
        address.setPreferredSize(new Dimension(75, 20));

        zip = new JLabel("Zip", SwingConstants.LEFT);
        zip.setFont(font);
        zip.setPreferredSize(new Dimension(75, 20));

        since = new JLabel("Since", SwingConstants.LEFT);
        since.setFont(font);
        since.setPreferredSize(new Dimension(75, 20));

        packageLabel = new JLabel("   Package Info", SwingConstants.LEFT);
        packageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        packageLabel.setPreferredSize(new Dimension(150, 30));
        packageLabel.setVerticalTextPosition(JLabel.TOP);

        packageName = new JLabel("P.Name", SwingConstants.LEFT);
        packageName.setFont(font);
        packageName.setPreferredSize(new Dimension(75, 30));

        fee = new JLabel("Fee", SwingConstants.LEFT);
        fee.setFont(font);
        fee.setPreferredSize(new Dimension(75, 20));

        types = new JLabel("Types", SwingConstants.LEFT);
        types.setFont(font);
        types.setPreferredSize(new Dimension(75, 20));

        more = new JLabel("More", SwingConstants.LEFT);
        more.setFont(font);
        more.setPreferredSize(new Dimension(75, 20));

        charge = new JLabel("Charge", SwingConstants.LEFT);
        charge.setFont(font);
        charge.setPreferredSize(new Dimension(75, 20));

        idText = new JTextField(10);
        idText.setPreferredSize(new Dimension(10, 25));
        idText.setToolTipText("Enter ID here");
        idText.setFont(new Font("Segoe UI", 0, 15));
        idText.setHorizontalAlignment(SwingConstants.CENTER);
        setIDText();

        NICText = new JTextField(10);
        NICText.setPreferredSize(new Dimension(10, 25));
        NICText.setToolTipText("Enter NIC here");
        NICText.setFont(new Font("Segoe UI", 0, 15));
        NICText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = NICText.getText().matches("^[0-9]{0,9}");

                    if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                        NICText.setHorizontalAlignment(SwingConstants.LEFT);
                    }
                    if (!matches) {
                        NICText.setText(NICText.getText().substring(0, NICText.getText().length() - 1));
                        setPopup("It should be an integer", NICText, 400, 0);
                        NICText.requestFocus(true);
                    }
                    boolean finished = NICText.getText().matches("^[0-9]{9}");
                    if (finished) {
                        NICText.setText(NICText.getText() + "V");
                    }
                }
            }
        });

        NICText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NICText.setHorizontalAlignment(SwingConstants.CENTER);
                typeText.requestFocus(true);
            }
        });

        nameText = new JTextField(20);
        nameText.setPreferredSize(new Dimension(10, 25));
        nameText.setToolTipText("Enter name here");
        nameText.setFont(new Font("Segoe UI", 0, 15));

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                nameText.requestFocus(true);
            }
        });

        nameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setHorizontalAlignment(SwingConstants.CENTER);
                NICText.requestFocus(true);
            }
        });

        nameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    nameText.setHorizontalAlignment(SwingConstants.LEFT);
                }

                if ((e.getKeyCode() != KeyEvent.VK_BACK_SPACE) && (e.getKeyCode() != KeyEvent.VK_SPACE)) {
                    boolean matches = nameText.getText().matches("^[A-za-z ]*");
                    if (!matches) {
                        nameText.setText(nameText.getText().substring(0, nameText.getText().length() - 1));
                        setPopup("It should be a letter", nameText, 0, 50);
                        nameText.requestFocus(true);
                    }
                }
            }
        });

        addressText = new JTextField(15);
        addressText.setPreferredSize(new Dimension(10, 25));
        addressText.setToolTipText("Enter address here");
        addressText.setFont(new Font("Segoe UI", 0, 15));
        addressText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addressText.setHorizontalAlignment(SwingConstants.CENTER);
                telText.requestFocus(true);
            }
        });
        addressText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    addressText.setHorizontalAlignment(SwingConstants.LEFT);
                }
            }
        });

        typeText = new JComboBox();
        typeText.setPreferredSize(new Dimension(10, 25));
        typeText.setToolTipText("Enter type here");
        typeText.setFont(new Font("Segoe UI", 0, 15));

        typeText.setUI(new BasicComboBoxUI() {
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

        typeText.setRenderer(new DefaultListCellRenderer() {
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

        typeText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    addressText.requestFocus(true);
                }
            }
        });

        typeText.setOpaque(false);

        typeText.setFont(new Font("Segoe UI", 0, 18));
        typeText.addItem("Business");
        typeText.addItem("Individual");
        typeText.addItem("School");
        typeText.addItem("Organization");


        telText = new JTextField(15);
        telText.setPreferredSize(new Dimension(10, 25));
        telText.setToolTipText("Enter Tel.no here");
        telText.setFont(new Font("Segoe UI", 0, 15));
        telText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    telText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = telText.getText().matches("^[0-9]*");
                    if (!matches) {
                        telText.setText(telText.getText().substring(0, telText.getText().length() - 1));
                        setPopup("It should be an integer", telText, 0, 100);
                        telText.requestFocus(true);
                    }

                }
            }
        });

        telText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telText.setHorizontalAlignment(SwingConstants.CENTER);
                zipText.requestFocus(true);
            }
        });



        zipText = new JTextField(15);
        zipText.setPreferredSize(new Dimension(10, 25));
        zipText.setToolTipText("Enter zip here");
        zipText.setFont(new Font("Segoe UI", 0, 15));
        zipText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    zipText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = zipText.getText().matches("^[0-9]*");
                    if (!matches) {
                        zipText.setText(zipText.getText().substring(0, zipText.getText().length() - 1));
                        setPopup("It should be an integer", zipText, 0, 150);
                        zipText.requestFocus(true);
                    }

                }
            }
        });

        zipText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zipText.setHorizontalAlignment(SwingConstants.CENTER);
                sinceText.requestFocus(true);
            }
        });

        sinceText = new JXDatePicker();
        sinceText.setDate(new Date());
        sinceText.setFont(new Font("Segoe UI", 0, 13));
        sinceText.setPreferredSize(new Dimension(10, 25));
        sinceText.setToolTipText("Enter date here");
        sinceText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packageNameCombo.requestFocus(true);
            }
        });

        feeText = new JTextField(15);
        feeText.setPreferredSize(new Dimension(10, 25));
        feeText.setToolTipText("Enter fee here");
        feeText.setFont(new Font("Segoe UI", 0, 15));
        feeText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    feeText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = feeText.getText().matches("^[0-9]*");
                    if (!matches) {
                        feeText.setText(feeText.getText().substring(0, feeText.getText().length() - 1));
                        setPopup("It should be an integer", feeText, 400, 230);
                        feeText.requestFocus(true);
                    }

                }
            }
        });

        feeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feeText.setHorizontalAlignment(SwingConstants.CENTER);
                typesText.requestFocus(true);
            }
        });

        typesText = new JTextField(15);
        typesText.setPreferredSize(new Dimension(10, 25));
        typesText.setToolTipText("Enter types here");
        typesText.setFont(new Font("Segoe UI", 0, 15));
        typesText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    typesText.setHorizontalAlignment(SwingConstants.LEADING);
                }
            }
        });
        typesText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typesText.setHorizontalAlignment(SwingConstants.CENTER);
                moreText.requestFocus(true);
            }
        });

        moreText = new JTextField(15);
        moreText.setPreferredSize(new Dimension(10, 25));
        moreText.setToolTipText("Enter more features here");
        moreText.setFont(new Font("Segoe UI", 0, 15));
        moreText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    moreText.setHorizontalAlignment(SwingConstants.LEADING);
                }
            }
        });
        moreText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moreText.setHorizontalAlignment(SwingConstants.CENTER);
                chargeText.requestFocus(true);
            }
        });

        chargeText = new JTextField(15);
        chargeText.setPreferredSize(new Dimension(10, 25));
        chargeText.setToolTipText("Enter charge here");
        chargeText.setFont(new Font("Segoe UI", 0, 15));
        chargeText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    chargeText.setHorizontalAlignment(SwingConstants.LEADING);
                }
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    boolean matches = chargeText.getText().matches("^[0-9]*");
                    if (!matches) {
                        chargeText.setText(chargeText.getText().substring(0, chargeText.getText().length() - 1));
                        setPopup("It should be an integer", chargeText, 400, 320);
                        chargeText.requestFocus(true);
                    }

                }
            }
        });
        chargeText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chargeText.setHorizontalAlignment(SwingConstants.CENTER);
                addButton.requestFocus(true);
            }
        });

        packageNameCombo = new JComboBox();
        packageNameCombo.setPreferredSize(new Dimension(10, 25));
        packageNameCombo.setToolTipText("Select Package from here");

        packageNameCombo.setUI(new BasicComboBoxUI() {
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

        packageNameCombo.setRenderer(new DefaultListCellRenderer() {
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

        packageNameCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    feeText.requestFocus(true);
                }
            }
        });

        packageNameCombo.setOpaque(false);

        packageNameCombo.setFont(new Font("Segoe UI", 0, 18));
        packageNameCombo.addItem("Starter");
        packageNameCombo.addItem("Midi User");
        packageNameCombo.addItem("Haevy User");
        packageNameCombo.addItem("Master");

        font = new Font("Segoe UI Light", Font.PLAIN, 16);

        addButton = new JButton("Add");
        addButton.setFont(font);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(true);

        final JFrame frame = this;

        addButton.setBackground(new Color(185, 205, 229));

        final AddClient ac = this;

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE dd :MM : YYYY");
                try {
                    JTextField text[] = {idText, nameText, NICText, telText, addressText, zipText, feeText, typesText, moreText, chargeText};
                    JLabel labels[] = {id, name, NIC, tel, address, zip, fee, types, more, charge};
                    boolean fill[] = new boolean[10];
                    for (int i = 0; i < 10; i++) {
                        fill[i] = !text[i].getText().equals("");
                    }
                    if (fill[0] && fill[1] && fill[2] && fill[3] && fill[4] && fill[5] && fill[6] && fill[7] && fill[8] && fill[9]) {
                        ClientController controller = ServerConnector.serverConnection().getServerConnection();
                        boolean addClient = controller.addClient(new ClientM(idText.getText(), nameText.getText(), NICText.getText(),
                                typeText.getSelectedItem().toString(), telText.getText(), addressText.getText(), zipText.getText(),
                                dateFormat.format(sinceText.getDate()), packageNameCombo.getSelectedItem().toString(),
                                Double.parseDouble(feeText.getText()), typesText.getText(), moreText.getText(),
                                Double.parseDouble(chargeText.getText())));
                        if (addClient) {
                            new MessagePane(frame, "Added Success !").setVisible(true);

                            HistoryController historyController = ServerConnector.serverConnection().getHistoryController();
                            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY");
                            SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
                            Date date = new Date();
                            historyController.addHistory(new HistoryM(formatDate.format(date), formatTime.format(date),
                                    "Lee", "Added a new Client : " + idText.getText() + " : " + nameText.getText()));
                        } else {
                            new MessagePane(frame, "Added Faild !").setVisible(true);
                        }




                    } else {
                        String txt = "<html><center> Opps ! It seems you have forgotten to fill \" ";
                        int j = 0;
                        for (int i = 0; i < 10; i++) {
                            if (!fill[i]) {
                                text[i].setBackground(new Color(211, 224, 239));
                                if (i < 9) {
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


                } catch (RemoteException ex) {
                    new MessagePane(frame, ex.getMessage()).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    new MessagePane(frame, ex.getMessage()).setVisible(true);
                } catch (NotBoundException | MalformedURLException e) {
                    new MessagePane(frame, e.getMessage()).setVisible(true);
                } catch (IOException e) {
                    new MessagePane(frame, e.getMessage()).setVisible(true);
                } catch (DuplicateKeyException ex) {
                    new MessagePane(frame, ex.getMessage()).setVisible(true);
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

        Component com[] = {id, idText, NIC, NICText, name, nameText, type, typeText, tel, telText};
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
        detailPanel.add(NIC, cons);
        cons.gridx = 30;
        cons.gridy = 0;
        detailPanel.add(NICText, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        detailPanel.add(name, cons);
        cons.gridx = 10;
        cons.gridy = 10;
        detailPanel.add(nameText, cons);

        cons.gridx = 20;
        cons.gridy = 10;
        detailPanel.add(type, cons);
        cons.gridx = 30;
        cons.gridy = 10;
        detailPanel.add(typeText, cons);

        cons.gridx = 0;
        cons.gridy = 20;
        detailPanel.add(tel, cons);
        cons.gridx = 10;
        cons.gridy = 20;
        detailPanel.add(telText, cons);

        cons.gridx = 20;
        cons.gridy = 20;
        detailPanel.add(address, cons);
        cons.gridx = 30;
        cons.gridy = 20;
        detailPanel.add(addressText, cons);

        cons.gridx = 0;
        cons.gridy = 30;
        detailPanel.add(zip, cons);
        cons.gridx = 10;
        cons.gridy = 30;
        detailPanel.add(zipText, cons);

        cons.gridx = 20;
        cons.gridy = 30;
        detailPanel.add(since, cons);
        cons.gridx = 30;
        cons.gridy = 30;
        detailPanel.add(sinceText, cons);

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        p.setBackground(new Color(255, 255, 255));
        p.add(new JLabel(new ImageIcon(("./src/Images/notiBut.png"))));
        p.add(packageLabel);

        cons.gridx = 0;
        cons.gridy = 40;
        cons.gridwidth = 25;
        cons.gridheight = 20;
        detailPanel.add(p, cons);
        cons.gridx = 25;
        cons.gridy = 40;
        cons.gridwidth = 20;
        cons.gridheight = 20;
        detailPanel.add(sepLabel, cons);

        cons.gridx = 0;
        cons.gridy = 70;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        detailPanel.add(packageName, cons);
        cons.gridx = 10;
        cons.gridy = 70;
        cons.gridwidth = 1;
        detailPanel.add(packageNameCombo, cons);

        cons.gridx = 20;
        cons.gridy = 70;
        cons.gridwidth = 1;
        detailPanel.add(fee, cons);
        cons.gridx = 30;
        cons.gridy = 70;
        detailPanel.add(feeText, cons);

        cons.gridx = 0;
        cons.gridy = 80;
        detailPanel.add(types, cons);
        cons.gridx = 10;
        cons.gridy = 80;
        detailPanel.add(typesText, cons);

        cons.gridx = 0;
        cons.gridy = 90;
        detailPanel.add(more, cons);
        cons.gridx = 10;
        cons.gridy = 90;
        detailPanel.add(moreText, cons);

        cons.gridx = 20;
        cons.gridy = 90;
        detailPanel.add(charge, cons);
        cons.gridx = 30;
        cons.gridy = 90;
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

    private void setPopup(String text, JTextField tf, int x, int y) {

        /**
         * Set the pop up window for the incorrect inputs The pop up will be
         * visible for 0.2 seconds and faded out
         *
         */
        final JPopupMenu menu = new JPopupMenu();
        JLabel popupLabel = new JLabel(text);
        popupLabel.setOpaque(true);
        popupLabel.setFont(new Font("Segoe UI", 0, 13));
        popupLabel.setBackground(new Color(255, 247, 159));
        popupLabel.setBackground(new Color(185, 205, 229));
        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.setBackground(new Color(255, 247, 159));
        menuPanel.setBackground(new Color(185, 205, 229));

        menu.setLightWeightPopupEnabled(false);

        JLabel ic = new JLabel(new ImageIcon("./src/Images/mistakeBut.png"));
        menuPanel.add(ic);
        menuPanel.add(popupLabel);
        menu.add(menuPanel);

        menu.show(tf, tf.getLocation().x - x, tf.getLocation().y - y);


        /**
         * To fade out the pop up
         *
         */
        final Window windowAncestor = SwingUtilities.getWindowAncestor(menu);
        windowAncestor.setOpacity(0.6f);

        final float opacity = 0.0f;

        new Thread() {
            @Override
            public void run() {
                for (int i = 10; i > 0; i--) {
                    try {
                        windowAncestor.setOpacity(i * 0.1f + opacity);
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AddClient.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                menu.setVisible(false);
            }
        }.start();

    }

    private void setIDText() {

        /**
         * Set the new client id ;calculated by the IDCreator class
         *
         */
        try {
            ClientController serverConnection = ServerConnector.serverConnection().getServerConnection();
            String createID = new IDCreator(serverConnection).createID();
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
        JPanel fPanel = new JPanel();
        fPanel.setBackground(new Color(255, 255, 255));
        contPanel.setBackground(new Color(255, 255, 255));
        contPanel.add(messageContent);
        contPanel.add(fPanel);

        lightBoxPanel.add(messageTopPanel, BorderLayout.NORTH);
        lightBoxPanel.add(contPanel, BorderLayout.CENTER);
        lightBoxPanel.add(southPanel, BorderLayout.SOUTH);

        messageContent.setText(txt);

        return lightBoxPanel;
    }

    public static void main(String args[]) {
        new AddClient().setVisible(true);
    }
}
