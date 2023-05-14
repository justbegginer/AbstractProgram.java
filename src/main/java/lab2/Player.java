package lab2;

public class Player {
    private String song;
    public Player(){
        this.song = "SONG NUMBER 0";
    }
    @MyAnnotation(repeatTimes = 3)
    public String playSong(int arg){
        return "Playing song - " + song + " int arg - " + arg;
    }

    @MyAnnotation(repeatTimes = 4)
    protected String nextSong(double arg){
        return "Move to next song double arg - "+arg;
    }

    @MyAnnotation
    private String previousSong(ArgClass argClass){
        return "Move to previous song " + argClass;
    }

    @MyAnnotation(repeatTimes = 5)
    private void setSong(String song){
        this.song = song;
    }
}