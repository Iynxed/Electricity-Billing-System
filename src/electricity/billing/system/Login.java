package electricity.billing.system;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;


public class Login extends JFrame implements ActionListener{
    
    JTextField userText,passwordText;
    Choice LoginChoice;
    JButton loginButton, cancelButton, signupButton;
    
    Login(){
        super("Login");
        getContentPane().setBackground(new Color(203, 193,245 ));

        JLabel username = new JLabel("UserName");
        username.setBounds(300, 60, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);
        
        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        JLabel loggin = new JLabel("Logging in as");
        loggin.setBounds(300, 140, 100, 20);
        add(loggin);

        LoginChoice = new Choice();
        LoginChoice.add("Admin");
        LoginChoice.add("Customer");
        LoginChoice.setBounds(400, 140, 150, 20);
        add(LoginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(400, 215, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/splash/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(10, 5, 250, 250);
        add(profileLabel);

        setSize(648, 300);
        setLocation(650, 400);
        setLayout(null);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton){
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String suser = LoginChoice.getSelectedItem();

            try {
                Database c = new Database();
                String queryy = "select * from Signup where username='"+susername+"' and password='"+spassword+"' and usertype='"+suser+"'";
                ResultSet resultSet = c.statement.executeQuery(queryy);
                
                if(resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(suser, meter);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Login details");
                }

            } catch (Exception E) {
                E.printStackTrace();
            }

        }else if(e.getSource()==cancelButton){
            setVisible(false);
        }else if(e.getSource()==signupButton){
            setVisible(false);
            new Signup();
        }
            
        
    }
    public static void main(String[] args) {
        new Login();
    }

}
