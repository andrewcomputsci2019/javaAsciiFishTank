package com.company;

import java.util.*;


/**
 * main class that runs the ascii art aquarium keep in mind that this only works in non dev consoles use jar file to see product
 * this will manage the thread pool for the fish and other static elements
 * only works in ansi accepting terminals: git bash windows terminal bash etc
 */
public class Main {
    static RandomFish randomFish = new RandomFish();
    static String clear = "\033[2J";
    static String cursorEscape = "\033[%d;%dH%s";//row then column then string
    static ThreadPool pool;
    static Random random = new Random();
    public static void main(String[] args) throws InterruptedException {
        if(args.length>0){
            if(args[0].equalsIgnoreCase("debug")){
                System.out.println("\033[2J");
                debuging();
                System.exit(0);
            }
        }
        System.out.println("\033[2J");
        System.out.println(String.format(cursorEscape,5,6," hello")); // rows then columns
        System.out.print(clear);
        for (int i=120; i>0;i--){
            for (int j = 0; j< Fish.fish.length; j++){
                System.out.println(String.format(cursorEscape,j+1,i, Fish.fish[j].substring(0, Math.min(121 - i, Fish.fish[j].length()))));
            }
            Thread.sleep(25);
            System.out.print(clear);
        }
        for (int i=120; i>0;i--){
            for (int j = 0; j< Fish.fish2.length; j++){
                System.out.println(String.format(cursorEscape,j+1,i, Fish.fish2[j]));
            }
            Thread.sleep(25);
            System.out.print(clear);
        }
        for (int i=120; i>0;i--){
            for (int j=0; j<Fish.fish3.length;j++){
                System.out.println(String.format(cursorEscape,j+1,i,Fish.fish3[j]));
            }
            Thread.sleep(25);
            System.out.println(clear);
        }
        MoveFish moveFish = new MoveFish(Fish.fish, 0,75L,Fish.getColor(Fish.fish.length));
        MoveFish moveFish1 = new MoveFish(Fish.fish2, 4,50L,Fish.getColor(Fish.fish2.length));
        MoveFishRev moveFishRev = new MoveFishRev(Fish.revFish3,7,250L,Fish.getColor(Fish.revFish3.length));
        Thread thread = new Thread(moveFish);
        Thread thread1 =  new Thread(moveFish1);
        Thread thread2 = new Thread(moveFishRev);
        thread1.start();
        thread.start();
        thread2.start();
        thread.join();
        thread1.join();
        thread2.join();
        DrawSeaWeed seaWeed = new DrawSeaWeed(30);
        Thread seaWeedThread1 = new Thread(seaWeed);
        Thread seaWeedThread2 = new Thread(new DrawSeaWeed(60));
        seaWeedThread1.setDaemon(true);// Daemon
        seaWeedThread2.setDaemon(true);
        seaWeedThread2.start();
        seaWeedThread1.start();
        pool = new ThreadPool(new Main());
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
               Main.Quit();
            }
        };
        Thread quitThread = new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                if (scanner.nextLine().equalsIgnoreCase("q")){
                    System.out.println(ANSICodes.clear);
                    Quit();
                    break;
                }
            }
        });
        quitThread.start();
       /* //different syntax outdated to -> functions
       Thread quitThread1 = new Thread(){
            public void run(){
                Scanner scanner =new Scanner(System.in);
                while (scanner.hasNext()){
                    if (scanner.nextLine().equalsIgnoreCase("q")){
                        Quit();
                    }
                }
            }
        };*/
       // Timer timer = new Timer();
       // timer.schedule(task,10000L);
    }
    /**
     * call back method to quit the program
     */
    public static void Quit(){
        pool.quit = true;
        pool.ShutDownThreads();
        System.gc();
    }

    /**
     * callBack to queue another item
     */
    public void queueFish(){
       FishWrapper wrapper = randomFish.makeFish();
       if(wrapper.direction){
           pool.queueThread(new MoveFishRev(wrapper.fish, wrapper.startingRow,random.nextInt(120)+25,wrapper.colors));
       }else
        pool.queueThread(new MoveFish(wrapper.fish, wrapper.startingRow,random.nextInt(120)+25,wrapper.colors));
    }
    public static void debuging() {
        MoveFishRev moveFishRev = new MoveFishRev(Fish.revFish2, 7, 70L,Fish.getColor(Fish.revFish2.length));
        Thread thread = new Thread(moveFishRev);
        thread.start();
        try {
            thread.join();
        }catch (Exception e){
            System.out.println("Thread had an issue");
        }
    }

}
//@TODO add code to color fish on the screen