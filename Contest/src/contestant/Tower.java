package contestant;

import java.util.ArrayList;

public class Tower {
    Space space;
    ArrayList<Layer> layers;
    ArrayList<Bucket> buckets;
    private double top_radius;
    private ArrayList<Double> sand_in_buckets;
    Score score;

    public Tower(Space space, ArrayList<Bucket> buckets, int index, double portion){
        this.space = space;
        layers = new ArrayList<>();
        this.buckets = buckets;
        top_radius = space.getMax_radius();
        sand_in_buckets = new ArrayList<>();
        for(int i=0; i<buckets.size(); i++){
            sand_in_buckets.add(buckets.get(i).getMax_volume());
        }
        try{
            add_layer(index, portion);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERR");
        }
        score = new Score(1,0.015,layers,sand_in_buckets);
    }

    public Tower(Tower tower, int index, double portion){
        this.space = tower.space;
        this.layers = new ArrayList<>(tower.layers);
        this.buckets = tower.buckets;
        this.top_radius = tower.top_radius;
        //this.sand_in_buckets = tower.sand_in_buckets;
        this.sand_in_buckets = new ArrayList<>(tower.sand_in_buckets);
        try{
            add_layer(index, portion);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERR");
        }
        score = new Score(1,0.015,layers,sand_in_buckets);
    }

    private void add_layer(int index, double portion){
        if(sand_in_buckets.get(index)>=portion){
            try{
                Layer layer = new Layer(buckets.get(index), portion, top_radius);
                layers.add(layer);
                sand_in_buckets.set(index, sand_in_buckets.get(index)-portion);
                top_radius = layer.getR();
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("ERR");
            }
        }
        else{
            throw new IllegalArgumentException("ERR");
        }
    }

    public ArrayList<Layer> getLayers(){
        return layers;
    }
}
