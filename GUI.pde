
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
  
  void update(Object processing){
    
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
  
  void changeTime(Object processing){
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

