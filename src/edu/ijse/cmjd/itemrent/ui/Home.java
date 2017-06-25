package edu.ijse.cmjd.itemrent.ui;

import AppPackage.AnimationClass;
import edu.ijse.cmjd.itemrent.connection.ServerConnector;
import edu.ijse.cmjd.itemrent.controller.ItemController;
import edu.ijse.cmjd.itemrent.model.ItemM;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Home extends JFrame {

    JLabel closeButton;
    JLabel header;
    JLabel time;
    JLabel date;
    JLabel sepLabel;
    JLabel sepLabel2;
    JLabel user;
    JLabel mainMenuButton;
    JLabel newItemLabel, topItemLabel, trendItemLabel, promoteItemLabel, topClientLabel;
    JLabel menuItemClient, menuItemProduct, menuItemService, menuItemHistory, menuItemSchedule;
    JPanel topPanel;
    JPanel closePanel;
    JPanel headerPanel;
    JPanel dateTimePanel;
    JPanel menuPanel;
    JPanel newPanel;
    JPanel trendPanel;
    JPanel newItemPanel;
    JPanel topItemPanel;
    JPanel trendItemPanel;
    JPanel promoteItemPanel;
    JPanel topClientPanel;
    JPanel contentPanel;
    JLayeredPane menuLayer;
    JPanel temp;
    JPanel make;
    int val;

    public Home() {
        initializeComponents();

        setFrameLayout();
        getContentPane().setBackground(new Color(255, 255, 255));
        setSize(800, 600);
        setUndecorated(true);
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

        header = new JLabel("Home");
        header.setFont(new Font("Segoe UI Light", 0, 35));

        sepLabel = new JLabel();
        sepLabel.setOpaque(true);
        sepLabel.setBackground(new Color(185, 205, 229));
        sepLabel.setPreferredSize(new Dimension(800, 10));

        sepLabel2 = new JLabel();
        sepLabel2.setOpaque(true);
        sepLabel2.setBackground(new Color(81, 131, 191));
        sepLabel2.setPreferredSize(new Dimension(800, 6));

        user = new JLabel(new ImageIcon("./src/Images/User.png"));

        menuLayer = new JLayeredPane();

        menuPanel = new JPanel();
        setMenu();

        mainMenuButton = new JLabel(new ImageIcon("./src/Images/Menu.png"));

        date = new JLabel();
        date.setFont(new Font("Segoe UI Light", 0, 25));
        date.setVerticalAlignment(SwingConstants.BOTTOM);
        setDate();

        time = new JLabel();
        time.setFont(new Font("Segoe UI Light", 0, 30));
        time.setVerticalAlignment(SwingConstants.BOTTOM);
        setTime();

        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(header);
        headerPanel.setBackground(new Color(255, 255, 255));

        closePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closePanel.add(closeButton);
        closePanel.setBackground(new Color(255, 255, 255));

        topPanel = new JPanel(new BorderLayout());
        topPanel.add("East", closePanel);
        topPanel.add("West", headerPanel);
        topPanel.setBackground(new Color(255, 255, 255));

        dateTimePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        dateTimePanel.add(time);
        dateTimePanel.add(date);
        dateTimePanel.add(user);
        dateTimePanel.add(mainMenuButton);
        dateTimePanel.setBackground(new Color(255, 255, 255));

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        setContent();

        mainMenuButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Thread() {
                    public void run() {
                        for (int i = 0; i < 220; i = i + 4) {
                            try {
                                Thread.sleep(20);
                                Insets insets = temp.getInsets();
                                menuPanel.setVisible(true);
                                Dimension size = make.getPreferredSize();
                                make.setBounds(1 + insets.left, -3 + i + insets.top,
                                        size.width, size.height);

                            } catch (InterruptedException ex) {
                            }
                        }

                    }
                }.start();

            }
        });


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
        cons.gridy = 10;
        add(dateTimePanel, cons);

        cons.gridx = 0;
        cons.gridy = 14;
        add(sepLabel2, cons);

        cons.gridx = 0;
        cons.gridy = 20;
        cons.gridwidth = 2;
        add(contentPanel, cons);

    }

    private void setMenu() {

        /**
         * Set the main menu
         *
         */
        String menuLabels[] = {"Client", "Product", "Service", "History", "Schedule"};
        Font font = new Font("Segoe UI Light", 0, 18);

        menuItemClient = new JLabel(menuLabels[0]);
        menuItemClient.setFont(font);
        menuItemClient.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Client().setVisible(true);
            }
        });
        menuItemClient.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                menuItemClient.setForeground(new Color(113, 153, 202));
            }
        });
        menuItemClient.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                menuItemClient.setForeground(new Color(0, 0, 0));
            }
        });

        menuItemProduct = new JLabel(menuLabels[1]);
        menuItemProduct.setFont(font);
        menuItemProduct.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Item().setVisible(true);
            }
        });
        menuItemProduct.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                menuItemProduct.setForeground(new Color(113, 153, 202));
            }
        });
        menuItemProduct.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                menuItemProduct.setForeground(new Color(0, 0, 0));
            }
        });

        menuItemService = new JLabel(menuLabels[2]);
        menuItemService.setFont(font);
        menuItemService.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Order().setVisible(true);
            }
        });
        menuItemService.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                menuItemService.setForeground(new Color(113, 153, 202));
            }
        });
        menuItemService.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                menuItemService.setForeground(new Color(0, 0, 0));
            }
        });

        menuItemHistory = new JLabel(menuLabels[3]);
        menuItemHistory.setFont(font);
        menuItemHistory.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new History().setVisible(true);
            }
        });
        menuItemHistory.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                menuItemHistory.setForeground(new Color(113, 153, 202));
            }
        });
        menuItemHistory.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                menuItemHistory.setForeground(new Color(0, 0, 0));
            }
        });

        menuItemSchedule = new JLabel(menuLabels[4]);
        menuItemSchedule.setFont(font);
        menuItemSchedule.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new Schedule().setVisible(true);
            }
        });
        menuItemSchedule.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                menuItemSchedule.setForeground(new Color(113, 153, 202));
            }
        });
        menuItemSchedule.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent evt) {
                menuItemSchedule.setForeground(new Color(0, 0, 0));
            }
        });

        menuPanel.setLayout(new GridLayout(7, 1, 12, 12));
        menuPanel.setBackground(new Color(255, 255, 255));

        menuPanel.add(menuItemClient);
        menuPanel.add(menuItemProduct);
        menuPanel.add(menuItemService);
        menuPanel.add(menuItemHistory);
        menuPanel.add(menuItemSchedule);



    }

    private void setDate() {

        final SimpleDateFormat sdf = new SimpleDateFormat("EEEE   YYYY:MM:dd");
        new Thread() {
            public void run() {
                while (true) {
                    Date dt = new Date();
                    date.setText(sdf.format(dt));

                }
            }
        }.start();

    }

    private void setTime() {

        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:a");
        new Thread() {
            public void run() {
                while (true) {
                    Date dt = new Date();
                    time.setText(sdf.format(dt));

                }
            }
        }.start();
    }

    public void setContent() {

        /**
         * Initialize swing components Set the relevant listeners to the
         * JTextFields
         */
        Font font = new Font("Segoe UI Light", 0, 22);
        newItemLabel = new JLabel("What's new");
        newItemLabel.setFont(font);
        newItemPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        newItemPanel.setBackground(new Color(255, 255, 255));

        JLabel[] newItems = new JLabel[6];
        JLabel[] newItems_t = new JLabel[6];
        JPanel[] transPanels = new JPanel[6];
        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            int i = 0;
            for (ItemM itemM : allItems) {
                newItems[i] = new JLabel("<html>" + itemM.getDes() + "<br>" + itemM.getBrand() + "<html>");
                newItems[i].setForeground(new Color(255, 255, 255));
                newItems[i].setVerticalAlignment(JLabel.BOTTOM);
                newItems[i].setHorizontalAlignment(JLabel.LEADING);
                newItems[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
                newItems[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                newItems[i].setOpaque(true);
                newItems[i].setBackground(new Color(113, 153, 202));
                newItems[i].setPreferredSize(new Dimension(100, 102));

                newItems_t[i] = new JLabel("<html>" + itemM.getQty() + "<br> more <html>");
                newItems_t[i].setForeground(new Color(81, 131, 191));
                newItems_t[i].setVerticalAlignment(JLabel.CENTER);
                newItems_t[i].setHorizontalAlignment(JLabel.CENTER);
                newItems_t[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
                newItems_t[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                newItems_t[i].setOpaque(true);
                newItems_t[i].setBackground(new Color(255, 255, 255));
                newItems_t[i].setPreferredSize(new Dimension(100, 102));

                transPanels[i] = new JPanel(null);
                transPanels[i].setBackground(new Color(255, 255, 255));
                transPanels[i].setPreferredSize(new Dimension(100, 102));
                Insets ins = transPanels[i].getInsets();
                transPanels[i].add(newItems[i]);
                transPanels[i].add(newItems_t[i]);
                Dimension di = newItems[i].getPreferredSize();
                newItems[i].setBounds(0 + ins.left, 0 + ins.top, di.width, di.height);
                di = newItems_t[i].getPreferredSize();
                newItems_t[i].setBounds(100 + ins.left, 0 + ins.top, di.width, di.height);
                val = ins.left;
                moveSideToSideAnimation(newItems[i], newItems_t[i], val, 6000 + i * 1000);
                newItemPanel.add(transPanels[i]);
                newItemPanel.repaint();

                i++;
            }

        } catch (NotBoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        topItemLabel = new JLabel("Top Products");
        topItemLabel.setFont(font);
        topItemPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        topItemPanel.setBackground(new Color(255, 255, 255));

        JLabel[] topItems = new JLabel[6];
        JLabel[] topItems_t = new JLabel[6];
        JPanel[] topItems_transPanels = new JPanel[6];

        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            int i = 0;
            for (ItemM itemM : allItems) {
                topItems[i] = new JLabel("<html>" + itemM.getDes() + "<br>" + itemM.getBrand() + "<html>");
                topItems[i].setForeground(new Color(255, 255, 255));
                topItems[i].setVerticalAlignment(JLabel.BOTTOM);
                topItems[i].setHorizontalAlignment(JLabel.LEADING);
                topItems[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
                topItems[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                topItems[i].setOpaque(true);
                topItems[i].setBackground(new Color(113, 153, 202));
                topItems[i].setPreferredSize(new Dimension(100, 102));

                topItems_t[i] = new JLabel("<html>" + itemM.getQty() + "<br> more <html>");
                topItems_t[i].setForeground(new Color(81, 131, 191));
                topItems_t[i].setVerticalAlignment(JLabel.CENTER);
                topItems_t[i].setHorizontalAlignment(JLabel.CENTER);
                topItems_t[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
                topItems_t[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                topItems_t[i].setOpaque(true);
                topItems_t[i].setBackground(new Color(255, 255, 255));
                topItems_t[i].setPreferredSize(new Dimension(100, 102));

                topItems_transPanels[i] = new JPanel(null);
                topItems_transPanels[i].setBackground(new Color(255, 255, 255));
                topItems_transPanels[i].setPreferredSize(new Dimension(100, 102));
                Insets ins = topItems_transPanels[i].getInsets();
                topItems_transPanels[i].add(topItems[i]);
                topItems_transPanels[i].add(topItems_t[i]);
                Dimension di = topItems[i].getPreferredSize();
                topItems[i].setBounds(0 + ins.left, 0 + ins.top, di.width, di.height);
                di = topItems_t[i].getPreferredSize();
                topItems_t[i].setBounds(100 + ins.left, 0 + ins.top, di.width, di.height);
                val = ins.left;
                moveSideToSideAnimation(topItems[i], topItems_t[i], val, 7000 + i * 1000);
                topItemPanel.add(topItems_transPanels[i]);
                topItemPanel.repaint();

                i++;
            }

        } catch (NotBoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }


        trendItemLabel = new JLabel("Trending");
        trendItemLabel.setHorizontalAlignment(SwingConstants.LEFT);
        trendItemLabel.setFont(font);
        trendItemPanel = new JPanel(new GridLayout(6, 1, 25, 15));
        trendItemPanel.setBackground(new Color(255, 255, 255));

        JLabel[] trendItems = new JLabel[6];

        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            int i = 0;
            for (ItemM itemM : allItems) {
                trendItems[i] = new JLabel("#" + itemM.getDes());
                trendItemPanel.add(trendItems[i]);
                trendItemPanel.repaint();
            }

        } catch (NotBoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }


        JPanel trendLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        trendLabelPanel.add(trendItemLabel);
        trendLabelPanel.setBackground(new Color(255, 255, 255));

        make = new JPanel();
        make.setLayout(new FlowLayout());
        make.setBackground(new Color(255, 255, 255));
        make.add(trendLabelPanel);
        make.add(trendItemPanel);
        make.setPreferredSize(new Dimension(96, 445));

        temp = new JPanel();
        temp.setLayout(null);
        temp.setPreferredSize(new Dimension(100, 245));
        temp.setBackground(new Color(255, 255, 255));
        temp.add(make);
        temp.add(menuPanel);

        Insets insets = temp.getInsets();
        Dimension size = make.getPreferredSize();
        make.setBounds(-1 + insets.left, -10 + insets.top,
                size.width, size.height);

        size = menuPanel.getPreferredSize();
        menuPanel.setBounds(10 + insets.left, 25 + insets.top,
                size.width, size.height);


        promoteItemLabel = new JLabel("What's Promoted");
        promoteItemLabel.setFont(font);
        promoteItemPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        promoteItemPanel.setBackground(new Color(255, 255, 255));

        JLabel[] promoteItems = new JLabel[3];
        JLabel[] promoteItem_t = new JLabel[3];
        JPanel[] promoteItem_transPanels = new JPanel[3];

        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            int i = 0;
            for (ItemM itemM : allItems) {
                promoteItems[i] = new JLabel("<html>" + itemM.getDes() + "<br>" + itemM.getBrand() + "<html>");
                promoteItems[i].setForeground(new Color(255, 255, 255));
                promoteItems[i].setVerticalAlignment(JLabel.BOTTOM);
                promoteItems[i].setHorizontalAlignment(JLabel.LEADING);
                promoteItems[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
                promoteItems[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                promoteItems[i].setOpaque(true);
                promoteItems[i].setBackground(new Color(113, 153, 202));
                promoteItems[i].setPreferredSize(new Dimension(100, 102));

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                Date parse = format.parse(itemM.getSince());

                SimpleDateFormat format1 = new SimpleDateFormat("EEEEE");

                promoteItem_t[i] = new JLabel("<html>" + "Till <br> " + format1.format(parse) + "<html>");
                promoteItem_t[i].setForeground(new Color(81, 131, 191));
                promoteItem_t[i].setVerticalAlignment(JLabel.CENTER);
                promoteItem_t[i].setHorizontalAlignment(JLabel.CENTER);
                promoteItem_t[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
                promoteItem_t[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                promoteItem_t[i].setOpaque(true);
                promoteItem_t[i].setBackground(new Color(255, 255, 255));
                promoteItem_t[i].setPreferredSize(new Dimension(100, 102));

                promoteItem_transPanels[i] = new JPanel(null);
                promoteItem_transPanels[i].setBackground(new Color(255, 255, 255));
                promoteItem_transPanels[i].setPreferredSize(new Dimension(100, 102));
                Insets ins = promoteItem_transPanels[i].getInsets();
                promoteItem_transPanels[i].add(promoteItems[i]);
                promoteItem_transPanels[i].add(promoteItem_t[i]);
                Dimension di = promoteItems[i].getPreferredSize();
                promoteItems[i].setBounds(0 + ins.left, 0 + ins.top, di.width, di.height);
                di = topItems_t[i].getPreferredSize();
                promoteItem_t[i].setBounds(100 + ins.left, 0 + ins.top, di.width, di.height);
                val = ins.left;
                moveSideToSideAnimation(promoteItems[i], promoteItem_t[i], val, 8000 + i * 1000);
                promoteItemPanel.add(promoteItem_transPanels[i]);
                promoteItemPanel.repaint();

                i++;
                if (i == 3) {
                    break;
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        topClientLabel = new JLabel("Our Top Clients");
        topClientLabel.setFont(font);
        topClientPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        topClientPanel.setBackground(new Color(255, 255, 255));

        JLabel[] topClients = new JLabel[3];
        JLabel[] topClient_t = new JLabel[3];
        JPanel[] topClient_transPanels = new JPanel[3];

        try {
            ItemController itemController = ServerConnector.serverConnection().getItemController();
            ArrayList<ItemM> allItems = itemController.getAllItems();
            int i = 0;
            for (ItemM itemM : allItems) {
                topClients[i] = new JLabel("<html>" + itemM.getDes() + "<br>" + itemM.getBrand() + "<html>");
                topClients[i].setForeground(new Color(255, 255, 255));
                topClients[i].setVerticalAlignment(JLabel.BOTTOM);
                topClients[i].setHorizontalAlignment(JLabel.LEADING);
                topClients[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
                topClients[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                topClients[i].setOpaque(true);
                topClients[i].setBackground(new Color(113, 153, 202));
                topClients[i].setPreferredSize(new Dimension(100, 102));

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                Date parse = format.parse(itemM.getSince());

                SimpleDateFormat format1 = new SimpleDateFormat("EEEEE");

                topClient_t[i] = new JLabel("<html>" + "Till <br> " + format1.format(parse) + "<html>");
                topClient_t[i].setForeground(new Color(81, 131, 191));
                topClient_t[i].setVerticalAlignment(JLabel.CENTER);
                topClient_t[i].setHorizontalAlignment(JLabel.CENTER);
                topClient_t[i].setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
                topClient_t[i].setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                topClient_t[i].setOpaque(true);
                topClient_t[i].setBackground(new Color(255, 255, 255));
                topClient_t[i].setPreferredSize(new Dimension(100, 102));

                topClient_transPanels[i] = new JPanel(null);
                topClient_transPanels[i].setBackground(new Color(255, 255, 255));
                topClient_transPanels[i].setPreferredSize(new Dimension(100, 102));
                Insets ins = topClient_transPanels[i].getInsets();
                topClient_transPanels[i].add(topClients[i]);
                topClient_transPanels[i].add(topClient_t[i]);
                Dimension di = topClient_t[i].getPreferredSize();
                topClients[i].setBounds(0 + ins.left, 0 + ins.top, di.width, di.height);
                di = topClient_t[i].getPreferredSize();
                topClient_t[i].setBounds(100 + ins.left, 0 + ins.top, di.width, di.height);
                val = ins.left;
                moveSideToSideAnimation(topClients[i], topClient_t[i], val, 9000 + i * 1000);
                topClientPanel.add(topClient_transPanels[i]);
                topClientPanel.repaint();

                i++;
                if (i == 3) {
                    break;
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();

        cons.anchor = GridBagConstraints.WEST;
        cons.fill = GridBagConstraints.BOTH;
        cons.insets = new Insets(10, 10, 10, 10);

        cons.weightx = 1.0;
        cons.weighty = 1.0;

        cons.gridx = 0;
        cons.gridy = 0;
        contentPanel.add(newItemLabel, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        contentPanel.add(newItemPanel, cons);

        cons.gridx = 10;
        cons.gridy = 0;
        contentPanel.add(topItemLabel, cons);

        cons.gridx = 10;
        cons.gridy = 10;
        contentPanel.add(topItemPanel, cons);

        cons.gridx = 20;
        cons.gridy = 0;
        cons.gridheight = 40;
        contentPanel.add(temp, cons);

        cons.gridx = 0;
        cons.gridy = 20;
        cons.gridheight = 1;
        contentPanel.add(promoteItemLabel, cons);

        cons.gridx = 0;
        cons.gridy = 30;
        cons.gridheight = 1;
        contentPanel.add(promoteItemPanel, cons);

        cons.gridx = 10;
        cons.gridy = 20;
        cons.gridheight = 1;
        //cons.gridwidth = 2;
        contentPanel.add(topClientLabel, cons);

        cons.gridx = 10;
        cons.gridy = 30;
        cons.gridheight = 1;
        contentPanel.add(topClientPanel, cons);

    }

    
    private void moveSideToSideAnimation(JLabel first, JLabel second, int val, int delay) {
           
        /**
         * Animate labels of a panel
         */
        
        final JLabel f = first;
        final JLabel s = second;
        final int v = val;
        final int d = delay;
        final AnimationClass ac = new AnimationClass();

        new Thread() {
            int count;

            @Override
            public void run() {
                while (true) {

                    switch (count) {

                        case 0:
                            try {
                                Thread.sleep(d);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ac.jLabelXLeft(v + 0, -100 - v, 20, 5, f);
                            ac.jLabelXLeft(100 + v, v + 0, 20, 5, s);
                            s.setBorder(BorderFactory.createLineBorder(new Color(113, 153, 202), 2));

                            count = 1;

                            break;
                        case 1:

                            try {
                                Thread.sleep(d);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ac.jLabelXRight(v + 0, 100 + v, 20, 5, s);
                            s.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
                            ac.jLabelXRight(-100 - v, v + 0, 20, 5, f);
                            count = 0;

                            break;

                        default:

                    }

                }
            }
        }.start();
    }

    public static void main(String args[]) {
        new Home().setVisible(true);
    }
}
