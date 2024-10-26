package contestant;

import java.util.ArrayList;

public class Solution {
    private double score;
    private ArrayList<Tower> chosen_towers;

    public Solution(ArrayList<Tower> chosen_towers, double portion, ArrayList<Bucket> buckets, double wage_1, double wage_2){
        this.chosen_towers = chosen_towers;
        calculate_score(portion, buckets, wage_1, wage_2);
    }

    private void calculate_score(double portion, ArrayList<Bucket> buckets, double wage_1, double wage_2){
        double height=0;
        double towers_volume = 0;
        for(Tower tower : chosen_towers){
            height += tower.getScore().getHeight();
            towers_volume += portion * tower.getLayers().size();
        }
        height = height/chosen_towers.size();
        double sand_full=0;
        for(Bucket bucket : buckets){
            sand_full += bucket.getMax_volume();
        }
        double sand_remained = sand_full-towers_volume;
        score = (wage_1*height)+(wage_2*sand_remained);
    }

    public void print_solution(){
        for(int i=0; i<chosen_towers.size(); i++){
            System.out.print("Miejsce "+i+": ");
            for(int j=0; j<chosen_towers.get(i).getLayers().size(); j++){
                System.out.print(chosen_towers.get(i).getLayers().get(j).getBucket().getId());
            }
            System.out.println(", "+chosen_towers.get(i).getScore().getHeight());
        }
    }

    public double getScore() {
        return score;
    }
}
