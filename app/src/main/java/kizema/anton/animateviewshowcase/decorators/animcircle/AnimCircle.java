package kizema.anton.animateviewshowcase.decorators.animcircle;

/**
 * Created by somename on 06.09.2016.
 */
public class AnimCircle {

    private int startRadius;

//    private int radius;

    public AnimCircle(){
    }

    public void setStartRadius(int startRadius){
        this.startRadius = startRadius;
    }

    public int getStartRadius(){
        return startRadius;
    }
    public void incrementStartRadius(int dx){
        this.startRadius += dx;
    }

//    public void setRadius(int radius){
//        this.radius = radius;
//    }

}
