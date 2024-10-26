package contestant;

import java.util.ArrayList;

public class TowerList {

    private static final int MAX_TOWERS = 100;
    private ArrayList<ArrayList<ArrayList<Tower>>> towers = new ArrayList<>();

    public void build_towers(ArrayList<Space> spaces, ArrayList<Bucket> buckets, double portion){
        for(int i=0; i<spaces.size(); i++){
            towers.add(new ArrayList<>());
        }
        for(Space space : spaces){
            double max_layers =  calculate_max_layers(buckets);
            for(int i=0; i<max_layers; i++){
                towers.get(space.getId()).add(new ArrayList<>());
                if(i==0) one_layer_tower(space, i, buckets, portion);
                else more_layer_tower(space, i, buckets, portion);
            }
        }

        for(int i=0; i<towers.size(); i++){
            for(int j=0; j<towers.get(i).size(); j++){
                for(int k=0; k<towers.get(i).get(j).size(); k++){
                    for(int l=0; l<towers.get(i).get(j).get(k).getLayers().size(); l++){
                        System.out.print(towers.get(i).get(j).get(k).getLayers().get(l).getBucket().getId());
                    }
                    System.out.println(" "+towers.get(i).get(j).get(k).getScore().getHeight());
                }
            }
        }
    }

    private double calculate_max_layers(ArrayList<Bucket> buckets){
        double max_layers = 0;
        for(Bucket bucket : buckets) max_layers += bucket.getMax_layers();
        return max_layers;
    }

    private void one_layer_tower(Space space, int i, ArrayList<Bucket> buckets, double portion){
        for(int n=0; n<buckets.size(); n++){
            try{
                Tower tower = new Tower(space,buckets,n, portion);
                towers.get(space.getId()).get(i).add(tower);
            } catch (IllegalArgumentException e){}
        }
        decrease_layers(space.getId(), i, buckets);
    }

    private void more_layer_tower(Space space, int i, ArrayList<Bucket> buckets, double portion){
        for(int n=0; n<buckets.size(); n++){
            for(int m=0; m<towers.get(space.getId()).get(i-1).size(); m++){
                try {
                    Tower tower = new Tower(towers.get(space.getId()).get(i-1).get(m), n, portion);
                    towers.get(space.getId()).get(i).add(tower);
                } catch (IllegalArgumentException e) {}
            }
        }
        decrease_layers(space.getId(), i, buckets);
    }

    private void decrease_layers(int spaceId, int layerIndex, ArrayList<Bucket> buckets) {
        // Pobieramy listę wież dla danego miejsca i warstwy
        ArrayList<Tower> towersAtLayer = towers.get(spaceId).get(layerIndex);

        for(int i=0; i<towersAtLayer.size(); i++){
            Tower tower1 = towersAtLayer.get(i);
            int[]  lay_number = new int[buckets.size()];
            for(int index=0; index<tower1.getLayers().size(); index++){
                lay_number[tower1.getLayers().get(index).getBucket().getId()]++;
            }
            boolean alg = true;
            for(int j=0; j<lay_number.length; j++){
                if(lay_number[j]>buckets.get(j).getConsequences()){
                    for(int k=i+1; k< towersAtLayer.size(); k++){
                        Tower tower2 = towersAtLayer.get(k);
                        int[] lay_number2 = new int[buckets.size()];
                        for(int index=0; index<tower2.getLayers().size(); index++){
                            lay_number2[tower2.getLayers().get(index).getBucket().getId()]++;
                        }
                        algorithm_2(tower1,tower2,lay_number,lay_number2,towersAtLayer,j);
                    }
                    alg = false;
                }
            }
            if(alg){
                for(int k=i+1; k< towersAtLayer.size(); k++){
                    Tower tower2 = towersAtLayer.get(k);
                    int[] lay_number2 = new int[buckets.size()];
                    for(int index=0; index<tower2.getLayers().size(); index++){
                        lay_number2[tower2.getLayers().get(index).getBucket().getId()]++;
                    }
                    algorithm_1(tower1,tower2,lay_number,lay_number2,towersAtLayer);
                }
            }
        }

        // Sprawdzamy, czy liczba wież przekracza maksymalną wartość
        if (towersAtLayer.size() > MAX_TOWERS) {
            // Używamy klasycznego sortowania z wykorzystaniem Comparatora
            for (int i = 0; i < towersAtLayer.size(); i++) {
                for (int j = i + 1; j < towersAtLayer.size(); j++) {
                    // Pobieramy wieże z listy
                    Tower tower1 = towersAtLayer.get(i);
                    Tower tower2 = towersAtLayer.get(j);

                    // Porównujemy wieże po ich wysokościach i zamieniamy, jeśli są w złej kolejności
                    if (tower1.getScore().getHeight() < tower2.getScore().getHeight()) {
                        towersAtLayer.set(i, tower2);
                        towersAtLayer.set(j, tower1);
                    }
                }
            }

            // Usuwamy wszystkie wieże poza pierwszymi MAX_TOWERS
            while (towersAtLayer.size() > MAX_TOWERS) {
                towersAtLayer.remove(towersAtLayer.size() - 1);
            }

        }
    }

    public ArrayList<ArrayList<ArrayList<Tower>>> getTowers() {
        return towers;
    }

    private void algorithm_1(Tower tower1, Tower tower2, int[] lay1, int[] lay2, ArrayList<Tower> towers){
        boolean the_same = true;
        for(int i=0; i<lay1.length; i++) if(lay1[i]!=lay2[i]){
            the_same = false;
        }
        if(the_same){
            if(tower1.getScore().getHeight()>=tower2.getScore().getHeight()){
                towers.remove(tower2);
            }
            else{
                towers.remove(tower1);
            }
        }
    }

    private void algorithm_2(Tower tower1, Tower tower2, int[] lay1, int[] lay2, ArrayList<Tower> towers, int index){
        if(lay1[index] == lay2[index]){
            if(tower1.getScore().getHeight()>=tower2.getScore().getHeight()){
                towers.remove(tower2);
            }
            else{
                towers.remove(tower1);
            }
        }
    }

}
