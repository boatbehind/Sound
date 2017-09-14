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
     
     
color[] morning1   = {color(98,110,135), color(85,94,119), color(71,76,107), color(58,55,84), color(114,132,156)};
color[] forenoon1  = {color(84,112,92),color(71,97,78),color(57,83,67),color(48,75,61),color(116,150,126)};
color[] afternoon1 = {color(140,88,75), color(118,67,53), color(92,47,39), color(75,31,24), color(174,119,106)};
color[] evening1   = {color(140,88,75), color(118,67,53), color(92,47,39), color(75,31,24), color(174,119,106)};
color[] midnight1  = {color(98,110,135), color(85,94,119), color(71,76,107), color(58,55,84), color(114,132,156)};

color[] morning2   = {color(127,135,151), color(123,137,155), color(116,129,150), color(112,129,153), color(98,106,123)};
color[] forenoon2  = {color(118,153,129),color(106,138,115),color(105,137,114),color(113,147,123),color(97,127,104)};
color[] afternoon2 = {color(153,102,90), color(149,95,84), color(150,98,85), color(175,121,108), color(141,93,78)};
color[] evening2   = {color(153,102,90), color(149,95,84), color(150,98,85), color(175,121,108), color(141,93,78)};
color[] midnight2  = {color(127,135,151), color(123,137,155), color(116,129,150), color(112,129,153), color(98,106,123)};

color[][] mountainCol1 = {morning1, forenoon1, afternoon1, evening1, midnight1};
color[][] mountainCol2 = {morning2, forenoon2, afternoon2, evening2, midnight2};

String[] imageStr = {"morning.png", "forenoon.png", "afternoon.png", "evening.png", "midnight.png"};

int[] noise = {(int)random(0,100), (int)random(0,100), (int)random(0,100), (int)random(0,100)};

private static final float NOISE_SCALE = .007f;
private static final float TIME_STEP = 0.0001f;
private static final float AMPLITUDE = 230f;
