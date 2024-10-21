package contestant;

import java.util.ArrayList;

public class Solution {
    private double score;
    private ArrayList<Tower> chosen_towers;

    public Solution(ArrayList<Tower> chosen_towers){
        this.chosen_towers = chosen_towers;
        calculate_score();
    }

    private void calculate_score(){
        for(Tower tower : chosen_towers){
            score += tower.getScore().getScore();
        }
    }

    public void print_solution(){
        for(int i=0; i<chosen_towers.size(); i++){
            System.out.print("Miejsce "+i+": ");
            for(int j=0; j<chosen_towers.get(i).getLayers().size(); j++){
                System.out.print(chosen_towers.get(i).getLayers().get(j).getBucket().getId());
            }
            System.out.println();
        }
    }

    public double getScore() {
        return score;
    }
}
