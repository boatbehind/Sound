class Panel{
  color colBackground;
  boolean display = false;
  int alpha;
  PImage[] image = new PImage[4];
  boolean change = false;
  
  Panel(String flag, color col){
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
  
  void update(int name, int vol, String flag){
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
