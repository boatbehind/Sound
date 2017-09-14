class River{
  AnalyzeSound AS;
  int index;
  boolean isSetting;
  Panel panel;
  color col1, col2;
  
  River(Object processing, color c1, color c2){
    isSetting = false;
    index = 0;//"liu.mp3"
    AS = new AnalyzeSound(processing, audioRiver[index]);
    AS.play();
    col1 = c1;  col2 = c2;
    panel = new Panel("m", col1);
    panel.change = true;
  }   
  
  void update(){
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
  
  void nextAudio(Object processing){
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
  
  void PlayPause(){
    if(isSetting){
      if(AS.player.isPlaying())
        AS.player.pause(); 
      else
        AS.player.loop();
    }
  }
  
}
