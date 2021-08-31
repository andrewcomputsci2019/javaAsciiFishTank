package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ANSICodes {
    /**
     * maxRows of the console
     */
    public final int maxRows = 30;
    /**
     * max Columns of the console
     */
    public final int maxColumns = 120;
    public static String clear = "\033[2J";
    /**
     * Escape character for moving the cursor in console
     * row then column then the string to be followed
     */
    public static String cursorEscape = "\033[%d;%dH%s";//row then column then string
    public static String clearLine = "\033[2K";
    public static String colorReset = "\u001b[0m";
    private static final AnsiColors[] colors = AnsiColors.values().clone(); // look up table @TODO finish look up table
    private static final Random random = new Random();

    /**
     * Wrapper function for getColor
     * @see #getColor(int)
     * @return string of the prefix of the ansi color
     */
    public static String grabColor(){
       return getColor(random.nextInt(colors.length)).getCode();
    }

    /**
     * see enum class for all enums {@link AnsiColors}
     * @param value the index of the array returns a enum value
     * @return  <font color='green' size=4>a enum of AnsiColor</>
     */
    private static AnsiColors getColor(int value){
        assert value>=0 && value<colors.length:"Out of bounds Index";
        return colors[value];

    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(colors));
        System.out.println(grabColor()+"this is some text");
    }

}
