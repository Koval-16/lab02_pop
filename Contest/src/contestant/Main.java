package contestant;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter portion value: ");
        double portion = scanner.nextDouble();
        System.out.print("Enter wage_1 value: ");
        double wage_1 = scanner.nextDouble();
        System.out.print("Enter wage_2 value: ");
        double wage_2 = scanner.nextDouble();
        ArrayList<Bucket> buckets = Reader.read_buckets("buckets.txt",portion);
        ArrayList<Space> spaces = Reader.read_spaces("spaces.txt");
        for(Bucket bucket : buckets) bucket.calculate_consequences(spaces.size());
        TowerList towerList = new TowerList();
        towerList.build_towers(spaces,buckets,portion);
        Algorithm algorithm = new Algorithm(towerList,buckets, portion, wage_1, wage_2);
        algorithm.getBest_solution().print_solution();
    }
}
