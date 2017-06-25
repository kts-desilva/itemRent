/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author User
 */
public class MessagePane extends JDialog {

    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel messagePanel;
    private JLabel titleLabel;
    private JLabel messageLabel;
    private JButton okBut;
    private JButton cancelBut;
    private String message;

    public MessagePane(JFrame frame, String message) {

        this.message=message;
        initializeComponents();
                
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(255, 255, 255));
        setSize(450, 180);
        DropShadowBorder b = new DropShadowBorder(Color.BLACK, 10, 0.2f, 10, true, true, true, true);
        getRootPane().setBorder(b);
        setUndecorated(true);
        setLocationRelativeTo(frame);


    }

    private void initializeComponents() {
        
        /**
         * Initialize swing components Set the relevant listeners to the buttons
         */
        
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Message Box");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(titleLabel);

        messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));

        messagePanel = new JPanel();
        messagePanel.setBackground(new Color(255, 255, 255));
        messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        messagePanel.add(messageLabel);

        cancelBut = new JButton("Cancel");
        cancelBut.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        cancelBut.setContentAreaFilled(false);
        cancelBut.setOpaque(true);
        cancelBut.setBackground(new Color(185, 205, 229));
        cancelBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        okBut = new JButton("OK");
        okBut.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
        okBut.setContentAreaFilled(false);
        okBut.setOpaque(true);
        okBut.setBackground(new Color(185, 205, 229));
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okBut);
        buttonPanel.add(cancelBut);

        mainPanel.setPreferredSize(new Dimension(350, 150));
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(messagePanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
        add(mainPanel);

    }

    public static void main(String[] args) {
        new MessagePane(null, "2pm is the best").setVisible(true);
    }
}
