package contestant;

public class Bucket {
    private int id;
    private int angle;
    private int max_volume;

    public Bucket(int id, int angle, int max_volume){
        this.id = id;
        this.angle = angle;
        this.max_volume = max_volume;
    }

    public int getAngle() {
        return angle;
    }

    public int getMax_volume() {
        return max_volume;
    }



    public int getId(){
        return id;
    }
}
