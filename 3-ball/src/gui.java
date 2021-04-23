

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class gui extends JPanel implements Runnable,KeyListener {
    
    int oynamaSırası = 1;
    
    int hit1 = 0;
    int hit2 = 0;
    
    int flag1 = 0;
    int flag2 = 0;
    
    int FPP = 0;
    int SPP = 0;
     
    ball b1;
    ball b2;
    ball b3;
    
    boolean going = true;

    Thread animator;
    
    double deltaXb1b2;
    double deltaYb1b2;
    double deltaXb1b3;
    double deltaYb1b3;
    double deltaXb2b3;
    double deltaYb2b3;
    double distance;
    

    public gui() {
        b1 = new ball(600, 100, 20, Color.yellow);
        b1.setxSpeed(0);
        b1.setySpeed(0);

        b2 = new ball(600, 250, 20, Color.red);
        b2.setxSpeed(0);
        b2.setySpeed(0);

        b3 = new ball(80, 300, 20, Color.white);
        b3.setxSpeed(0);
        b3.setySpeed(0);

        animator = new Thread(this);
        animator.start();
       
        
        setBackground(Color.GREEN);
        

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        b1.draw(g);
        b2.draw(g);
        b3.draw(g);
       
    }

    @Override
    public void run() {
        while (going) {
            
            b1.move();
            b2.move();
            b3.move();
            
            
            checkCollision();
            
            slowing();

            repaint();
            
            if(FPP == 5){
                
                JOptionPane.showMessageDialog(null, "Tebrikler!\n"
                        + "Birinci Oyuncu Kazandı!" , "Oyun Bitti!", -1);
                
                System.exit(0);
                
            }
            else if(SPP == 5){
                
                JOptionPane.showMessageDialog(null, "Tebrikler!\n"
                        + "İkinci Oyuncu Kazandı!" , "Oyun Bitti!", -1);
                
                System.exit(0);
                
            }
            
            if(oynamaSırası == 1){
                P1hit2ball();
            }
            else if(oynamaSırası == 2){
                P2hit2ball();
            }
            

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
            }
            
          
        }
    }

    public void checkCollision() {

        deltaXb1b2 = Math.abs(b1.getX() - b2.getX());
        deltaYb1b2 = Math.abs(b1.getY() - b2.getY());
        distance = deltaXb1b2 * deltaXb1b2 + deltaYb1b2 * deltaYb1b2;
        
        if (distance < (b1.getDiametar() / 2 + b2.getDiametar() / 2) * (b1.getDiametar() / 2 + b2.getDiametar() / 2)) {

            double newxSpeed1 = (b1.getxSpeed() * (4 - 7) + (2 * 7 * b2.getxSpeed())) / 11;

            double newxSpeed2 = (b2.getxSpeed() * (7 - 4) + (2 * 4 * b1.getxSpeed())) / 11;

            double newySpeed1 = (b1.getySpeed() * (4 - 7) + (2 * 7 * b2.getySpeed())) / 11;

            double newySpeed2 = (b2.getySpeed() * (7 - 4) + (2 * 4 * b1.getySpeed())) / 11;

            b2.setxSpeed(newxSpeed2);
            b2.setySpeed(newySpeed2);
            b1.setxSpeed(newxSpeed1);
            b1.setySpeed(newySpeed1);
           

        }
        
        deltaXb1b3 = Math.abs(b1.getX() - b3.getX());
        deltaYb1b3 = Math.abs(b1.getY() - b3.getY());
        distance = deltaXb1b3 * deltaXb1b3 + deltaYb1b3 * deltaYb1b3;
        
        if (distance < (b1.getDiametar() / 2 + b3.getDiametar() / 2) * (b1.getDiametar() / 2 + b3.getDiametar() / 2)) {

            double newxSpeed1 = (b1.getxSpeed() * (4 - 7) + (2 * 7 * b3.getxSpeed())) / 11;

            double newxSpeed2 = (b3.getxSpeed() * (7 - 4) + (2 * 4 * b1.getxSpeed())) / 11;

            double newySpeed1 = (b1.getySpeed() * (4 - 7) + (2 * 7 * b3.getySpeed())) / 11;

            double newySpeed2 = (b3.getySpeed() * (7 - 4) + (2 * 4 * b1.getySpeed())) / 11;

            b3.setxSpeed(newxSpeed2);
            b3.setySpeed(newySpeed2);
            b1.setxSpeed(newxSpeed1);
            b1.setySpeed(newySpeed1);
            
            flag1 = 2;
            
            hit1 = 0 ;
        }
        
        
        deltaXb2b3 = Math.abs(b2.getX() - b3.getX());
        deltaYb2b3 = Math.abs(b2.getY() - b3.getY());
        distance = deltaXb2b3 * deltaXb2b3 + deltaYb2b3 * deltaYb2b3;
        
        if (distance < (b2.getDiametar() / 2 + b3.getDiametar() / 2) * (b2.getDiametar() / 2 + b3.getDiametar() / 2)) {

            double newxSpeed1 = (b2.getxSpeed() * (4 - 7) + (2 * 7 * b3.getxSpeed())) / 11;

            double newxSpeed2 = (b3.getxSpeed() * (7 - 4) + (2 * 4 * b2.getxSpeed())) / 11;

            double newySpeed1 = (b2.getySpeed() * (4 - 7) + (2 * 7 * b3.getySpeed())) / 11;

            double newySpeed2 = (b3.getySpeed() * (7 - 4) + (2 * 4 * b2.getySpeed())) / 11;

            b3.setxSpeed(newxSpeed2);
            b3.setySpeed(newySpeed2);
            b2.setxSpeed(newxSpeed1);
            b2.setySpeed(newySpeed1);
            
            flag2 = 2;
            
            hit2 = 0;

        }
        
        if (b3.getxSpeed() == 0 && b3.getySpeed() == 0
            && b2.getxSpeed() == 0 && b2.getySpeed() == 0
            && b1.getxSpeed() == 0 && b1.getySpeed() == 0) {
            
            if(flag1 != 2 && flag2 != 2){
                 if(hit1 == 1 && hit2 == 1){
                    flag1 = 1;
                    flag2 = 1;
            
                    hit1 = 0;
                    hit2 = 0;
                }
            }
            
            if(flag1 == 2 && flag2 != 2){
                if(hit1 == 0 && hit2 == 1){
                    flag1 = 1;
                    flag2 = 1;
            
                    hit1 = 0;
                    hit2 = 0;
                }
            }
            
            if(flag1 != 2 && flag2 == 2){
                if(hit1 == 1 && hit2 == 0){
                    flag1 = 1;
                    flag2 = 1;
            
                    hit1 = 0;
                    hit2 = 0;
                }
            }
        }
        
        
    }
    
    public void slowing() {
        
        if(b3.getxSpeed() != 0){
            
            b3.setxSpeed(b3.getxSpeed()-(b3.getxSpeed()/125));
            
            if(b3.getxSpeed() < 0.3 && b3.getxSpeed() > -0.3){
                b3.setxSpeed(0);
            }
            
        }
        
        if(b3.getySpeed() != 0){
            
            b3.setySpeed(b3.getySpeed()-(b3.getySpeed()/125));
            
            if(b3.getySpeed() < 0.3 && b3.getySpeed() > -0.3){
                b3.setySpeed(0);
            }
        }
        
        if(b1.getxSpeed() != 0){
            
            b1.setxSpeed(b1.getxSpeed()-(b1.getxSpeed()/125));
            
            if(b1.getxSpeed() < 0.3 && b1.getxSpeed() > -0.3){
                b1.setxSpeed(0);
            }
        }
        
        if(b1.getySpeed() != 0){
            
            b1.setySpeed(b1.getySpeed()-(b1.getySpeed()/125));
            
            if(b1.getySpeed() < 0.3 && b1.getySpeed() > -0.3){
                b1.setySpeed(0);
            }
        }
        
        if(b2.getxSpeed() != 0){
            
            b2.setxSpeed(b2.getxSpeed()-(b2.getxSpeed()/125));
            
            if(b2.getxSpeed() < 0.3 && b2.getxSpeed() > -0.3){
                b2.setxSpeed(0);
            }
        }
        
        if(b2.getySpeed() != 0){
            
            b2.setySpeed(b2.getySpeed()-(b2.getySpeed()/125));
            
            if(b2.getySpeed() < 0.3 && b2.getySpeed() > -0.3){
                b2.setySpeed(0);
            }
        }
    }
    
    
    public void P1hit2ball(){
        
        if(flag1 == 2 && flag2 == 2 &&  b3.getxSpeed() == 0  && b3.getySpeed() == 0
                && b2.getxSpeed() == 0 && b2.getySpeed() == 0
                && b1.getxSpeed() == 0 && b1.getySpeed() == 0){
            FPP++;
            JOptionPane.showMessageDialog(null, "1 Puan Kazandınız\nBirinci Oyuncu Toplam Puan: " + FPP + "\nİkinci Oyuncu Toplam Puan: " + SPP + 
                    "\nSıra Tekrar Birinci Oyuncuda.." , "Tebrikler!", -1);
            flag1 = 0;
            flag2 = 0;
            
            
            
            
            
            oynamaSırası = 1;
            
        }
        
        
        else if(flag1 == 1 && flag2 == 1 &&  b3.getxSpeed() == 0  && b3.getySpeed() == 0
                && b2.getxSpeed() == 0 && b2.getySpeed() == 0
                && b1.getxSpeed() == 0 && b1.getySpeed() == 0){
            
            JOptionPane.showMessageDialog(null, "Puan Kazanamadınız..\nBirinci Oyuncu Toplam Puan: " + FPP + "\nİkinci Oyuncu Toplam Puan: " + SPP + 
                    "\nSıra İkinci Oyuncuda.." , "Hay Aksi Şeytan!", -1);
            flag1 = 0;
            flag2 = 0;
            
            
            
            oynamaSırası = 2;
        }
        
    }
    
    public void P2hit2ball(){
        
        if(flag1 == 2 && flag2 == 2 &&  b3.getxSpeed() < 0.3 && b3.getxSpeed() > -0.3 && b3.getySpeed() < 0.3 && b3.getySpeed() > -0.3
                && b2.getxSpeed() < 0.3 && b2.getxSpeed() > -0.3 && b2.getySpeed() < 0.3 && b2.getySpeed() > -0.3
                && b1.getxSpeed() < 0.3 && b1.getxSpeed() > -0.3 && b1.getySpeed() < 0.3 && b1.getySpeed() > -0.3){
            SPP++;
            JOptionPane.showMessageDialog(null, "1 Puan Kazandınız\nBirinci Oyuncu Toplam Puan: " + FPP + "\nİkinci Oyuncu Toplam Puan: " + SPP + 
                    "\nSıra Tekrar İkinci Oyuncuda.." , "Tebrikler!", -1);
            flag1 = 0;
            flag2 = 0;
            
            b3.setxSpeed(0);
            b3.setySpeed(0);
            
            oynamaSırası = 2;
            
        }
        
        
        else if(flag1 == 1 && flag2 == 1 &&  b3.getxSpeed() < 0.3 && b3.getxSpeed() > -0.3 && b3.getySpeed() < 0.3 && b3.getySpeed() > -0.3
                && b2.getxSpeed() < 0.3 && b2.getxSpeed() > -0.3 && b2.getySpeed() < 0.3 && b2.getySpeed() > -0.3
                && b1.getxSpeed() < 0.3 && b1.getxSpeed() > -0.3 && b1.getySpeed() < 0.3 && b1.getySpeed() > -0.3){
            
            JOptionPane.showMessageDialog(null, "Puan Kazanamadınız..\nBirinci Oyuncu Toplam Puan: " + FPP + "\nİkinci Oyuncu Toplam Puan: " + SPP + 
                    "\nSıra Birinci Oyuncuda.." , "Hay Aksi Şeytan!", -1);
            flag1 = 0;
            flag2 = 0;
            
            b3.setxSpeed(0);
            b3.setySpeed(0);
            
            oynamaSırası = 1;
        }
        
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        int c = e.getKeyCode();
                
        if(c == KeyEvent.VK_LEFT && b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
                    
            if(b3.getX() <= 0 ){
                        
                b3.setX(0);
            }
                    
            else {
                        
                b3.setX(b3.getX() - 3);
            }
        }
                
        if(c == KeyEvent.VK_RIGHT && b3.getxSpeed() == 0 && b3.getySpeed() == 0){
                    
            if(b3.getX() >= 780 ){
                        
                b3.setX(780);
            }
                    
            else {
                        
                b3.setX(b3.getX() + 3);
            }
        }
                
        if(c == KeyEvent.VK_UP && b3.getxSpeed() == 0 && b3.getySpeed() == 0){
                    
            if(b3.getY()<= 0 ){
                        
                b3.setY(0);
            }
                    
            else {
                        
                b3.setY(b3.getY()- 3);
            }
        }
                
        if(c == KeyEvent.VK_DOWN && b3.getxSpeed() == 0 && b3.getySpeed() == 0){
                    
            if(b3.getY()>= 400 ){
                        
                b3.setY(400);
            }
                    
            else {
                        
                b3.setY(b3.getY() + 3);
            }
        }
        
        
        if(c == KeyEvent.VK_W  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(0);
            b3.setySpeed(-10);
            
            hit1 = 1;
            hit2 = 1;
        }
        
        if(c == KeyEvent.VK_S  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(0);
            b3.setySpeed(10);
            
            hit1 = 1;
            hit2 = 1;
        }
        
        if(c == KeyEvent.VK_A  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(-10);
            b3.setySpeed(0);
            
            hit1 = 1;
            hit2 = 1;
        }
        
        if(c == KeyEvent.VK_D  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(10);
            b3.setySpeed(0);
            
            hit1 = 1;
            hit2 = 1;
        }
        
        if(c == KeyEvent.VK_E  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(6);
            b3.setySpeed(-8);
            
            hit1 = 1;
            hit2 = 1;
        }
        
        if(c == KeyEvent.VK_Q  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(-6);
            b3.setySpeed(-8);
            
            hit1 = 1;
            hit2 = 1;
        }
        
        if(c == KeyEvent.VK_Z  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(-6);
            b3.setySpeed(8);
            
            hit1 = 1;
            hit2 = 1;
        }
        
        if(c == KeyEvent.VK_X  &&  b3.getxSpeed() == 0 && b3.getySpeed() == 0 ){
            
            b3.setxSpeed(6);
            b3.setySpeed(8);
            
            hit1 = 1;
            hit2 = 1;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
