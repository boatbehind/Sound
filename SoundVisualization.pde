
import processing.video.*;
GUI gui;
Movie movie;
boolean moviePlaying;
float mouseMoveTime;

void setup(){
  size(1280, 720, OPENGL);
  noStroke();
  
  movie = new Movie(this, "startmov.m4v");
  movie.play();
  moviePlaying = true;
  frameRate(60); 
}

void draw(){
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

void update(){ 

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

void mouseClicked(){
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

void mouseWheel(MouseEvent event){
  float e = event.getCount();
  if(gui.mountain[3].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[3].isSetting){
      if(e == 1.0)        gui.mountain[3].AS.DecVolume();
      else if(e == -1.0)  gui.mountain[3].AS.IncVolume();
      gui.mountain[3].panel.change = true;
  }else if(gui.mountain[2].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[2].isSetting){
      if(e == 1.0)        gui.mountain[2].AS.DecVolume();
      else if(e == -1.0)  gui.mountain[2].AS.IncVolume();
      gui.mountain[2].panel.change = true;
  }else if(gui.mountain[1].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[1].isSetting){
      if(e == 1.0)        gui.mountain[1].AS.DecVolume();
      else if(e == -1.0)  gui.mountain[1].AS.IncVolume();
      gui.mountain[1].panel.change = true;
  }else if(gui.mountain[0].isInsideMountain(new PVector(mouseX, mouseY)) && gui.mountain[0].isSetting){
      if(e == 1.0)        gui.mountain[0].AS.DecVolume();
      else if(e == -1.0)  gui.mountain[0].AS.IncVolume();
      gui.mountain[0].panel.change = true;
  }else if(mouseY > 500){
      if(e == 1.0)        gui.river.AS.DecVolume();
      else if(e == -1.0)  gui.river.AS.IncVolume();
      gui.river.panel.change = true;
  }else if(mouseY < 200){
    if(e == 1.0){
      gui.mountain[3].AS.DecVolume();
      gui.mountain[2].AS.DecVolume();
      gui.mountain[1].AS.DecVolume();
      gui.mountain[0].AS.DecVolume();
      gui.river.AS.DecVolume();
    } 
    else if(e == -1.0){
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

void mouseMoved(){
 mouseMoveTime = millis(); 
}
