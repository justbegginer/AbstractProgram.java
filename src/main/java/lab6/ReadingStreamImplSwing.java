package lab6;

public class ReadingStreamImplSwing extends ReadingStream {
    private ReadingStreamImplSwing() {
        super();
    }
    @Override
    public void run() {
        setActive(true);
        while (isActive() || !isEmpty()) {
            while (isEmpty()) {
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.refreshPage(getElem());
        }
    }

    public static ReadingStream getInstance() {
        if (instance == null) {
            instance = new ReadingStreamImplSwing();
        }
        return instance;
    }
}