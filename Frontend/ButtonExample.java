import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.fazecast.jSerialComm.*;

public class ButtonExample {
    private static SerialPort serialPort;

    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new JButton
        JButton button = new JButton("Send Command");

        // Set the layout manager of the JFrame to a FlowLayout
        frame.setLayout(new FlowLayout());

        // Add the button to the JFrame
        frame.add(button);

        // Create a new ActionListener for the button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle button click event here
                sendCommandToSerialPort("ttyUSB0", "YOUR_COMMAND_HERE");
            }
        });

        // Set the size of the JFrame and make it visible
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public static void sendCommandToSerialPort(String portName, String command) {
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (serialPort.openPort()) {
            try {
                byte[] commandBytes = command.getBytes();
                serialPort.getOutputStream().write(commandBytes);
                System.out.println("Command sent: " + command);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                serialPort.closePort();
            }
        } else {
            System.out.println("Failed to open serial port.");
        }
    }
}
