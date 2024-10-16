package contestant;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception{
        ArrayList<Bucket> buckets = Reader.read_buckets("buckets.txt");
        ArrayList<Space> spaces = Reader.read_spaces("spaces.txt");
        int portion = 1;
        Solution best_solution;
        double wage_1 = 0.5;
        double wage_2 = 0.5;
    }
}
