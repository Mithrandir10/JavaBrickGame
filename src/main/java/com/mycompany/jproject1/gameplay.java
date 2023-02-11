/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jproject1;


import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 * @author spart
 */
public class gameplay extends JPanel implements KeyListener, ActionListener{
    
    private boolean play=false;
    private int score =0;
    private int totalbricks=21;
    private Timer Timer;
    private int delay=8;
    private int playerX=310;
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    
    private MapGenerator map;
    
    public gameplay(){
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        
        Timer = new Timer(delay,this);
        Timer.start();
    }
    
    public void paint(Graphics g){
      g.setColor(Color.black);
      g.fillRect(1,1,692,592);
      
      map.draw((Graphics2D) g);
      
      g.setColor(Color.lightGray);
      g.fillRect(0,0, 3, 592);
      g.fillRect(0,0, 692, 3);
      g.fillRect(691,0, 3, 592);
      
     
      g.setColor(Color.WHITE);
      g.setFont(new Font("Verdana", Font.BOLD, 25));
      g.drawString(" " + score,590, 20);
      
      g.setColor(Color.YELLOW);
      g.fillRect(playerX, 550, 100, 8);
      
      g.setColor(Color.GREEN);
      g.fillOval(ballposX, ballposY, 20, 20);
      
      
      if(ballposY > 570){
          play=false;
          
          g.setColor(Color.RED);
          g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("    Game Over Score: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
          
      }
      
      if(totalbricks == 0){
            play = false;
            ballYdir = 0;
            ballXdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    Game Over: "+score,190,300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);


        }
      
      g.dispose();
      
      
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
          Timer.start();
          
          if(play){
              //ball strikes the player bar
              if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
              ballYdir=-ballYdir;
              
              }
              
              //ball strikes the brick
              A:
              for(int i=0;i<map.map.length;i++){
                 for(int j=0;j<map.map[0].length;j++){
                   int brickX=j*map.bricksWidth + 80;
                   int brickY=i*map.bricksHeight + 50;
                   int brickWidth=map.bricksWidth;
                   int brickHeight=map.bricksHeight;
                   
                   Rectangle brickrect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                   Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
                   //if the ball strikes the brick and the brick hasn't been hit before
                   if(ballrect.intersects(brickrect) && map.map[i][j]!=0){
                       map.setBricksValue(0, i, j);
                      totalbricks--;
                      score+=5;
                    
                      if(ballposX + 19 <=brickrect.x || ballposX +1 >=brickrect.x + brickWidth){
                      ballXdir=-ballXdir;
                      }else{
                              ballYdir=-ballYdir;
                      }
                      
                      break A;
                   }
                 
                 }
              
              }
              
              ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
          
          }
    
          repaint();
    }
    
        @Override
    public void keyTyped(KeyEvent e) {

       }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalbricks = 21;
                map = new MapGenerator(3, 7);

                repaint();
            }
        }


        }
    
     public void moveRight ()
        {
            play = true;
            playerX += 20;
        }
        public void moveLeft ()
        {
            play = true;
            playerX -= 20;
        }
    
    
}
