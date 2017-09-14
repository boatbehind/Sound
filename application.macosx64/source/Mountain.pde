class Mountain{
  
 ArrayList<PVector> pos;
 AnalyzeSound AS;
 int index;                    //index of 11 audio
 color col1, col2;
 float height1, height2;
 float time = 0;
 PVector[] vertices = new PVector[13];
 PVector[] verticTemp = new PVector[9];
 int verticesIndex = -1;
 boolean isSetting = false;
 boolean firstTime = true;
 Panel panel;
 
 Mountain(Object processing, color c1, color c2, float h1, float h2){
   index = 0;
   AS = new AnalyzeSound(processing, audio[index]);
   noiseSeed(12);
   col1 = c1;  col2 = c2;
   height1 = h1;  height2 = h2;
   panel = new Panel("m", col1);
   
 } 
  
  int temp;
  void update(){
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
          float a = map(n, 0, 1, -1, 0.5);
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
  
  void nextAudio(Object processing){
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
  
  void PlayPause(){
    if(isSetting){
      if(AS.player.isPlaying()){
        AS.player.pause(); 
      }
      else{
        AS.player.loop();
      }
    }
  }
  
  boolean isInsideMountain(PVector pos) {
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
