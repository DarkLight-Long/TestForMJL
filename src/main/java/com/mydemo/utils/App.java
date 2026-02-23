package com.mydemo.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SWing 简单示例
 */
public class App  {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World");
        frame.setLocation(300, 300);
        frame.setMinimumSize(new Dimension(800, 600));

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setIconImage(new ImageIcon(App.class.getResource("/public/snow&loading/w10.png")).getImage());


        JLabel label = new JLabel();
        label.setText("23333");
        label.setSize(200, 200);
        label.setBackground(Color.magenta);
        frame.setGlassPane(label);

        JMenuBar menubar = new JMenuBar();

        JMenu menu = new JMenu("File");
        menubar.add(menu);
        menubar.setVisible(false);
        frame.setJMenuBar(menubar);


        JButton button = new JButton("Click Me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(true);
                JOptionPane.showMessageDialog(frame, "Click Me");
            }
        });
        button.setVisible(true);

        frame.setContentPane(new JPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(button);

        frame.setVisible(true);
    }
}
