package contestant;

public class Bucket {
    private int id;
    private int angle;
    private double max_volume;
    private double max_layers;
    private double consequences;

    public Bucket(int id, int angle, double max_volume, double portion){
        this.id = id;
        this.angle = angle;
        this.max_volume = max_volume;
        this.max_layers = max_volume/portion;
    }

    public int getAngle() {
        return angle;
    }

    public double getMax_volume() {
        return max_volume;
    }

    // oblicza wartość "warstw bez konsekwencji dla budowania wież"
    public void calculate_consequences(int spaces){
        consequences = Math.floor(max_layers/spaces);
    }

    public double getConsequences(){
        return consequences;
    }

    public int getId(){
        return id;
    }

    public double getMax_layers() {
        return max_layers;
    }
}
