package uzayoyunu;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;

public class Oyun extends JPanel implements KeyListener, ActionListener{
    
    private int gecenSure = 0;
    private int harcananAtes = 0;
    private BufferedImage image;
    
    private ArrayList<Ates> atesler = new ArrayList<>();
    
    private int atesdirY = 5;
    
    private int topX = 0;
    
    private int topdirX = 4;
    
    private int uzayGemisiX = 0;
    private int dirUzayX = 20;
    
    
    
    Timer timer = new Timer(10, this);


    public Oyun() {       
        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            ex.getLocalizedMessage();
        }       
        setBackground(Color.BLACK);
        
        timer.start();
        
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gecenSure += 5;
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);  
        g.drawImage(image, uzayGemisiX, 490, image.getWidth()/10,image.getHeight()/10,this);

        for(int i=0; i<atesler.size(); i++){
            if(atesler.get(i).getY() < 0){
                atesler.remove(i);
            }
        }
        g.setColor(Color.BLUE);
        
        for(int i=0; i<atesler.size(); i++){
            g.fillRect(atesler.get(i).getX(), atesler.get(i).getY(), 10, 20);
        }
        
        if(kontrolEt()){
            timer.stop();
            String message = "Kazandınız!"+"\n"+
                    "Harcanan Ates : "+harcananAtes + "\n" +
                    "Gecen süre : " +  (float)(gecenSure/1000) + " sn";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }
    
    
    public boolean kontrolEt(){
        for(int i=0; i<atesler.size(); i++){
            Ates ates = atesler.get(i);
            if(new Rectangle(ates.getX(), ates.getY(), 10, 20).intersects(new Rectangle(topX, 0, 20, 20))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void repaint() {
        super.repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT){
            if(uzayGemisiX > 0){
                uzayGemisiX -= dirUzayX;
            }           
        }else if(c == KeyEvent.VK_RIGHT){
            if(uzayGemisiX < 740){
                uzayGemisiX += dirUzayX;
            }
        } else if(c == KeyEvent.VK_CONTROL){
            atesler.add(new Ates(uzayGemisiX + 15, 475));
            harcananAtes++;          
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for(int i=0; i< atesler.size(); i++){
            atesler.get(i).setY(atesler.get(i).getY() - atesdirY);
        }
        
        topX += topdirX;
        
        if(topX > 760){
            topdirX = -topdirX;
        }
        if(topX <= 0){
            topdirX = -topdirX;
        }     
        repaint();
    }
    
    
}
