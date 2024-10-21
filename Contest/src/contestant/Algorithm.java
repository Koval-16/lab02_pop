package contestant;

import java.util.ArrayList;

public class Algorithm {

    private TowerList list;
    private ArrayList<ArrayList<Tower>> optimal_towers = new ArrayList<>();
    private Solution best_solution;

    public Algorithm(TowerList list, ArrayList<Bucket> buckets){
        this.list = list;
        choose_optimal_towers();
        search_best_solution(buckets);
    }

    private void choose_optimal_towers(){
        for(int i=0; i<list.getTowers().size(); i++){
            optimal_towers.add(new ArrayList<>());
            for(int j=0; j<list.getTowers().get(i).size(); j++){
                Tower best = null;
                for(int k=0; k<list.getTowers().get(i).get(j).size(); k++){
                    if(k==0) best = list.getTowers().get(i).get(j).get(k);
                    else{
                        if(list.getTowers().get(i).get(j).get(k).getScore().getScore()>best.getScore().getScore()){
                            best = list.getTowers().get(i).get(j).get(k);
                        }
                    }
                }
                if(best!=null) optimal_towers.get(i).add(best);
            }
        }
    }

    private void search_best_solution(ArrayList<Bucket> buckets){
        int combinations = 1;
        for(int i=0; i<optimal_towers.size(); i++){
            combinations *= optimal_towers.get(i).size();
        }
        for(int i=0; i<combinations; i++){
            ArrayList<Tower> current_combination = new ArrayList<>();
            int current = i;
            for(int j= 0; j< optimal_towers.size(); j++){
                int index = current % optimal_towers.get(j).size();
                current_combination.add(optimal_towers.get(j).get(index));
                current /= optimal_towers.get(j).size();
            }
            if(is_valid(current_combination,buckets)) compare_solutions(current_combination);
        }
    }

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

    private void compare_solutions(ArrayList<Tower> current_combination){
        Solution current_solution = new Solution(current_combination);
        if (best_solution == null || current_solution.getScore() > best_solution.getScore()) {
            best_solution = current_solution;
        }
    }



    public Solution getBest_solution() {
        return best_solution;
    }
}
