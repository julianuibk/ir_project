#include <Arduino.h>
#include <IRremote.h>
#define ledLAUT 3
#define relai 8
int datenInput = 0;

int IR_PIN = 11; // Infarot Pin

enum tasten {
  POWER = 0x100C,
};

IRsend irSender;

void serialEvent() {
  if (Serial.available()) {
    int number = Serial.parseInt();

    if (number >= 0 && number <= 7) {
      datenInput =
          number; // Speichert die empfangene Zahl in der Varriable datenInput
    }

    while (Serial.available() > 0) // leert den BUffer
    {
      Serial.read();
    }
  }
}
void senden(unsigned int value) {
  irSender.sendSony(value, 32);

  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);
  //  0x100C
  //  0x180C
}

void Power() {
  irSender.sendSony(0x100C, 32);

  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);
  //  0x100C
  //  0x180C
}
void Lauter() {
  irSender.sendSony(0x1015, 32);
  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);
  // 0x1015
  // 0x1815
}
void Leiser() {
  irSender.sendSony(0x1014, 32);
  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);
  // 0x1014
  // 0x1814
}
void ChanPlus() {
  irSender.sendSony(0x1018, 32);
  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);
  // 0x1018
  // 0x1818
}
void ChanMinus() {
  irSender.sendSony(0x1017, 32);
  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);

  // 0x1017
  // 0x1817
}

void InputMenu() {
  irSender.sendSony(0x1038, 32);
  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);

  // 0x1038
  // 0x1838
}
void Mute() {

  irSender.sendSony(0x100D, 32);
  digitalWrite(ledLAUT, HIGH);
  delay(100);
  digitalWrite(ledLAUT, LOW);
  // 0x100D
  // 0x180D
}

void setup() {
  Serial.begin(1200);
  pinMode(IR_PIN, OUTPUT);
  irSender.begin(IR_PIN);
  pinMode(ledLAUT, OUTPUT);
  pinMode(3, OUTPUT);
}

void loop() {
  Serial.println(datenInput);
  switch (datenInput) {
  case 1:
    senden(tasten.POWER);
    // Power();
    break;
  case 2:
    Lauter();
    break;
  case 3:
    Leiser();
    break;
  case 4:
    ChanPlus();
    break;
  case 5:
    ChanMinus();
    break;
  case 6:
    InputMenu();
    break;
  case 7:
    Mute();
    break;

  default:
    break;
  }
}