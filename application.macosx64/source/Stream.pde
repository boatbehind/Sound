class Stream{
  color colFill;
  float posX1, posX2;
  float posY;
  float angle;
  float amplitude;
  int index;
  
  
  Stream(float x1, float y, color cf, float a){
    float temp = random(40, 80);
    posX1     = x1 - temp/2;
    posX2     = x1 + temp/2;
    posY      = y;
    colFill   = cf;
    amplitude = a;
  }
  
  void update(color c){
    
    angle += 0.02;
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
