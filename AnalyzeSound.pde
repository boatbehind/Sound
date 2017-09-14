import ddf.minim.analysis.*;
import ddf.minim.*;

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
  
  void update(){
    fft.forward(player.mix);
    for(int i = 0; i < fft.specSize(); i++){
      tone[i] = fft.getBand(i);
    }
  }
  
  void play(){
    player.loop();
  }
  
  void pause(){
    player.pause();
  }
  
  void DecVolume(){
      if(volume > -80.0)
        volume --;
      player.setGain(volume);
  }
  
  void IncVolume(){
      if(volume < 12.0)
        volume ++;
      player.setGain(volume);
  } 
  
}
