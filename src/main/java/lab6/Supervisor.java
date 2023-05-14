package lab6;

public class Supervisor extends Thread {
    private final AbstractProgram program;
    private ReadingStream readingStream;

    public Supervisor(AbstractProgram program, ReadingStream readingStream) {
        this.program = program;
        this.readingStream = readingStream;
        this.program.build(this, readingStream);
    }

    @Override
    public void run() {
        readingStream.setDaemon(true);
        readingStream.start();
        this.program.start();
        Status status = program.getStatus();
        readingStream.print(String.valueOf(status));
        System.out.println(status);
        while (true) {
            try {
                synchronized (this) {
                    this.wait();
                    readingStream.print("continue");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            status = program.getStatus();
            if (status == Status.FATAL_ERROR) {
                readingStream.print("==" + status + "==");
                readingStream.print("<-PROGRAM STOPPED->");
                readingStream.print("counter result is " + this.program.getCounter());
                this.program.setActive(false);
                this.readingStream.setActive(false);
                break;
            } else {
                readingStream.print("==" + status + "==");
            }
        }
    }
}
