package lab6;

import java.util.Random;


public class AbstractProgram extends Thread {
    private Status status = Status.UNKNOWN;
    private long counter;
    private Supervisor supervisor;
    private boolean isActive;
    private ReadingStream readingStream;

    public void build(Supervisor supervisor, ReadingStream readingStream) {
        this.supervisor = supervisor;
        this.readingStream = readingStream;
    }

    public Status getStatus() {
        return status;
    }

    public synchronized boolean isActive() {
        return isActive;
    }

    public synchronized void setActive(boolean active) {
        isActive = active;
    }

    public long getCounter() {
        return counter;
    }

    @Override
    public void run() {
        this.status = Status.UNKNOWN;
        this.setActive(true);
        Thread thread = new Thread(() -> {
            while (isActive) {
                try {
                    Thread.sleep(1000);
                    switch (new Random().nextInt(4)) {
                        case 1:
                            status = Status.RUNNING;
                            break;
                        case 2:
                            status = Status.STOPPING;
                            break;
                        case 3:
                            status = Status.FATAL_ERROR;
                            break;
                    }
                    readingStream.print("changed status");
                    synchronized (supervisor) {
                        supervisor.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        while (isActive()) {
            counter++;
        }
        readingStream.print("end");
    }
}
