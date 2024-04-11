import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchPage extends JFrame{

    private JTextArea resultTextArea;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdata";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Lavanya@2005";

    SearchPage(){
        JFrame frame2 = new JFrame("Search for a Buddy");
        frame2.getContentPane().setBackground(Color.white);
        frame2.setSize(700, 700);
        frame2.setVisible(true);           
        frame2.setLayout(null);
        frame2.setResizable(false);
        
        JLabel lb3 = new JLabel();
        lb3.setText("Search for a Buddy !!!");
        lb3.setBackground(Color.black);
        lb3.setBounds(200, 100, 250, 30);
        lb3.setFont(new Font("Times New Roman",Font.BOLD,20));

        JTextField userNameField, interestsField, hobbiesField;

        JLabel userNameLabel = new JLabel("User Name :");
        userNameLabel.setBounds(80, 160, 80, 30);

        JLabel interestsLabel = new JLabel("Interests :");
        interestsLabel.setBounds(80, 220, 80, 30);

        JLabel hobbiesLabel = new JLabel("Hobbies :");
        hobbiesLabel.setBounds(80, 280, 80, 30);

        userNameField = new JTextField();
        userNameField.setBounds(200, 160, 300, 40);

        interestsField = new JTextField();
        interestsField.setBounds(200, 220, 300, 40);

        hobbiesField = new JTextField();
        hobbiesField.setBounds(200, 280, 300, 40);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBounds(80, 400, 540, 200);

        frame2.add(lb3);
        frame2.add(userNameLabel);
        frame2.add(interestsLabel);
        frame2.add(hobbiesLabel);

        frame2.add(userNameField);
        frame2.add(interestsField);
        frame2.add(hobbiesField);
        
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.pink);
        searchButton.setBounds(200, 400, 200, 30);

        searchButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            String interests = interestsField.getText().trim();
            String hobbies = hobbiesField.getText().trim();
            
            performSearch(interests, hobbies);
            frame2.dispose();
            }
        });

        frame2.add(searchButton);
    }

    private void performSearch(String interests, String hobbies) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE interests LIKE ? OR hobbies LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + interests + "%");
                preparedStatement.setString(2, "%" + hobbies + "%");

                ResultSet resultSet = preparedStatement.executeQuery();

                BuddiesPage resultFrame = new BuddiesPage();

                while (resultSet.next()) {
                    String retrievedUsername = resultSet.getString("username");
                    int retrievedAge = resultSet.getInt("age");
                    String retrievedInterests = resultSet.getString("interests");
                    String retrievedHobbies = resultSet.getString("hobbies");

                    resultFrame.addResult(retrievedUsername, retrievedInterests, retrievedHobbies, retrievedAge);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}