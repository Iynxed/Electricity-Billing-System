package electricity.billing.system;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame{
    Splash(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/Splash.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne); 
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        setSize(600, 400);
        setLocation(600, 350);
        setVisible(true);

        try {
            Thread.sleep(3000);
            setVisible(false);

            new Login();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }

    }

    public static void main(String[] args) {
        
        new Splash();
    }
}
