package lab1;

public interface Move {
    String move(Point a, Point b);

    String errorMessage(int errorCode);
}