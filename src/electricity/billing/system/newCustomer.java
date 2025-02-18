package electricity.billing.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class newCustomer extends JFrame implements ActionListener{
    JLabel heading,customerName, meterNum, address, city, state, email, phone, meternumText;
    JButton next, cancel;
    TextField nameText, addressText, cityText, stateText, emailText, phoneText;

    newCustomer(){
        super("New Customer");
        setSize(700, 500);
        setLocation(625, 325);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 181, 86));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("New Customer");
        customerName.setBounds(50, 80, 100, 20);
        customerName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(customerName);
 
        nameText = new TextField();
        nameText.setBounds(180, 80, 150, 20 );
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50, 120, 100, 20); 
        meterNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(meterNum);

        meternumText = new JLabel("");
        meternumText.setBounds(180, 120, 150, 20);
        panel.add(meternumText);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meternumText.setText(""+ Math.abs(number));


        address = new JLabel("Address");
        address.setBounds(50, 160, 100, 20); 
        address.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);


        city = new JLabel("City");
        city.setBounds(50, 200, 100, 20); 
        city.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);
        


        state = new JLabel("State");
        state.setBounds(50, 240, 100, 20); 
        state.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);


        email = new JLabel("Email id");
        email.setBounds(50, 280, 100, 20); 
        email.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);



        phone = new JLabel("Phone no.");
        phone.setBounds(50, 320, 100, 20); 
        phone.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        next = new JButton();
        next.setBounds(120, 390, 100, 25);
        next.setText("Next");
        next.setBackground(new Color(140, 235, 52));
        next.setForeground(new Color(8, 3, 74));
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton();
        cancel.setBounds(250, 390, 100, 25);
        cancel.setText("Cancel");
        cancel.setBackground(new Color(140, 235, 52));
        cancel.setForeground(new Color(8, 3, 74));
        cancel.addActionListener(this);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/splash/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel, "West");



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==next){
            String sname = nameText.getText();
            String smeter = meternumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();

            String query_customer = "insert into new_customer (name, meter_no, address, city, state, email, phone_no) values('"+ sname + "','" + smeter + "','" + saddress + "','" + scity + "','" + sstate + "','" + eemail + "','" + sphone + "')";
            String query_signup = "insert into Signup(meter_no, username ,name, password, usertype, employer_id) values('" + smeter + "', '', '" + sname + "', '', '', '')";


            try{
                Database c = new Database();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                setVisible(false);
                new meterinfo(smeter);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
        
    }
    public static void main(String[] args) {
        new newCustomer();
    }
}
