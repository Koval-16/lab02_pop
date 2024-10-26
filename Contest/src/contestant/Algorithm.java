package contestant;

import java.util.ArrayList;
import java.util.Random;

public class Algorithm {

    private TowerList list;
    private ArrayList<ArrayList<Tower>> optimal_towers = new ArrayList<>();
    private ArrayList<ArrayList<Tower>> optimal_expanded = new ArrayList<>();
    private Solution best_solution;

    public Algorithm(TowerList list, ArrayList<Bucket> buckets, double portion, double wage_1, double wage_2){
        this.list = list;
        choose_optimal_towers(wage_1, wage_2);
        choose_height_optimal();
        search_best_solution(optimal_towers,buckets, portion, wage_1, wage_2);
        search_solutions_expanded(buckets, portion, wage_1, wage_2);
    }

    // wybiera optymalne wieże dla każdej grupy wież
    private void choose_optimal_towers(double wage_1, double wage_2){
        for(int i=0; i<list.getTowers().size(); i++){
            optimal_towers.add(new ArrayList<>());
            for(int j=0; j<list.getTowers().get(i).size(); j++){
                Tower best = null;
                for(int k=0; k<list.getTowers().get(i).get(j).size(); k++){
                    if(k==0) best = list.getTowers().get(i).get(j).get(k);
                    else{
                        if(list.getTowers().get(i).get(j).get(k).getScore().getHeight()>best.getScore().getHeight()){
                            best = list.getTowers().get(i).get(j).get(k);
                        }
                    }
                }
                if(best!=null){
                    optimal_towers.get(i).add(best);
                    best.getScore().calculate_score(wage_1, wage_2);
                }
            }
        }
        choose_optimal_heights();
    }

    // wybiera najlepsze grupy wież
    private void choose_optimal_heights() {
        for (ArrayList<Tower> towersGroup : optimal_towers) {
            for (int i = 0; i < towersGroup.size() - 1; i++) {
                for (int j = i + 1; j < towersGroup.size(); j++) {
                    Tower tower1 = towersGroup.get(i);
                    Tower tower2 = towersGroup.get(j);
                    if (tower1.getScore().getScore() < tower2.getScore().getScore()) {
                        towersGroup.set(i, tower2);
                        towersGroup.set(j, tower1);
                    }
                }
            }
        }
    }

    // sprawdza kilka innych losowych rozwiązań z najbardziej optymalnych grup wież
    private void choose_height_optimal(){
        int numOptimalTowers = 5;
        for (int i = 0; i <optimal_towers.size(); i++) {
            optimal_expanded.add(new ArrayList<>());
            for(int j=0; j<Math.min(5, optimal_towers.get(i).size()); j++){
                int index = optimal_towers.get(i).get(j).getLayers().size()-1;
                ArrayList<Tower> towersAtLayer = list.getTowers().get(i).get(index);
                for (int m=0; m<towersAtLayer.size()-1; m++) {
                    for (int n=m+1; n<towersAtLayer.size(); n++) {
                        Tower tower1 = towersAtLayer.get(m);
                        Tower tower2 = towersAtLayer.get(n);
                        if (tower1.getScore().getHeight() < tower2.getScore().getHeight()) {
                            towersAtLayer.set(m, tower2);
                            towersAtLayer.set(n, tower1);
                        }
                    }
                }
                for (int k = 0; k < Math.min(numOptimalTowers, towersAtLayer.size()); k++) {
                    optimal_expanded.get(i).add(towersAtLayer.get(k));
                }
            }
        }
    }

    // szuka najlepszego rozwiązania
    private void search_best_solution(ArrayList<ArrayList<Tower>> optimal,ArrayList<Bucket> buckets, double portion, double wage_1, double wage_2){
        int combinations = 1;
        for(int i=0; i<optimal.size(); i++) combinations *= optimal.get(i).size();
        for(int i=0; i<combinations; i++){
            ArrayList<Tower> current_combination = new ArrayList<>();
            int current = i;
            for(int j= 0; j< optimal.size(); j++){
                int index = current % optimal.get(j).size();
                current_combination.add(optimal.get(j).get(index));
                current /= optimal.get(j).size();
            }
            if(is_valid(current_combination,buckets)) compare_solutions(current_combination, portion, buckets, wage_1, wage_2);
        }
    }


    // sprawdza czy dane rozwiązanie jest poprawne
    private void search_solutions_expanded(ArrayList<Bucket> buckets, double portion, double wage_1, double wage_2){
        Random random = new Random();
        for(int i=0; i<100; i++){
            ArrayList<Tower> current_combination = new ArrayList<>();
            for(int j=0; j<optimal_expanded.size(); j++){
                current_combination.add(optimal_expanded.get(j).get(random.nextInt(optimal_expanded.get(j).size())));
            }
            if(is_valid(current_combination,buckets)) compare_solutions(current_combination, portion, buckets, wage_1, wage_2);

        }
    }

    // sprawdza czy wybrane rozwiązanie jest poprawne
    private boolean is_valid(ArrayList<Tower> current_combination, ArrayList<Bucket> buckets){
        double[] max_layers = new double[buckets.size()];
        for(int m=0; m<buckets.size(); m++) max_layers[m] = buckets.get(m).getMax_layers();
        for (Tower tower : current_combination) for (Layer layer : tower.getLayers()) for (int n = 0; n < max_layers.length; n++) if (layer.getBucket().getId() == n) max_layers[n]--;
        boolean is_valid = true;
        for(int m=0; m<max_layers.length; m++){
            if(max_layers[m]<0) is_valid=false;
        }
        return is_valid;
    }

    // porównuje obecnie rozważane rozwiązanie z najlepszym
    private void compare_solutions(ArrayList<Tower> current_combination, double portion, ArrayList<Bucket> buckets, double wage_1, double wage_2){
        Solution current_solution = new Solution(current_combination, portion, buckets, wage_1, wage_2);
        if (best_solution == null || current_solution.getScore() > best_solution.getScore()) {
            best_solution = current_solution;
        }
    }



    public Solution getBest_solution() {
        return best_solution;
    }
}