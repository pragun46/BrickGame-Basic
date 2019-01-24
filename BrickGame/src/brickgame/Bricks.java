/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickgame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author pragu
 */
public class Bricks {
    public static int map[][];
    public static int width;
    public static int height;
    public static int row,col;
    public Bricks(){
        row=3;
        col=7;
        map= new int[row][col];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                map[i][j]=1;
                
            }
        }
        width=540/col;
        height=150/row;
    }
    
    public static void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(j*width+80,i*height+50, width, height); 
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*width+80,i*height+50, width, height); 
                }  
            }
        }
        
    }
    public void setBrickValue(int value, int row, int col){
        map[row][col]=value;
    }
}
