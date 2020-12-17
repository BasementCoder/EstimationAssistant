package com.garagonic.estimationAssistant.old;

import javax.swing.*;
import java.awt.*;

public class Gui {

    public static void main (String[] args){

        JFrame frame =new JFrame();

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        frame.getContentPane().add(panel);

        JButton button = new JButton("Racking Space Registration");
     //   button.setSize(new Dimension(100,100));
        panel.add(button);
        JTextField textField= new JTextField("Type here or select");
        textField.setPreferredSize(new Dimension(200,100));
        panel.add(textField);

        JButton button2 = new JButton("Is it big enough?");
        panel.add(button2);



        frame.setSize(new Dimension(500,400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Booking in New Location");
        frame.setResizable(false);
        frame.setVisible(true);







    }

}
