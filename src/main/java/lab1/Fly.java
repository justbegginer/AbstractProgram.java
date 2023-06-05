package lab1;

public class Fly implements Move {

    @Override
    public String move(Point a, Point b) {
        if (a.getZ() - b.getZ() > 0) {
            return errorMessage(2);
        } else if (a.getZ() - b.getZ() > 50) {
            return errorMessage(1);
        } else {
            if (Point.getDistance(a, b) > 75) {
                return errorMessage(3);
            } else {
                //System.out.println("I walk from Point: " + a.getCoordinatesMessage() + " to Point " + b.getCoordinatesMessage());
                return "Moved successfully";
            }
        }
    }

    @Override
    public String errorMessage(int errorCode) {
        switch (errorCode) {
            case 1 -> {
                return "I can't fly so high";
            }
            case 2 -> {
                return "I can't dig";
            }
            case 3 -> {
                return "I can't walk so far";
            }
        }
        return "No error";
    }
}