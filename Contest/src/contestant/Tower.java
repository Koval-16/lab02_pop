package contestant;

import java.util.ArrayList;

public class Tower {
    Space space;
    ArrayList<Layer> layers;
    ArrayList<Bucket> buckets;
    private double top_radius;
    private ArrayList<Integer> sand_in_buckets;

    // warstwa nie moze byc dodana jeżeli:
    // - wysypana warstwa nie zmieści sie
    // - juz wykorzystane zostalo wiaderko

    public Tower(Space space, ArrayList<Bucket> buckets, int index){
        this.space = space;
        layers = new ArrayList<>();
        this.buckets = buckets;
        top_radius = space.getMax_radius();
        sand_in_buckets = new ArrayList<>();
        for(int i=0; i<buckets.size(); i++){
            sand_in_buckets.add(buckets.get(i).getMax_volume());
        }
        try{
            add_layer(index);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERR");
        }
    }

    public Tower(Tower tower, int index){
        this.space = tower.space;
        //this.layers = tower.layers;
        this.layers = new ArrayList<>(tower.layers);
        /*for (Layer layer : tower.layers) {
            this.layers.add(new Layer(layer.getBucket(), 1, layer.getR()));
        }*/
        this.buckets = tower.buckets;
        this.top_radius = tower.top_radius;
        //this.sand_in_buckets = tower.sand_in_buckets;
        this.sand_in_buckets = new ArrayList<>(tower.sand_in_buckets);
        try{
            add_layer(index);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("ERR");
        }
    }

    private void add_layer(int index){
        if(sand_in_buckets.get(index)>=1){
            try{
                Layer layer = new Layer(buckets.get(index), 1, top_radius);
                layers.add(layer);
                sand_in_buckets.set(index, sand_in_buckets.get(index)-1);
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
