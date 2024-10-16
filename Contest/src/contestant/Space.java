package contestant;

public class Space {
    private int id;
    private int max_radius;
    private double surface;
    private int radius;

    public Space(int id, int max_radius){
        this.id = id;
        this.max_radius = max_radius;
        this.surface = Math.PI*Math.pow(radius,2);
    }

    public int getRadius() {
        return max_radius;
    }
}
