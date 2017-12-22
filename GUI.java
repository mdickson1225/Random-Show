/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package side.project.pkg1;

/**
 *
 * @author Mark
 */
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import java.awt.Button;
import javax.swing.*;

public class GUI {
    //GUI gang GUI gang GUI gang.
  public GUI() {
    JFrame jf = new JFrame();
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setTitle("Show Generator");
    JPanel jp = new JPanel();
    JButton enter = new JButton("Enter");
    jf.add(enter, BorderLayout.SOUTH);
    jf.setVisible(true);
    
    
    
}
}