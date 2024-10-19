package contestant;

public class Space {
    private int id;
    private double max_radius;

    public Space(int id, double max_radius){
        this.id = id;
        this.max_radius = max_radius;
    }



    public double getMax_radius(){
        return max_radius;
    }

    public int getId() {
        return id;
    }

}
