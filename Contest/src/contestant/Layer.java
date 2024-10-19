package contestant;

public class Layer {
    private Bucket bucket;
    private double volume;
    private double R;
    private double r;
    private double height;

    public Layer(Bucket bucket, double volume, double R){
        this.bucket = bucket;
        this.volume = volume;
        this.R = R;
        calculate();
        if(r<0){
            throw new IllegalArgumentException("ERR");
        }
    }

    public void calculate(){
        r = Math.cbrt(Math.pow(R,3)-((3*volume*Math.tan(Math.toRadians(bucket.getAngle())))/Math.PI));
        height = (R - r)/Math.tan(Math.toRadians(bucket.getAngle()));
    }

    public double getR(){
        return r;
    }

    public Bucket getBucket(){
        return bucket;
    }

    public double getHeight() {
        return height;
    }
}
