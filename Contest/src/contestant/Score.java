package contestant;

import java.util.ArrayList;

public class Score {
    private double score;
    private double height;

    public Score(ArrayList<Layer> layers, ArrayList<Double> sand){
        height = calculate_height(layers);
    }

    private double calculate_remaining_vol(ArrayList<Double> sand_remained){
        double remained_volume = 0;
        for(Double sand : sand_remained){
            remained_volume += sand;
        }
        return remained_volume;
    }

    private double calculate_height(ArrayList<Layer> layers){
        double height = 0;
        for(Layer layer : layers){
            height += layer.getHeight();
        }
        return height;
    }

    private double calculate_score(double wage_1, double wage_2, double height, double volume){
        return (wage_1*height)+(wage_2*volume);
    }

    public double getScore() {
        return score;
    }

    public double getHeight(){
        return height;
    }
}
