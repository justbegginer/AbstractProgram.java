package lab1;

public class Dig implements Move{
    @Override
    public String move(Point a, Point b) {
        if (a.getZ() - b.getZ() > 50) {
            return errorMessage(2);
        } else if (a.getZ() - b.getZ() < 0) {
            return errorMessage(1);
        } else {
            if (Point.getDistance(a, b) > 75) {
                return errorMessage(3);
            } else {
                //System.out.println("I digged from "+a.getCoordinatesMessage() + " to "+ b.getCoordinatesMessage());
                return "Moved successfully";
            }
        }
    }

    @Override
    public String errorMessage(int errorCode) {
        switch (errorCode) {
            case 1 -> {
                return "I can't fly";
            }
            case 2 -> {
                return "I can't dig so deep";
            }
            case 3 -> {
                return "I can't move so long";
            }
        }
        return "No error message";
    }
}