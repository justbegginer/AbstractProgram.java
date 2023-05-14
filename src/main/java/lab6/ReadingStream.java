package lab6;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class ReadingStream extends Thread{
    private boolean isActive;
    protected static ReadingStream instance;
    private static LinkedBlockingDeque<String> readingRequest;
    protected ReadingStream(){
        readingRequest = new LinkedBlockingDeque<String>();
    }
    public synchronized String getElem(){
        return readingRequest.pop();
    }
    public synchronized boolean isEmpty(){
        return readingRequest.size() == 0;
    }
    public synchronized void print(String str){
        readingRequest.add(str);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
