import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HomePage implements ActionListener{

    JFrame window = new JFrame("Find My Buddy");
    public HomePage(){    
           
        window.getContentPane().setBackground(Color.white);
        window.setSize(700, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        window.setVisible(true);           
        window.setLayout(null);
        window.setResizable(false);

        JLabel lb1 = new JLabel();
        lb1.setText("Hello Buddy !!!");
        lb1.setBackground(Color.black);
        lb1.setBounds(200, 100, 250, 30);
        lb1.setFont(new Font("Times New Roman",Font.BOLD,30));

        JButton button1 = new JButton();
        button1.setText("Create an Account");
        button1.setBackground(Color.pink);
        button1.setBounds(210, 210, 200, 30);
        button1.setActionCommand("Create an Account");
        button1.addActionListener(this);
        
        JButton button2 = new JButton();
        button2.setText("Search for a Buddy");
        button2.setBackground(Color.pink);
        button2.setBounds(210, 260, 200, 30);
        button2.setActionCommand("Search for a Buddy");
        button2.addActionListener(this);

        window.add(lb1);
        window.add(button1);
        window.add(button2);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Create an Account")){
            new CreatePage();
            window.dispose();
        }
        if(e.getActionCommand().equals("Search for a Buddy")){
            new SearchPage();
            window.dispose();
        }
    }
    public static void main(String[] args) {
        new HomePage();
}
}