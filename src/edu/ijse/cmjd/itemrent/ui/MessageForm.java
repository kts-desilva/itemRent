/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.cmjd.itemrent.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class MessageForm extends JFrame {
    
    private JLabel closeButton;
    private JLabel header;
    private JPanel headerPanel;
    private JPanel jp;

        
    public MessageForm() {
        super("Transparent Window");


        //setting it causes the frame to be transparent .Hence both panel and frame are transparent.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        getContentPane().setLayout(new FlowLayout());


        jp = new JPanel() {
            public void paintComponent(Graphics g) {
                //super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                //Paint gp = new GradientPaint(0, 0, new Color(100, 20, 210, 105), 0, 200, new Color(80, 20, 40, 105));
                Paint gp = new GradientPaint(0, 0, new Color(113, 153, 202, 105), 0, 200, new Color(113, 153, 202, 105));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(jp);
        setContetnt();
       // setFrameLayout();

        
       /* JButton jbtn = new JButton("Enter");
        jp.add(jbtn);*/
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    private void setContetnt() {
        closeButton = new JLabel(new ImageIcon("./src/Images/closeBut.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                dispose();
            }
        });

        header = new JLabel("Wait...");
        header.setFont(new Font("Segoe UI Light ",0, 25));
        header.setOpaque(true);
        //header.setBackground(new Color(255, 255, 255));
        
        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(header);
        headerPanel.setBackground(new Color(113, 153, 202));
        
        jp.setLayout(new FlowLayout());
        jp.add(header);
        //jp.add(bur);
        jp.add(closeButton);
        jp.add(new JTextArea("kfkjkjf"));

    }

    public static void main(String[] args) {
        new MessageForm();
    }
}
