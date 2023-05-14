package lab1;

import java.util.Objects;

public class Hero {
    private Move moveType;
    private Point position;

    public Hero() {
        this.moveType = new Walk();
        this.position = new Point(0, 0, 0);
    }
    public Hero(Move moveType){
        this.moveType = moveType;
        this.position = new Point(0, 0, 0);
    }
    public Hero(String move) {
        this.changeMovementType(move);
        this.position = new Point(0, 0, 0);
    }

    public boolean changeMovementType(String type) {
        switch (type.toLowerCase()) {
            case "fly":
                this.moveType = new Fly();
                //System.out.println("Now you're flying being");
                return true;
            case "walk":
                this.moveType = new Walk();
                //System.out.println("Now you're walking being");
                return true;
            case "dig":
                this.moveType = new Dig();
                //System.out.println("Now you're digging being");
                return true;
            default:
                this.moveType = new Walk();
                //System.out.println("Wrong movement type\nYou have the same type that was before");
                return false;
        }
    }

    public String move(Point a) {
        if (Objects.equals(this.moveType.move(this.position, a), "Moved successfully")) {
            this.position = a;
        }
        return this.moveType.move(this.position, a);
    }

    public Point getPosition() {
        return position;
    }
}