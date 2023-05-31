import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.fazecast.jSerialComm.*;

public class ButtonExample {
    private static SerialPort serialPort;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Send Command");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String portName = "/dev/ttyUSB0";
                String command = "YOUR_COMMAND_HERE";

                if (serialPort != null && serialPort.isOpen()) {
                    serialPort.writeBytes(command.getBytes(), command.length());
                    System.out.println("Command sent: " + command);
                } else {
                    System.out.println("Serial port is not open.");
                }
            }
        });

        frame.getContentPane().add(button, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        String portName = "/dev/ttyUSB0";
        int baudRate = 9600;
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(baudRate);

        if (serialPort.openPort()) {
            System.out.println("Serial port opened successfully.");
        } else {
            System.out.println("Failed to open serial port.");
        }
    }
}
