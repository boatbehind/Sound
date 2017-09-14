import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.video.*; 
import ddf.minim.analysis.*; 
import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SoundVisualization extends PApplet {



GUI gui;
Movie movie;
boolean moviePlaying;
float mouseMoveTime;

public void setup(){
  size(1280, 720, OPENGL);
  noStroke();
  
  movie = new Movie(this, "startmov.m4v");
  movie.play();
  moviePlaying = true;
  frameRate(60); 
}

public void draw(){
  if(movie.time() < movie.duration() && moviePlaying){
    if(movie.available() == true){
      movie.read();
      image(movie, 0, 0, width, height);
    }
  }else{
    if(moviePlaying){
      moviePlaying = false;
      gui = new GUI(this);
    }
    background(color(mountainCol1[gui.index][4], gui.alpha));
    gui.update(this);
    update();
  } 
}

public void update(){ 

     gui.skySetting = false;
     gui.mountain[3].isSetting    = false;
     gui.mountain[2].isSetting    = false;
     gui.mountain[1].isSetting    = false;
     gui.mountain[0].isSetting    = false;
    
    if(gui.mountain[3].isInsideMountain(new PVector(mouseX, mouseY))){
       gui.mountain[3].isSetting    = true;
    }else if(gui.mountain[2].isInsideMountain(new PVector(mouseX, mouseY))){
       gui.mountain[2].isSetting    = true;
    }else if(gui.mountain[1].isInsideMountain(new PVector(mouseX, mouseY))){
       gui.mountain[1].isSetting    = true;
    }else if(gui.mountain[0].isInsideMountain(new PVector(mouseX, mouseY))){
       gui.mountain[0].isSetting    = true;
    }else if(mouseY < 200){
       gui.skySetting = true;
    }
    
  if(mouseY > 500){
    gui.river.isSetting = true;
  }else{
    gui.river.isSetting = false;
  }
  
  if(gui.change){
//    gui.changeTime(this);
    gui.index = ++gui.index % 5;
    gui.imageForeground = loadImage(imageStr[gui.index]);
    gui.mountain[0].col1 = color(mountainCol1[gui.index][0], 0);  gui.mountain[0].col2 = color(mountainCol2[gui.index][0], 0); 
    gui.mountain[1].col1 = color(mountainCol1[gui.index][1], 0);  gui.mountain[1].col2 = color(mountainCol2[gui.index][1], 0); 
    gui.mountain[2].col1 = color(mountainCol1[gui.index][2], 0);  gui.mountain[2].col2 = color(mountainCol2[gui.index][2], 0); 
    gui.mountain[3].col1 = color(mountainCol1[gui.index][3], 0);  gui.mountain[3].col2 = color(mountainCol2[gui.index][3], 0); 
    gui.river.col1 = mountainCol1[gui.index][4];  gui.river.col2 = mountainCol2[gui.index][4];
    gui.panel.change = true;
  }   
}

public void mouseClicked(){
  if(moviePlaying){
    moviePlaying = false;
    gui = new GUI(this);
  }else{
    if(mouseButton == LEFT)
    {
      if(gui.mountain[3].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[3].PlayPause();
      }else if(gui.mountain[2].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[2].PlayPause(); 
      }else if(gui.mountain[1].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[1].PlayPause();
      }else if(gui.mountain[0].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[0].PlayPause();
      }else if(mouseY > 500){
         gui.river.PlayPause();
      }
    }else if(mouseButton == RIGHT){
      if(gui.mountain[3].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[3].nextAudio(this);
      }else if(gui.mountain[2].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[2].nextAudio(this); 
      }else if(gui.mountain[1].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[1].nextAudio(this);
      }else if(gui.mountain[0].isInsideMountain(new PVector(mouseX, mouseY))){
         gui.mountain[0].nextAudio(this);
      }else if(mouseY > 500){
         gui.river.nextAudio(this); 
      }
    }
    
    if(gui.mountain[0].isInsideMountain(new PVector(mouseX,mouseY))){
      ;
    }else if(mouseY<200 && mouseButton ==RIGHT){
      gui.changeTimeBool = true;
    }
  }
 mouseMoveTime = millis(); 
}

public void mouseWheel(MouseEvent event){
  float e = event.getCount();
  if(gui.mountain[3].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[3].isSetting){
      if(e == 1.0f)        gui.mountain[3].AS.DecVolume();
      else if(e == -1.0f)  gui.mountain[3].AS.IncVolume();
      gui.mountain[3].panel.change = true;
  }else if(gui.mountain[2].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[2].isSetting){
      if(e == 1.0f)        gui.mountain[2].AS.DecVolume();
      else if(e == -1.0f)  gui.mountain[2].AS.IncVolume();
      gui.mountain[2].panel.change = true;
  }else if(gui.mountain[1].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[1].isSetting){
      if(e == 1.0f)        gui.mountain[1].AS.DecVolume();
      else if(e == -1.0f)  gui.mountain[1].AS.IncVolume();
      gui.mountain[1].panel.change = true;
  }else if(gui.mountain[0].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[0].isSetting){
      if(e == 1.0f)        gui.mountain[0].AS.DecVolume();
      else if(e == -1.0f)  gui.mountain[0].AS.IncVolume();
      gui.mountain[0].panel.change = true;
  }else if(mouseY > 500){
      if(e == 1.0f)        gui.river.AS.DecVolume();
      else if(e == -1.0f)  gui.river.AS.IncVolume();
      gui.river.panel.change = true;
  }else if(mouseY < 200){
    if(e == 1.0f){
      gui.mountain[3].AS.DecVolume();
      gui.mountain[2].AS.DecVolume();
      gui.mountain[1].AS.DecVolume();
      gui.mountain[0].AS.DecVolume();
      gui.river.AS.DecVolume();
    } 
    else if(e == -1.0f){
      gui.mountain[3].AS.IncVolume();
      gui.mountain[2].AS.IncVolume();
      gui.mountain[1].AS.IncVolume();
      gui.mountain[0].AS.IncVolume();
      gui.river.AS.IncVolume();
    }
    
    gui.volume = (int)gui.mountain[0].AS.volume;
    for(int i=0; i<4; i++){
      if(gui.volume <= gui.mountain[i].AS.volume){
        gui.volume = (int)gui.mountain[i].AS.volume;
        gui.panel.change = true;
      }
    }
    if(gui.volume <= gui.river.AS.volume){
       gui.volume = (int)gui.river.AS.volume;
       gui.panel.change = true; 
    }
  }
 mouseMoveTime = millis();  
}

public void mouseMoved(){
 mouseMoveTime = millis(); 
}



class AnalyzeSound{
  AudioPlayer player;
  float[]     tone;
  float       volume;
  Minim       minim;
  FFT         fft;
  boolean     isSetting = false;
  
  AnalyzeSound(Object processing, String fileName){ 
    minim = new Minim(processing);
    player = minim.loadFile(fileName, 1024);
    fft = new FFT(player.bufferSize(), player.sampleRate());
    volume = -11;
    tone = new float[513];
  }
  
  public void update(){
    fft.forward(player.mix);
    for(int i = 0; i < fft.specSize(); i++){
      tone[i] = fft.getBand(i);
    }
  }
  
  public void play(){
    player.loop();
  }
  
  public void pause(){
    player.pause();
  }
  
  public void DecVolume(){
      if(volume > -80.0f)
        volume --;
      player.setGain(volume);
  }
  
  public void IncVolume(){
      if(volume < 12.0f)
        volume ++;
      player.setGain(volume);
  } 
  
}
AudioPlayer AP_bo;
AudioPlayer AP_chuo;
AudioPlayer AP_dong;
AudioPlayer AP_ling;
AudioPlayer AP_liu;
AudioPlayer AP_qin;
AudioPlayer AP_tiao;
AudioPlayer AP_xian;
AudioPlayer AP_xiao;
AudioPlayer AP_yuan;
AudioPlayer AP_yue;
AudioPlayer AP_zhong;

String      Str_bo   = "bo.mp3";
String      Str_chuo = "chuo.mp3";
String      Str_dong = "dong.mp3";
String      Str_ling = "ling.mp3";
String      Str_liu  = "liu.mp3";
String      Str_qin  = "qin.mp3";
String      Str_tiao = "tiao.mp3";
String      Str_xian = "xian.mp3";
String      Str_xiao = "xiao.mp3";
String      Str_yuan = "yuan.mp3";
String      Str_yue  = "yue.mp3";
String      Str_zhong= "zhong.mp3";

//String[] audio = { "chuo.mp3", "dong.mp3", "ling.mp3", "liu.mp3", 
//                   , "xiao.mp3",  "yue.mp3", };
                  
String[] audio = {"tiao.mp3", "yuan.mp3","qin.mp3","bo.mp3","zhong.mp3", "xian.mp3", "chuo.mp3", "dong.mp3", "ling.mp3",  "yue.mp3", "xiao.mp3", "liu.mp3"}; 
String[] audioRiver = {"liu.mp3","tiao.mp3","yuan.mp3","qin.mp3","bo.mp3","zhong.mp3","xian.mp3","chuo.mp3","dong.mp3","ling.mp3","yue.mp3","xiao.mp3"};

String[] type = {"le.png", "se.png", "yin.png"};
String[] yanse = {"li.png", "chen.png", "wu.png", "xia.png", "mu.png"};
String[] yinyueM = {"tiao.png", "yuan.png","qin.png","bo.png","zhong.png", "xian.png", "chuo.png", "dong.png", "ling.png",  "yue.png", "xiao.png", "liu.png"};
String[] yinyueR = {"liu.png","tiao.png","yuan.png","qin.png","bo.png","zhong.png","xian.png","chuo.png","dong.png","ling.png","yue.png","xiao.png"};
String[] yinliang= {"zixiao.png", "zizhongxiao.png", "zizhong.png", "zizhongda.png", "zida.png"};  
     
     
int[] morning1   = {color(98,110,135), color(85,94,119), color(71,76,107), color(58,55,84), color(114,132,156)};
int[] forenoon1  = {color(84,112,92),color(71,97,78),color(57,83,67),color(48,75,61),color(116,150,126)};
int[] afternoon1 = {color(140,88,75), color(118,67,53), color(92,47,39), color(75,31,24), color(174,119,106)};
int[] evening1   = {color(140,88,75), color(118,67,53), color(92,47,39), color(75,31,24), color(174,119,106)};
int[] midnight1  = {color(98,110,135), color(85,94,119), color(71,76,107), color(58,55,84), color(114,132,156)};

int[] morning2   = {color(127,135,151), color(123,137,155), color(116,129,150), color(112,129,153), color(98,106,123)};
int[] forenoon2  = {color(118,153,129),color(106,138,115),color(105,137,114),color(113,147,123),color(97,127,104)};
int[] afternoon2 = {color(153,102,90), color(149,95,84), color(150,98,85), color(175,121,108), color(141,93,78)};
int[] evening2   = {color(153,102,90), color(149,95,84), color(150,98,85), color(175,121,108), color(141,93,78)};
int[] midnight2  = {color(127,135,151), color(123,137,155), color(116,129,150), color(112,129,153), color(98,106,123)};

int[][] mountainCol1 = {morning1, forenoon1, afternoon1, evening1, midnight1};
int[][] mountainCol2 = {morning2, forenoon2, afternoon2, evening2, midnight2};

String[] imageStr = {"morning.png", "forenoon.png", "afternoon.png", "evening.png", "midnight.png"};

int[] noise = {(int)random(0,100), (int)random(0,100), (int)random(0,100), (int)random(0,100)};

private static final float NOISE_SCALE = .007f;
private static final float TIME_STEP = 0.0001f;
private static final float AMPLITUDE = 230f;

class GUI{
  int index;  //stands for morning1(0), afternoon1(1), evening1(2), midnight1(3);
  Mountain[] mountain = new Mountain[4];
  PImage[] streamImage = new PImage[5];
//  ArrayList<Stream> stream = new ArrayList<Stream>();
  River river;
  boolean changeTimeBool;
  boolean changeTimeTemp;
  boolean change;
  int alpha;
  PImage imageForeground;
  Panel panel;
  int volume;
  boolean skySetting;
  
  GUI(Object processing){
      index = 0;
      mountain[0] = new Mountain(processing, color(mountainCol1[index][0],0), color(mountainCol2[index][0],0), 250, 500);
      mountain[1] = new Mountain(processing, color(mountainCol1[index][1],0), color(mountainCol2[index][1],0), 300, 500);
      mountain[2] = new Mountain(processing, color(mountainCol1[index][2],0), color(mountainCol2[index][2],0), 350, 500);
      mountain[3] = new Mountain(processing, color(mountainCol1[index][3],0), color(mountainCol2[index][3],0), 400, 500); 
//      int numRiv = (int)random(180, 200);
//      for(int n = 0; n < numRiv; n++){
//        float x = random(0, width);
//        float y = random(520, 700);
//        if((x<220 && y-x*9/25>520) || (x>1060 && y+x*9/25>1000)){
//          numRiv++;
//        }else
//          stream.add(new Stream(x, y, mountainCol1[index][0], random(5, 8)));
//      }
      for(int i=0; i<5; i++)
        streamImage[i] = loadImage(i+".png");
      
      river = new River(processing, mountainCol1[index][4], mountainCol2[index][4]);
      changeTimeBool = true;
      changeTimeTemp = true;
      change = false;
      skySetting = false;
      alpha = 5;
      volume = (int)mountain[0].AS.volume;
      panel = new Panel("s", color(mountainCol1[index][4], alpha));
      
      imageForeground = loadImage(imageStr[index]);
  }
  
  public void update(Object processing){
    
    tint(255, alpha);
    image(streamImage[index], 0, 500, 1280, 220);
    
    //sun
    fill(200, alpha);
    ellipse(280, 150, 60, 60);
    
    tint(255, 255);
    for(Mountain m : mountain){
      m.update();
    }
    tint(100, 50);
    panel.update(gui.index, volume, "s");
    if(skySetting && millis()-mouseMoveTime < 10000)
      panel.display = true;
    else
      panel.display = false;
    
    tint(255, 255);
    river.update();
    
    tint(150, map(alpha,0,255,0,127));
    image(streamImage[index], 0, 500, 1280, 220);
    
//    for(Stream s : stream){
//      s.update(color(mountainCol1[index][0], alpha));
//    }
    
    tint(255,50);
    image(imageForeground, 0, 0, 1280, 720);
    
    
    
    if(changeTimeBool){
      if(alpha > 0 && !changeTimeTemp){
        alpha -= 5;
        for(int i=0; i<4; i++){
          mountain[0].col1 = color(mountainCol1[index][0], alpha);  mountain[0].col2 = color(mountainCol2[index][0], alpha); 
          mountain[1].col1 = color(mountainCol1[index][1], alpha);  mountain[1].col2 = color(mountainCol2[index][1], alpha); 
          mountain[2].col1 = color(mountainCol1[index][2], alpha);  mountain[2].col2 = color(mountainCol2[index][2], alpha); 
          mountain[3].col1 = color(mountainCol1[index][3], alpha);  mountain[3].col2 = color(mountainCol2[index][3], alpha); 
        }
      }else if(alpha < 255){
       changeTimeTemp = true;
       if(alpha == 0){
//         changeTime(processing);  //I don't know why is wrong here...
           change = true;
       }else{
           change = false; 
       }
        alpha+=5;
        mountain[0].col1 = color(mountainCol1[index][0], alpha);  mountain[0].col2 = color(mountainCol2[index][0], alpha); 
        mountain[1].col1 = color(mountainCol1[index][1], alpha);  mountain[1].col2 = color(mountainCol2[index][1], alpha); 
        mountain[2].col1 = color(mountainCol1[index][2], alpha);  mountain[2].col2 = color(mountainCol2[index][2], alpha); 
        mountain[3].col1 = color(mountainCol1[index][3], alpha);  mountain[3].col2 = color(mountainCol2[index][3], alpha);       
      }else{
        changeTimeBool = false;
        changeTimeTemp = false;
      }
    }
  }
  
  public void changeTime(Object processing){
//    index = ++index % 5;
//    imageForeground = loadImage(imageStr[index]);
//    mountain[0] = new Mountain(processing, color(mountainCol1[index][0],0), color(mountainCol2[index][0],0), 250, 500);
//    mountain[1] = new Mountain(processing, color(mountainCol1[index][1],0), color(mountainCol2[index][1],0), 300, 500);
//    mountain[2] = new Mountain(processing, color(mountainCol1[index][2],0), color(mountainCol2[index][2],0), 350, 500);
//    mountain[3] = new Mountain(processing, color(mountainCol1[index][3],0), color(mountainCol2[index][3],0), 400, 500); 
//    
//    river = new River(processing, mountainCol1[index][4], mountainCol2[index][4]);
//    
//    panel.change = true;
  }
}

class Mountain{
  
 ArrayList<PVector> pos;
 AnalyzeSound AS;
 int index;                    //index of 11 audio
 int col1, col2;
 float height1, height2;
 float time = 0;
 PVector[] vertices = new PVector[13];
 PVector[] verticTemp = new PVector[9];
 int verticesIndex = -1;
 boolean isSetting = false;
 boolean firstTime = true;
 Panel panel;
 
 Mountain(Object processing, int c1, int c2, float h1, float h2){
   index = 0;
   AS = new AnalyzeSound(processing, audio[index]);
   noiseSeed(12);
   col1 = c1;  col2 = c2;
   height1 = h1;  height2 = h2;
   panel = new Panel("m", col1);
   
 } 
  
  int temp;
  public void update(){
    AS.update(); 
    noStroke();
    beginShape();
    fill(col1);
    curveVertex(0, height1);
    vertices[++verticesIndex] = new PVector(0, height1);
    int vTemp = -1;
    if(AS.player.isPlaying() || firstTime){   
      float x;
      for(x = 0; x < width + 20; x +=160 ) {
          time += TIME_STEP;
          float n = noise(x * NOISE_SCALE + time, height1 * NOISE_SCALE - time, time);
          float a = map(n, 0, 1, -1, 0.5f);
          float dispy = height1 + a * AMPLITUDE;
          fill(col1);
          verticTemp[++vTemp] = new PVector(x, dispy);
          curveVertex(x, dispy);
          vertices[++verticesIndex] = new PVector(x, dispy);
      }
     firstTime = false; 
    }
    else{
      for(int j=0; j<9; j++)
      {
          curveVertex(verticTemp[j].x, verticTemp[j].y);
          vertices[++verticesIndex] = new PVector(verticTemp[j].x, verticTemp[j].y);
      } 
    }
    fill(col1);
    curveVertex(width, height1);
    vertices[++verticesIndex] = new PVector(width, height1);
    vertices[++verticesIndex] = new PVector(width, height2); 
    fill(col2);
    for(int w=width; w>0; w--){
      vertex(w, height2);     
    }
    vertices[++verticesIndex] = new PVector(0, height2); 
    endShape(CLOSE);
    verticesIndex = -1;
    
    panel.update(index, (int)AS.volume, "m");
    if(isSetting && millis()-mouseMoveTime < 10000){
      panel.display = true;
    }
    else
      panel.display = false;
  }
  
  public void nextAudio(Object processing){
    if(isSetting){
      boolean tempPlaying = false;
      if(AS.player.isPlaying())
        tempPlaying = true;
      AS.player.close();
      if(mouseX > width/2)
        index = ++index % 11;
      else if(mouseX <= width/2){
        index = --index;
        if(index<0)  index = 11;
      }
      AS = new AnalyzeSound(processing, audio[index]);
      if(tempPlaying)
        AS.player.loop();
        
      panel.change = true;
    }
  }
  
  public void PlayPause(){
    if(isSetting){
      if(AS.player.isPlaying()){
        AS.player.pause(); 
      }
      else{
        AS.player.loop();
      }
    }
  }
  
  public boolean isInsideMountain(PVector pos) {
    int i, j=vertices.length-1;
    int sides = vertices.length;
    boolean oddNodes = false;
    for (i=0; i<sides; i++) {
        if ((vertices[i].y < pos.y && vertices[j].y >= pos.y || vertices[j].y < pos.y && vertices[i].y >= pos.y) && (vertices[i].x <= pos.x || vertices[j].x <= pos.x)) {
              oddNodes^=(vertices[i].x + (pos.y-vertices[i].y)/(vertices[j].y - vertices[i].y)*(vertices[j].x-vertices[i].x)<pos.x); 
        }
        j=i; 
    }
    return oddNodes;
  }
}
class Panel{
  int colBackground;
  boolean display = false;
  int alpha;
  PImage[] image = new PImage[4];
  boolean change = false;
  
  Panel(String flag, int col){
    colBackground = col;
    image[0] = loadImage(yinliang[3]);
    image[1] = loadImage(type[2]);
    if(flag == "m")
      image[2] = loadImage(yinyueM[0]);
    else if(flag == "r")
      image[2] = loadImage(yinyueR[0]);
    else if(flag == "s")
      image[2] = loadImage(yanse[0]);
    if(flag == "m")
      image[3] = loadImage(type[0]);
    else if(flag == "s")
      image[3] = loadImage(type[1]);
  } 
  
  public void update(int name, int vol, String flag){
    if(display){
      if(alpha < 170)
        alpha += 50; 
    }
    else{
      if(alpha > 0)
        alpha -= 50;
    }
    
    if(change){
      int temp=0;
      for(temp=0; temp<5; temp++){
        if(-80+temp*23 >= vol)
          break;
      }
      image[0] = loadImage(yinliang[temp]);
      image[1] = loadImage(type[2]);
      if(flag == "m"){
        image[2] = loadImage(yinyueM[name]);
      }
      else if(flag == "r"){
        image[2] = loadImage(yinyueR[name]);
      }
      else if(flag == "s"){
        image[2] = loadImage(yanse[name]);
      }
      if(flag == "m")
        image[3] = loadImage(type[0]);
      else if(flag == "s")
        image[3] = loadImage(type[1]);
       
        alpha = 170;
        change = false;
    }
//    
//    if(flag == "m"){
//      fill(color(mountainCol1[gui.index][4], gui.alpha));
//      noStroke();
//      rect(1120, 0, 80, 120);
//    }
    
    tint(255, alpha);  // Apply transparency without changing color
    image(image[0], 1047, 28, 60, 60);
    image(image[1], 1100, 17, 45, 150);
    image(image[2], 1157, 24, 60, 60);
    image(image[3], 1210, 20, 45, 150);
    
  }
}
class River{
  AnalyzeSound AS;
  int index;
  boolean isSetting;
  Panel panel;
  int col1, col2;
  
  River(Object processing, int c1, int c2){
    isSetting = false;
    index = 0;//"liu.mp3"
    AS = new AnalyzeSound(processing, audioRiver[index]);
    AS.play();
    col1 = c1;  col2 = c2;
    panel = new Panel("m", col1);
    panel.change = true;
  }   
  
  public void update(){
    AS.update();
       
    beginShape();
    fill(col1);
    for(int w=0; w<width; w++)
       vertex(w, 500);
    fill(col2);
    for(int w=width; w>0; w--)
       vertex(w,720);
    endShape();
    
    panel.update(index, (int)AS.volume, "r");
    if(isSetting && millis()-mouseMoveTime < 10000){
      panel.display = true;
    }
    else
      panel.display = false;
  }
  
  public void nextAudio(Object processing){
    if(isSetting){
      boolean tempPlaying = false;
      if(AS.player.isPlaying())
        tempPlaying = true;
      AS.player.close();
      if(mouseX > width/2){
        index = ++index;
        if(index>11)  index = 0;
      }
      else if(mouseX <= width/2){
        index = --index;
        if(index<0)  index = 11;
      }
      AS = new AnalyzeSound(processing, audioRiver[index]);
      if(tempPlaying)
        AS.player.loop();
        
      panel.change = true;
    }
  }
  
  public void PlayPause(){
    if(isSetting){
      if(AS.player.isPlaying())
        AS.player.pause(); 
      else
        AS.player.loop();
    }
  }
  
}
class Stream{
  int colFill;
  float posX1, posX2;
  float posY;
  float angle;
  float amplitude;
  int index;
  
  
  Stream(float x1, float y, int cf, float a){
    float temp = random(40, 80);
    posX1     = x1 - temp/2;
    posX2     = x1 + temp/2;
    posY      = y;
    colFill   = cf;
    amplitude = a;
  }
  
  public void update(int c){
    
    angle += 0.02f;
    colFill = c;
    float x = angle;
    for(float i = posX1; i < posX2; i++){
      float y = sin(x) * amplitude;
      x += PI / 36;
      noStroke();
      fill(colFill, 100*sin(map(i-posX1, 0, posX2-posX1, 0, PI)));
      ellipse(i, posY + y, 4, 4);
    }
    
  } 
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#0D0C0C", "--stop-color=#cccccc", "SoundVisualization" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
