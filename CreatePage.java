import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;
public class CreatePage extends JFrame{

    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdata";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Lavanya@2005";

    CreatePage(){
        JFrame frame1 = new JFrame("Create an Account");
        frame1.getContentPane().setBackground(Color.white);
        frame1.setSize(700, 700);
        frame1.setVisible(true);           
        frame1.setLayout(null);
        frame1.setResizable(false);

        JLabel lb2 = new JLabel();
        lb2.setText("Create an Account !!!");
        lb2.setBackground(Color.black);
        lb2.setBounds(200, 100, 250, 30);
        lb2.setFont(new Font("Times New Roman",Font.BOLD,20));

        JTextField fullNameField, userNameField, ageField, interestsField, hobbiesField;

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setBounds(80, 100, 80, 30);

        JLabel userNameLabel = new JLabel("User Name :");
        userNameLabel.setBounds(80, 160, 80, 30);

        JLabel ageLabel = new JLabel("Age :");
        ageLabel.setBounds(80, 220, 80, 30);

        JLabel interestsLabel = new JLabel("Interests :");
        interestsLabel.setBounds(80, 280, 80, 30);

        JLabel hobbiesLabel = new JLabel("Hobbies :");
        hobbiesLabel.setBounds(80, 340, 80, 30);

        fullNameField = new JTextField();
        fullNameField.setBounds(200, 100, 300, 40);

        userNameField = new JTextField();
        userNameField.setBounds(200, 160, 300, 40);

        ageField = new JTextField();
        ageField.setBounds(200, 220, 300, 40);

        interestsField = new JTextField();
        interestsField.setBounds(200, 280, 300, 40);

        hobbiesField = new JTextField();
        hobbiesField.setBounds(200, 340, 300, 40);

        frame1.add(lb2);
        frame1.add(fullNameLabel);
        frame1.add(userNameLabel);
        frame1.add(ageLabel);
        frame1.add(interestsLabel);
        frame1.add(hobbiesLabel);

        frame1.add(fullNameField);
        frame1.add(userNameField);
        frame1.add(ageField);
        frame1.add(interestsField);
        frame1.add(hobbiesField);

        JButton createButton = new JButton("Create");
        createButton.setBackground(Color.pink);
        createButton.setBounds(200, 450, 200, 30);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            String fullName = fullNameField.getText().trim();
            String username = userNameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String interests = interestsField.getText().trim();
            String hobbies = hobbiesField.getText().trim();

            userDetails user = new userDetails(fullName, username, age, interests, hobbies);
            insertUserDetails(user);

            JOptionPane.showMessageDialog(CreatePage.this, "User created successfully.");
            new SearchPage();
            frame1.dispose();
            }
        });

        frame1.add(createButton);
        frame1.setVisible(true);
    }
    private void insertUserDetails(userDetails user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO users (full_name, username, age, interests, hobbies) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, user.getFullName());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getInterests());
                preparedStatement.setString(5, user.getHobbies());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }
    }
}