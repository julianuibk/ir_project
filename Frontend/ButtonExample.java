import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonExample {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a new JButton
        JButton button = new JButton("Click me!");
        
        // Set the layout manager of the JFrame to a FlowLayout
        frame.setLayout(new FlowLayout());
        
        // Add the button to the JFrame
        frame.add(button);
        
        // Create a new ActionListener for the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle button click event here
                JOptionPane.showMessageDialog(null, "Button clicked!");
            }
        });
        
        // Set the size of the JFrame and make it visible
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}