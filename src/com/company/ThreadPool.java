package com.company;

import java.util.concurrent.*;

public class ThreadPool{
    private final ThreadPoolExecutor pool;
    public volatile boolean quit = false;
    static final int MAX_T = 7;
    private Main main;
    public ThreadPool(Main main){
        pool  = new ThreadPoolExecutor(MAX_T,MAX_T,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        Thread thread = new Thread(new ThreadChecker());
        thread.setDaemon(true);
        thread.start();
        this.main = main;
    }

    public void ShutDownThreads() {
        pool.purge();
        pool.shutdown();

    }
    public void queueThread(Runnable action){
        pool.execute(action);
    }
    private class ThreadChecker implements Runnable{

        @Override
        public void run() {
            while(!quit) {
                Thread.onSpinWait();
                if (!pool.isTerminating() && !pool.isTerminated() && !pool.isShutdown() && pool.getActiveCount() < MAX_T) {
                    main.queueFish();
                } else {
                    try {
                        Thread.sleep(15L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }
        }
    }


}
