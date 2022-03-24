package uzayoyunu;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

public class OyunEkrani extends JFrame{
    
    public OyunEkrani(String uzay_Oyunu) throws HeadlessException{
        super(uzay_Oyunu);
    }

    
    
    public static void main(String[] args) {
        
        OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");
        
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        ekran.setVisible(true);
        
    }
 
    
}
