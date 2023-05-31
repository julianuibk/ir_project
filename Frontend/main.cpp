#include <fcntl.h>
#include <iostream>
#include <termios.h>
#include <unistd.h>

int main() {
  int serialPort = open("/dev/ttyUSB0", O_RDWR | O_NOCTTY | O_NDELAY);

  if (serialPort == -1) {
    std::cerr << "Error opening serial port" << std::endl;
    return 1;
  }

//   termios tty;
//   if (tcgetattr(serialPort, &tty) != 0) {
//     std::cerr << "Error getting serial port attributes" << std::endl;
//     return 1;
//   }

//   tty.c_cflag &= ~PARENB;  // Disable parity bit
//   tty.c_cflag &= ~CSTOPB;  // Set one stop bit
//   tty.c_cflag &= ~CSIZE;   // Clear character size mask
//   tty.c_cflag |= CS8;      // Set 8 data bits
//   tty.c_cflag &= ~CRTSCTS; // Disable hardware flow control
//   tty.c_cflag |=
//       CREAD | CLOCAL; // Enable reading and ignore modem control lines

//   tty.c_iflag &= ~(IXON | IXOFF | IXANY); // Disable software flow control

//   tty.c_lflag = 0;     // No local flags
//   tty.c_oflag = 0;     // No output flags
//   tty.c_cc[VMIN] = 0;  // Read doesn't block
//   tty.c_cc[VTIME] = 5; // 0.5 seconds read timeout

//   cfsetospeed(&tty, B9600); // Set baud rate (in this example, 9600)
//   cfsetispeed(&tty, B9600); // Set baud rate (in this example, 9600)

//   if (tcsetattr(serialPort, TCSANOW, &tty) != 0) {
//     std::cerr << "Error setting serial port attributes" << std::endl;
//     return 1;
//   }

//   const char *command = "AT\r\n"; // Example command

//   ssize_t bytesWritten = write(serialPort, command, 4);

//   if (bytesWritten == -1) {
//     std::cerr << "Error writing to serial port" << std::endl;
//     return 1;
//   }

  close(serialPort);

  return 0;
}