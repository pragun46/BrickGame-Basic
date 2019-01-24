/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
//import java.util.Timer;

/**
 *
 * @author pragu
 */
public class Game extends JPanel implements ActionListener,KeyListener {
    
    private boolean start1=false;
    private int score=0;
    public static int total=21;
    private Timer time;
    private final int slow=7;
    private int player=310;
    private int ballx=270;
    private int bally=350;
    private int x=-1;
    private int y=-2;
    private Bricks bricks;
    public  int highscore=0;
    boolean check=false;
    
    public Game(){
        //initComponents();
        bricks=new Bricks();
        addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
        //this.requestFocusInWindow();
        setFocusTraversalKeysEnabled(true);
        
        time=new Timer(slow,this);
        time.start();
        
    }
    @Override
    public void paint(Graphics g){
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);
        
        //bricks
        bricks.draw((Graphics2D)g);
        
        //Scores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,595, 30);
        
        //Option
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.BOLD,15));
        g.drawString("Exit: ESC",20, 20);
        
        //border
        g.setColor(Color.PINK);
        g.fillRect(0,0, 3, 592);
        g.fillRect(0,0, 692,3);
        g.fillRect(691,0, 3, 592);
        
        //base
        g.setColor(Color.blue);
        g.fillRect(player,550,100,10);
        
        //ball
        g.setColor(Color.red);
        g.fillOval(ballx,bally, 20, 20);
        
        if(bally>570){
            
            //start1 = false;
            x = 0;
            y = 0;

            if (score >= highscore) {
                //highscore=score;
                g.setColor(Color.RED);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("New High Score: " + score, 190, 300);
                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Press enter to Restart!", 230, 350);
                highscore = score;
                check = true;
            }
            if (!check) {
                g.setColor(Color.RED);
                g.setFont(new Font("serif", Font.BOLD, 30));
                g.drawString("Game Over--Score: " + score, 190, 300);
                g.setFont(new Font("serif", Font.BOLD, 20));
                g.drawString("Press enter to Restart!", 230, 350);
            }

            
            start1=false;
        }
        if(total<=0){
            start1=false;
            x=0;
            y=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Score: "+score,190, 300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("You Won",230, 350);
            restart();
        }
        
        
       
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        time.start();
        if(start1){
            if(new Rectangle(ballx,bally,20,20).intersects(new Rectangle(player,550,100,8))){
                y=-y;
            }
            A: for(int i=0;i<bricks.map.length;i++){
                for(int j=0;j<bricks.map[0].length;j++){
                    if(bricks.map[i][j]>0){
                        int brickx= j*bricks.width + 80;
                        int bricky= i*bricks.height +50;
                        int width= bricks.width;
                        int height= bricks.height;
                        
                        Rectangle rect= new Rectangle(brickx,bricky,width,height);
                        Rectangle ballrect= new Rectangle(ballx,bally,20,20);
                        Rectangle brickrect=rect;
                        if(ballrect.intersects(brickrect)) {
                            bricks.setBrickValue(0, i, j);
                            total--;
                            score += 5;
                            if (ballx + 19 <= brickrect.x || ballx + 1 >= brickrect.x + brickrect.width) {
                                x = -x;
                            } else {
                                y = -y;
                            }
                            break A;
                        }
                    }
                }
            }
            ballx+=x;
            bally+=y;
            if(ballx<0){
                x=-x;
            }
            if(bally<0){
                y=-y;
            }
            if(ballx>670){
                x=-x;
            }
       // java.util.Timer time1=new java.util.Timer();
        //TimerTask task=new AddBricks();
        //time1.schedule(task, 20, 20);
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(player>=600){
                player=600;
            }
            else{
                moveRight();
            }
                //moveRight();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(player<10){
                player=10;
            }
            else{
                moveLeft();
            }
               
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!start1){
                start1=true;
                restart();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            start1=false;
            if(JOptionPane.showConfirmDialog(this, "EXIT?", "Game", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        
        //repaint();
    }
    public void restart(){
        ballx = 120;
        bally = 350;
        x = -1;
        y = -2;
        player = 310;
        score = 0;
        total = 21;
        check= false;
        bricks = new Bricks();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void moveRight(){
        start1=true;
        player+=20;
        //repaint();
    }
    public void moveLeft(){
        start1=true;
        player-=20;
        //repaint();
    }
    
}
