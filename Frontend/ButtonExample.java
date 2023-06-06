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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);

        SendButton[] sendButtons = {
            new SendButton("Button 1", "Command 1", 50, 50, Color.RED),
            new SendButton("Button 2", "Command 2", 150, 100, Color.BLUE),
            new SendButton("Button 3", "Command 3", 250, 150, Color.GREEN),
            new SendButton("Button 4", "Command 4", 350, 200, Color.YELLOW),
            new SendButton("Button 5", "Command 5", 450, 250, Color.ORANGE),
            new SendButton("Button 6", "Command 6", 550, 300, Color.MAGENTA),
            new SendButton("Button 7", "Command 7", 650, 350, Color.CYAN)
        };

        for (SendButton sendButton : sendButtons) {
            JButton button = new JButton(sendButton.getLabel());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String portName = "/dev/ttyUSB0";
                    String command = sendButton.getCommand();

                    if (serialPort != null && serialPort.isOpen()) {
                        serialPort.writeBytes(command.getBytes(), command.length());
                        System.out.println("Command sent: " + command);
                    } else {
                        System.out.println("Serial port is not open.");
                    }
                }
            });

            button.setBounds(sendButton.getX(), sendButton.getY(), 100, 30);
            button.setBackground(sendButton.getButtonColor());
            buttonPanel.add(button);
        }

        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.setSize(800, 500);
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

class SendButton {
    private String label;
    private String command;
    private int x;
    private int y;
    private Color buttonColor;

    public SendButton(String label, String command, int x, int y, Color buttonColor) {
        this.label = label;
        this.command = command;
        this.x = x;
        this.y = y;
        this.buttonColor = buttonColor;
    }

    public String getLabel() {
        return label;
    }

    public String getCommand() {
        return command;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getButtonColor() {
        return buttonColor;
    }
}
