package contestant;

import java.util.ArrayList;

public class TowerList {

    ArrayList<ArrayList<ArrayList<Tower>>> towers = new ArrayList<>();

    public void build_towers(ArrayList<Space> spaces, ArrayList<Bucket> buckets, double portion){
        for(int i=0; i<spaces.size(); i++){
            towers.add(new ArrayList<>());
        }
        for(Space space : spaces){
            double max_layers = 0;
            for(Bucket bucket : buckets) max_layers += bucket.getMax_volume()/portion;
            for(int i=0; i<max_layers; i++){
                towers.get(space.getId()).add(new ArrayList<>());
                if(i==0) for(int n=0; n<buckets.size(); n++){
                    try{
                        Tower tower = new Tower(space,buckets,n, portion);
                        towers.get(space.getId()).get(i).add(tower);
                    } catch (IllegalArgumentException e){}
                }
                else for(int n=0; n<buckets.size(); n++) for(int m=0; m<towers.get(space.getId()).get(i-1).size(); m++){
                    try {
                        Tower tower = new Tower(towers.get(space.getId()).get(i-1).get(m), n, portion);
                        towers.get(space.getId()).get(i).add(tower);
                    } catch (IllegalArgumentException e) {}
                    /*for(int t=0; t<towers.get(space.getId()).get(i).size(); t++){
                        for(int x=0; x<towers.get(space.getId()).get(i).get(t).getLayers().size(); x++){
                            System.out.print(towers.get(space.getId()).get(i).get(t).getLayers().get(x).getBucket().getId());
                        }
                        System.out.println();
                    }*/
                }

            }
        }

        // here ends actual code, but I want to check what have I done XDD

        for(int i=0; i<towers.size(); i++){
            for(int j=0; j<towers.get(i).size(); j++){
                for(int k=0; k<towers.get(i).get(j).size(); k++){
                    for(int l=0; l<towers.get(i).get(j).get(k).getLayers().size(); l++){
                        System.out.print(towers.get(i).get(j).get(k).getLayers().get(l).getBucket().getId());
                    }
                    System.out.println(" "+towers.get(i).get(j).get(k).score.getScore());
                }
            }
        }

    }

}
