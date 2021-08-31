package com.company;

public class MoveFishRev extends ANSICodes implements Runnable{
    private String[] colors;
    private String[] fish;
    private int StartingRow;
    long delay; // sleep uses long
    public MoveFishRev(String[] fish,int startingRow,long delay,String[] colors){
        this.fish = new String[fish.length]; // initialize array to same length of the fish array
        this.colors = new String[colors.length];
        System.arraycopy(fish,0,this.fish,0,fish.length); // copies array over
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

    /**
     *  o(n) time complexity
     * @return longest length of the array
     */
    private int GetLongestLength(){
        int length =0;
        for (String string:fish) {
            length = Math.max(length, string.length());
        }
        return length;
    }

    @Override
    public void run() {
        //Finished on 7/23/2021
        int LongestLength = this.GetLongestLength();
        for(int i=0;i<LongestLength;i++){
           // System.out.println(String.format(cursorEscape,this.StartingRow+ fish.length,0,i)); debug
            for(int j=0; j< fish.length;j++) {
                if ((LongestLength - fish[j].length())<= i) {
                    if(fish[j].length()<LongestLength){
                        System.out.println(String.format(cursorEscape,this.StartingRow+j,0,colors[j]+fish[j].substring(Math.max(0,fish[j].length()-1-i+(LongestLength-fish[j].length())))+ANSICodes.colorReset));
                    }else
                        System.out.println(String.format(cursorEscape,this.StartingRow+j,0,colors[j]+fish[j].substring(Math.max(0,fish[j].length()-i-1))+ANSICodes.colorReset));
                }
            }
            if(i<LongestLength-1){
                try {
                Thread.sleep(delay);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        int currentLine = 1; // represent the current line the cursor is on
        while (currentLine<120){
            if (currentLine>0){
                for (int i =0; i<fish.length; i++){
                    System.out.println(String.format(cursorEscape,this.StartingRow+i,currentLine-1," ")); // working
                }
            }
            for (int i=0; i<fish.length;i++){
                System.out.println(String.format(cursorEscape,this.StartingRow+i,currentLine,colors[i]+fish[i].substring(0,fish[i].length()+currentLine>120?120-currentLine:fish[i].length()))+ANSICodes.colorReset);//); //fish[i].substring(0,Math.min(currentLine, fish[i].length())
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentLine++;
        }
        for(int i=0; i<fish.length;i++){
            System.out.println(String.format(cursorEscape,this.StartingRow+i,currentLine-1," ")); // clears the fish off the screen
        }


    }
}
