package contestant;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception{
        double portion = 500;
        ArrayList<Bucket> buckets = Reader.read_buckets("buckets.txt");
        ArrayList<Space> spaces = Reader.read_spaces("spaces.txt");
        TowerList towerList = new TowerList();
        towerList.build_towers(spaces,buckets,portion);
        Solution best_solution;
        //double wage_1 = 0.75;
        //double wage_2 = 0.25;

    }
}
