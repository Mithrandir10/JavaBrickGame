/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.jproject1;

/**
 *
 * @author spart
 */
import javax.swing.*;

public class Jproject1 {

    public static void main(String[] args) {
        
        JFrame obj=new JFrame();
        gameplay g1=new gameplay();
        //JButton s1=new JButton("Start");
        //s1.setBounds(100,100,100,40);
        //obj.add(s1);
        System.out.println("Hello World!");
        obj.setBounds(10,10,800,600);
        obj.setTitle("Brick Breaker");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(g1);
    }
}
