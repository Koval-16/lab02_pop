package contestant;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public static ArrayList<Bucket> read_buckets(String file_name) throws Exception{
        ArrayList<Bucket> buckets = new ArrayList<>();
        File file = new File(file_name);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            buckets.add(new Bucket(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        return buckets;
    }

    public static ArrayList<Space> read_spaces(String file_name) throws Exception{
        ArrayList<Space> spaces = new ArrayList<>();
        File file = new File(file_name);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            spaces.add(new Space(scanner.nextInt(), scanner.nextInt()));
        }
        return spaces;
    }
}
