package contestant;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception{
        double portion = 500;
        double wage_1 = 10;
        double wage_2 = 0.01;
        ArrayList<Bucket> buckets = Reader.read_buckets("buckets.txt",portion);
        ArrayList<Space> spaces = Reader.read_spaces("spaces.txt");
        for(Bucket bucket : buckets) bucket.calculate_consequences(spaces.size());
        TowerList towerList = new TowerList();
        towerList.build_towers(spaces,buckets,portion);
        Algorithm algorithm = new Algorithm(towerList,buckets, portion, wage_1, wage_2);
        algorithm.getBest_solution().print_solution();
    }
}
