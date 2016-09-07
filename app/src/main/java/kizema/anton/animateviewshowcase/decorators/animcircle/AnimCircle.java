package kizema.anton.animateviewshowcase.decorators.animcircle;

public class AnimCircle {

    private int startRadius;

    public AnimCircle(){}

    public void setStartRadius(int startRadius){
        this.startRadius = startRadius;
    }

    public int getStartRadius(){
        return startRadius;
    }
    public void incrementStartRadius(int dx, int max){
        this.startRadius += dx;

        if (startRadius >= max){
            startRadius -= max;
        }
    }

}
