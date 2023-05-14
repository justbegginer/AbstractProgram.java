package lab1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.Math;

@AllArgsConstructor
@Getter
@Setter
public class Point {
    private double x;
    private double y;
    private double z;

    public static double getDistance(Point A, Point B) {

        return Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2) + Math.pow(A.getZ() - B.getZ(), 2));
    }

    public String getCoordinatesMessage() {
        return x + ":" + y + ":" + z;
    }
}