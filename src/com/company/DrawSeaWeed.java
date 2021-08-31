package com.company;

import static com.company.ANSICodes.*;

/**
 * a Demon thread that will run in the back ground as long as the program is running
 */
public class DrawSeaWeed implements Runnable{
    /*
        (     )
         )   (
        (     )
         )   (
     */
    /*
             )
            (
            )
        (   )
        (
        )
     */

   // {"()()",")()("} old
    public String[] seaWeedAnimations = {"()()",")()("};
    public String seaWeedColor = "\u001b[32;1m";
    public int column;
    public volatile boolean exit = false;
    public DrawSeaWeed(int column){
        this.column = column;
    }

    @Override
    public void run() {
        while(!exit){
            for (String seaWeedAnimation : seaWeedAnimations) {
                int counter = seaWeedAnimation.length();
                for (char piece: seaWeedAnimation.toCharArray()){
                    System.out.println(String.format(cursorEscape,30-counter,column,seaWeedColor+piece+colorReset));
                    counter--;
                }
                try {
                    Thread.sleep(350L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(clear);
                }
            }
        }
    }
}
