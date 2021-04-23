import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ThreeBallGame extends JFrame {
    
    public ThreeBallGame(String title) throws HeadlessException {
        super(title);
    }

    
    public static void main(String[] args) {
        
        
        ThreeBallGame frame = new ThreeBallGame("3 Ball Game");
        
        frame.setSize(800,400);
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JOptionPane.showMessageDialog(null, "Oyun Kuralları:\n"
                + "1.) 2 rakip sırasıyla aynı elde 2 topu da vurmaya çalışır.\n"
                + "2.) 2 topu da vuran oyuncu 1 puan ve  bir vuruş hakkı daha kazanır.\n"
                + "3.) Oyuncu 2 toptan herhangi birini vuramaz ise rakibi bir sonraki el beyaz topu istediği yere taşıyabilir.\n"
                + "4.) 5 puana ulaşan ilk oyuncu oyunu kazanır.\n"
                + "Tuşlar:\n"
                + " - Topun yerini değiştirmek için ok tuşlarını kullanın.\n"
                + " - Topa Vurmak için Q,W,E,A,S,D,Z,X tuşlarını kullanın.\n" , "Hoşgeldiniz!", -1);
        
        
        gui balls = new gui ();
        
        balls.requestFocus();
        balls.addKeyListener(balls);
        balls.setFocusable(true);
        balls.setFocusTraversalKeysEnabled(false);
        
        
        frame.add(balls);
        
        frame.setVisible(true);
        
        
    }
    
}