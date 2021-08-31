package com.company;

import java.util.Random;

/**
 * class for randomly creating a fish
 */
public class RandomFish {



    public RandomFish(){

    }

    /**
     * a function that randomly creat a fish with random attributes
     * @return  a FishWrapper element
     * @see FishWrapper
     */
    public FishWrapper makeFish(){
        boolean direction = new Random().nextBoolean();
        if (direction){
            String[] array = Fish.selectFishRev(new Random().nextInt(3));
            int row;
            row = new Random().nextInt(25)+3;
            String[] colors = Fish.getColor(array.length);
            return new FishWrapper(array,colors, true,row);
        }else {
            String[] array = Fish.selectFish(new Random().nextInt(3));
            int row;
            String[] colors = Fish.getColor(array.length);
            row = new Random().nextInt(25) + 3;
            return new FishWrapper(array,colors, false, row);
        }
    }


}
