import processing.serial.*; // kütüphane entegresi
import java.awt.event.KeyEvent;
import java.io.IOException;

Serial myPort;

String angle="";
String distance="";
String data="";
String noObject;
float pixsDistance;
int iAngle, iDistance;
int index1=0;
int index2=0;
PFont orcFont;

void setup() {
  // background(255,10,10);
 size (550, 550);
 smooth();
 myPort = new Serial(this,"COM6", 9600); // Com portunu seçin
 myPort.bufferUntil('.');

}

void draw() {

  fill(98,245,31);

  noStroke();
  fill(0,4);
  rect(0, 0, width, 1010);

  fill(98,245,31); // yeþil renk

  drawRadar();
  drawLine();
  drawObject();

}

void serialEvent (Serial myPort) {

  data = myPort.readStringUntil('.');
  data = data.substring(0,data.length()-1);

  index1 = data.indexOf(",");
  angle= data.substring(0, index1);
  distance= data.substring(index1+1, data.length());


  iAngle = int(angle);
  iDistance = int(distance);
}

void drawRadar() {
  pushMatrix();
  translate(285,285);
  noFill();
  strokeWeight(2);
  stroke(98,245,31);
  // draws the arc lines

  ellipse(0, 0, 500, 500);
ellipse(0, 0, 400, 400);
ellipse(0, 0, 300, 300);
ellipse(0, 0, 200, 200);
  // draws the angle lines
  line(-250,0,250,0);
  line(0,0,-250*cos(radians(30)),-250*sin(radians(30)));
  line(0,0,-250*cos(radians(60)),-250*sin(radians(60)));
  line(0,0,-250*cos(radians(90)),-250*sin(radians(90)));
  line(0,0,-250*cos(radians(120)),-250*sin(radians(120)));
  line(0,0,-250*cos(radians(150)),-250*sin(radians(150)));
    line(0,0,-250*cos(radians(180)),-250*sin(radians(180)));
  line(0,0,-250*cos(radians(210)),-250*sin(radians(210)));
  line(0,0,-250*cos(radians(240)),-250*sin(radians(240)));
  line(0,0,-250*cos(radians(270)),-250*sin(radians(270)));
  line(0,0,-250*cos(radians(300)),-250*sin(radians(300)));
  line(0,0,-250*cos(radians(330)),-250*sin(radians(330)));
  line(0,0,-250*cos(radians(360)),-250*sin(radians(360)));

  line(-250*cos(radians(30)),0,250,0);
  popMatrix();
}

void drawObject() {
  pushMatrix();
    translate(285,285);
  strokeWeight(9);
  stroke(255,10,10); // kýrmýzý renk
  pixsDistance = iDistance*7.5;
  // 40 cm ye kadar ölçer
  if(iDistance<40){
  
  line(pixsDistance*cos(radians(iAngle)),-pixsDistance*sin(radians(iAngle)),248*cos(radians(iAngle)),-248*sin(radians(iAngle)));
  }
  popMatrix();
}

void drawLine() {
  pushMatrix();
  strokeWeight(9);
  stroke(30,250,60);
    translate(285,285);
  line(0,0,248*cos(radians(iAngle)),-248*sin(radians(iAngle)));
  popMatrix();
}