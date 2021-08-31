package com.company;

/*
    __       __
  \/ 0\     /0 \/
  /\__/     \__/\
 */

/*
   ..\,                 ,\..
>='   ('>
  '''/''
        >
         '>
         '
          ,
          ('>
          ''
 */
/*
  ,/..
<')   `=<
 ``\```
 */


import java.util.ArrayList;

public class Fish {
    static String[] fish ={" __","/0 \\/","\\__/\\"};
    static  String[] fish2 = {"<><"};                                                                       //  ><>->          <-<><
    static  String[]  fish3 = { "  ,/..", "<')   `=<"," ``\\```" };
    static  String[] revFish2 = {"><>"};
    static String[] revFish = {"  __","\\/ 0\\","/\\__/"};
    static String[] revFish3 ={ "   ..\\,",">='   ('>","  '''/''"}; // >='   ('>
    static String[] ColorFish = {"\u001b[33m","\u001b[33m","\u001b[33m"};
    static String[] colorFish2 = {"\u001b[33m"};
    static String[] colorFish3 = {"\u001b[36;1m","\u001b[31;1m","\u001b[32;1m"};


    public static String[] selectFish(int select){
        switch (select){
           case 1: return fish2;
           case 2: return fish3;
           default: return fish;
        }
    }
    public static  String[] selectFishRev(int select){
        switch (select){
            case 1: return revFish2;
            case 2: return revFish3;
            default: return revFish;
        }
    }
    public static String[] getColor(int length){
        ArrayList<String> colorList = new ArrayList<>(length);
        for(int i=0; i<length;i++){
            colorList.add(ANSICodes.grabColor());
        }
        return colorList.toArray(new String[0]);

    }

}
