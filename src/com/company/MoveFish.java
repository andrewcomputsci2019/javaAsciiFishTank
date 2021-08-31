package com.company;

public class MoveFish extends ANSICodes implements Runnable{
    private String[] fish;
    private int StartingRow;
    private String[] colors;
    long delay; // sleep uses long

    /**
     *
     * @param fish a string array that represent an asci art fish
     * @param startingRow a int that represent the row of where the fish should be drawn
     * @param delay a long that represent the speed of the fish
     */
    public MoveFish(String[] fish,int startingRow,long delay,String[] colors){
        this.fish = new String[fish.length]; // initialize array to same length of the fish array
        System.arraycopy(fish,0,this.fish,0,fish.length); // copies array over
        this.colors = new String[colors.length];
        System.arraycopy(colors,0,this.colors,0,colors.length);
        this.StartingRow = startingRow;
        this.delay = delay;
        if(this.fish.length+startingRow>super.maxRows){
            throw new IllegalStateException("fish length is greater than max rows");
        }
        if (delay> 250L){
            throw new IllegalArgumentException("delay is longer than 250, delay Value:"+delay);
        }
    }
    private int GetLongestLength(){
        int length =0;
        for (String string:fish) {
            length = Math.max(length, string.length());
        }
        return length;
    }

    /**
     * threading constructor/method
     */
    @Override
    public void run() {
        int clearFish = this.GetLongestLength();
        int currentLine = 120; // represent the current line the cursor is on
        while (currentLine>0){
            if (currentLine<120){
                for (int i =0; i<fish.length; i++){
                    System.out.println(String.format(cursorEscape,this.StartingRow+i,currentLine+fish[i].length()," "));
                }
            }
            for (int i=0; i<fish.length;i++){
                System.out.println(String.format(cursorEscape,this.StartingRow+i,currentLine,colors[i]+fish[i].substring(0,Math.min(121 - currentLine, fish[i].length()))+ANSICodes.colorReset));
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentLine--;
        }
        currentLine+=1;
        int counter = 1;
        while (clearFish>0){
            // str.length()-clearFish= starting point
            for (int i=0; i<fish.length;i++){
                if (fish[i].length()-counter>0) {
                    System.out.println(String.format(cursorEscape, this.StartingRow + i, currentLine,colors[i]+ fish[i].substring(counter)+ANSICodes.colorReset));
                }
            }
            for (int i=0; i<fish.length;i++){
                int Spacing;
                if ((Spacing=fish[i].length()-counter)>=0) {
                    System.out.println(String.format(cursorEscape, this.StartingRow + i, currentLine + Spacing," "));
                }
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
            clearFish--;
        }

    }
}
/*
 *     " __","/0 \\/","\\__/\\"
 *       __
 *      /0 \/
 *      \__/\
 */
/*
    <><
 */
/*
      ,/..
    <')   `=<
     ``\```
 */