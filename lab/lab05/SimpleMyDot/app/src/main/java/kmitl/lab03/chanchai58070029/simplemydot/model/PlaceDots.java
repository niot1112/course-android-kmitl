package kmitl.lab03.chanchai58070029.simplemydot.model;


import java.util.ArrayList;

public class PlaceDots {
    private ArrayList<Dot> set;
    private Dot dot;

    public interface onDotChangedListener{
        void onDotChanged(PlaceDots placeDots);
    }
    private onDotChangedListener listener;

    public ArrayList<Dot> getSet() {
        return set;
    }

    public void setSet(Dot dot) {
        this.set.add(dot);
        this.listener.onDotChanged(this);
    }

    public Dot getDot() {
        return dot;
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public PlaceDots(onDotChangedListener listener) {
        this.set = new ArrayList();
        this.listener = listener;
    }
    public void removeDot(onDotChangedListener listener){
        this.set = new ArrayList();
        this.listener.onDotChanged(this);
    }
    public boolean removesomedot( int x, int y) {
        boolean check = true;
        if (this.set != null) {
            for (int i = 0; i < set.size(); i++) {
                if (Math.abs((x - set.get(i).getCenterX())) <= set.get(i).getRadius() && Math.abs(y - set.get(i).getCenterY()) <= set.get(i).getRadius()) {
                    set.remove(i);
                    this.listener.onDotChanged(this);
                    return check = false;
                }
            }
        }
        return check;
    }
    public boolean checkdot(int x,int y){
        boolean check = false;
        if (this.set != null) {
            for (int i = 0; i < set.size(); i++) {
                if (Math.abs((x - set.get(i).getCenterX())) <= set.get(i).getRadius() && Math.abs(y - set.get(i).getCenterY()) <= set.get(i).getRadius()) {
                    return check = true;
                }
            }
        }
        return check;
    }

}