package contestant;

public class Layer {
    private int space_id;
    private int floor_id;
    private double volume;
    private int angle;
    private double R;
    private double r;
    private double height;

    public Layer(int space_id, int floor_id, double volume, int angle, double R){
        this.space_id = space_id;
        this.floor_id = floor_id;
        this.volume = volume;
        this.angle = angle;
        this.R = R;
    }

    public void calculate(){
        r = Math.cbrt(Math.pow(R,3)-((3*volume*Math.tan(Math.toRadians(15)))/Math.PI));
        height = (R - r)/Math.tan(Math.toRadians(15));
    }
}
