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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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