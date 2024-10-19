package contestant;

import java.util.ArrayList;

public class Score {
    private double score;

    public Score(double wage_1, double wage_2, ArrayList<Layer> layers, ArrayList<Double> sand){
        score = calculate_score(wage_1,wage_2,calculate_height(layers),calculate_remaining_vol(sand));
    }

    public double calculate_remaining_vol(ArrayList<Double> sand_remained){
        double remained_volume = 0;
        for(Double sand : sand_remained){
            remained_volume += sand;
        }
        return remained_volume;
    }

    public double calculate_height(ArrayList<Layer> layers){
        double height = 0;
        for(Layer layer : layers){
            height += layer.getHeight();
        }
        return height;
    }

    public double calculate_score(double wage_1, double wage_2, double height, double volume){
        return (wage_1*height)+(wage_2*volume);
    }

    public double getScore() {
        return score;
    }
}
