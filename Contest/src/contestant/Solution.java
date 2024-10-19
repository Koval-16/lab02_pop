package contestant;

import java.util.ArrayList;

public class Solution {
    double total_height;
    double remaining_volume;
    double wage_1;
    double wage_2;
    double score;

    public Solution(double wage_1, double wage_2){
        this.wage_1 = wage_1;
        this.wage_2 = wage_2;
        total_height = 0;
        remaining_volume = 0;
    }



    public void calculate_score(){
        score = (wage_1*total_height)+(wage_2*remaining_volume);
    }
}
