#include <Stepper.h>  // step motorun sürülmesi için gerekli kütüphane
const int stepsPerRevolution = 500; //stepsPerRevolution adında değişken tanımlanmış ve içine 500 değeri atanmış

Stepper myStepper(stepsPerRevolution, 8, 10, 9, 11); //step motorun pals uclarının arduinoya bağlı oladuğu pin tanıtımı


const int trigPin = 13;//ULTRASONİC mesafe sensörünün echo ve trig pinlerinin arduino üzerinde bağlanacağı pinleri gösterir
const int echoPin = 12;

long duration; //burada 2 tane tam sayı değişken tanımlamışız
int distance;

void setup() {
  pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output // mesafe sensörünün arduino için giriş cıkış olarak belirlendiği bölüm
  pinMode(echoPin, INPUT); // Sets the echoPin as an Input
  myStepper.setSpeed(20); //step motorun hızını belirledik
  Serial.begin(9600);      //seri haberleşme hızını belirliyoruz
}
void loop() { //ana gövde
  for (int i = 0; i <= 360; i++) { // for döngüsünün içerisinde 0 dan 360 derece taraması için döngü kullandık
    myStepper.step(-5.69); //step adım değerini 5,69 azaltıyoruz
    delay(30);
    distance = ultra();// burada ultra fonksiyonun içindeki alınan mesafe değerini distance değişkenin içine atanır


  }
  for (int i = 360; i > 0; i--) { //bu for döngüsünde ise 360 dereceye kadar tarama işlemini yaptıktan sonra geri gelmesi için 360 dan geriye birer birer azaltıp parantez içindeki işlemleri yapar
    myStepper.step(5.69); // step adım değerini 5,69 attır
    delay(30);
    distance = ultra();// buarda ultra fonksiyonun içindeki alınan mesafe değerini distance değişkenin içine atanır


  }
}
// ultrasonic sensörün okunduğu fonksiyon burasıdır
int ultra() {

  digitalWrite(trigPin, LOW); //trigpin e 0 sinyali verilir
  delayMicroseconds(2); // 2 microsaniye bekleme komutu
  digitalWrite(trigPin, HIGH);// burada trigpin 10 mikrosaniye aktif edilir
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH); // echopinden okunan değeri duration değişkenin içine ata
  distance = duration * 0.034 / 2; //burada gerekli mesafe işlemleri yapılmıştır
  return distance;        //distance değişkenini döndür
}
