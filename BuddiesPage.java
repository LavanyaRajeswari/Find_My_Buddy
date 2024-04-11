import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class BuddiesPage extends JFrame{

    private JPanel resultsPanel;
    BuddiesPage() {
        setTitle("Search Results");
        setSize(700, 700);
        setBackground(Color.white);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lb4 = new JLabel();
        lb4.setText("Search Results !!!");
        lb4.setBackground(Color.black);
        lb4.setFont(new Font("Times New Roman",Font.BOLD,20));

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        add(lb4);
        add(scrollPane);
        setVisible(true);
    }
    public void addResult(String retrievedUsername, String retrievedInterests, String retrievedHobbies,int retrievedAge) {
                UserDetailsPanel userPanel = new UserDetailsPanel(retrievedUsername, retrievedInterests, retrievedHobbies,retrievedAge);
                resultsPanel.add(userPanel);
                resultsPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
                revalidate();
    }
    private class UserDetailsPanel extends JPanel {
        UserDetailsPanel(String username, String interests, String hobbies, int age) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.pink, 4));
            setLayout(new GridLayout(5, 1, 0, 5));
            setPreferredSize(new Dimension(200, 200));

            JLabel usernameLabel = new JLabel("Username: " + username);
            JLabel interestsLabel = new JLabel("Interests: " + interests);
            JLabel hobbiesLabel = new JLabel("Hobbies: " + hobbies);
            JLabel ageLabel = new JLabel("Age: " + age);

            usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            interestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            hobbiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
            ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            add(usernameLabel);
            add(ageLabel);
            add(interestsLabel);
            add(hobbiesLabel);
        }
    }
}
