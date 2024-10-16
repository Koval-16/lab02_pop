package contestant;

import java.util.ArrayList;

public class Solution {
    ArrayList<Bucket> buckets;
    ArrayList<Space> spaces;
    ArrayList<Layer> layers;
    double total_height;
    double remaining_volume;
    double score;

    public Solution(ArrayList<Bucket> buckets, ArrayList<Space> spaces){
        this.buckets = buckets;
        this.spaces = spaces;
        layers = new ArrayList<>();
        total_height = 0;
        remaining_volume = 0;
        for(Bucket bucket : buckets){
            remaining_volume += bucket.getMax_volume();
        }
    }



    public void calculate_score(){
        score = total_height+remaining_volume;
    }
}
