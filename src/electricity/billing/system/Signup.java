package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    Choice loginASCho;
    TextField meterText, EmployerText, userNameText, nameText, passwordText;
    JButton create, back;

    Signup() {
        super("Signup Page");
        getContentPane().setBackground(new Color(168, 203, 255));

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30, 50, 125, 20);
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(170, 50, 120, 20);
        add(loginASCho);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setVisible(false);  // Initially hidden for Admin
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);  // Initially hidden for Admin
        add(meterText);

        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30, 100, 125, 20);
        Employer.setVisible(true);  // Visible for Admin
        add(Employer);

        EmployerText = new TextField();
        EmployerText.setBounds(170, 100, 125, 20);
        EmployerText.setVisible(true);  // Visible for Admin
        add(EmployerText);

        JLabel userName = new JLabel("UserName");
        userName.setBounds(30, 140, 125, 20);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170, 140, 125, 20);
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 180, 125, 20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170, 180, 125, 20);
        nameText.setEditable(true);  // Editable for all users
        add(nameText);

        JLabel password = new JLabel("Password");
        password.setBounds(30, 220, 125, 20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170, 220, 125, 20);
        add(passwordText);

        // When the meter field loses focus, fetch customer name if meter number exists
        meterText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (loginASCho.getSelectedItem().equals("Customer")) {
                    try {
                        Database c = new Database();
                        String query = "SELECT * FROM Signup WHERE meter_no = ?";
                        PreparedStatement pstmt = c.connection.prepareStatement(query);
                        pstmt.setString(1, meterText.getText());
                        ResultSet resultSet = pstmt.executeQuery();
                        if (resultSet.next()) {
                            nameText.setText(resultSet.getString("name"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Meter number not found!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Changing visibility and interactivity of fields based on Admin/Customer selection
        loginASCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginASCho.getSelectedItem();
                if (user.equals("Customer")) {
                    Employer.setVisible(false);
                    EmployerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                    nameText.setEditable(true);  // Allow customers to edit their name
                } else {
                    Employer.setVisible(true);
                    EmployerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                    nameText.setEditable(true);  // Admin can edit name
                }
            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(66, 127, 219));
        create.setForeground(Color.black);
        create.setBounds(50, 285, 100, 25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(66, 127, 219));
        back.setForeground(Color.black);
        back.setBounds(180, 285, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/boy.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(320, 30, 250, 250);
        add(boyLabel);

        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String sloginAs = loginASCho.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            String semployer = EmployerText.getText();

            try {
                Database c = new Database();
                String query;
                PreparedStatement pstmt;

                if (sloginAs.equals("Admin")) {
                    // Query for Admins
                    query = "INSERT INTO Signup (username, name, password, usertype, employer_id) VALUES (?, ?, ?, ?, ?)";
                    pstmt = c.connection.prepareStatement(query);
                    pstmt.setString(1, susername);
                    pstmt.setString(2, sname);
                    pstmt.setString(3, spassword);
                    pstmt.setString(4, sloginAs);
                    pstmt.setString(5, semployer);
                } else {
                    // Query for Customers (requires meter number)
                    if (smeter.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Meter number is required for Customers!");
                        return;
                    }
                    query = "UPDATE Signup SET username = ?, password = ?, usertype = ? WHERE meter_no = ?";
                    pstmt = c.connection.prepareStatement(query);
                    pstmt.setString(1, susername);
                    pstmt.setString(2, spassword);
                    pstmt.setString(3, sloginAs);
                    pstmt.setString(4, smeter);
                }

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Account Created/Updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Error in creating/updating account. Please check inputs.");
                }

                setVisible(false);
                new Login();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
