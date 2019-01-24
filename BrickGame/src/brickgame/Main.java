/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickgame;

import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author pragu
 */
public class Main {
    public static void main(String[] args){
        JFrame frame= new JFrame();
        Game game= new Game();
        
        frame.setBounds(10,10,700,600);
        frame.setTitle("Break The Bricks");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
    }
}
