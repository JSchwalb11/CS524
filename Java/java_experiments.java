package Java;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.nio.file.Paths;

public class java_experiments {

  public static ArrayList<Integer> setup(String fn){
    String par_dir = Paths.get(".").toAbsolutePath().normalize().toString();
    String fp;
    FileReader fr;
    try {
      fp = par_dir +  "\\" + fn;
      System.out.println("Fp = " + fp);
      fr = new FileReader(fp);
    }
    catch(Exception e){
      //System.out.println(e);
      fp = par_dir +  "/" + fn;
      try {
        fr = new FileReader(fp);
        System.out.println("Fp = " + fp);
      } catch (FileNotFoundException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    ArrayList<Integer> arr = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fp))) {
      String line;
      while ((line = br.readLine()) != null) {
        arr.add(Integer.parseInt(line));
      }
    }
    catch(Exception e){
      System.out.println(e);
    }

    return arr;
  }

  public static int binarySearch(ArrayList<Integer> arr, int size, int search_value){
    int low = 0, high = size - 1, guesses = 0;

    while (low <= high) {
      guesses = guesses + 1;
      int mid = (low + high) / 2;
      if (arr.get(mid).intValue() == search_value) {
        System.out.println("Found value in " + Integer.toString(guesses) + " guesses.\n");
        return mid;
      }
      else if (arr.get(mid).intValue() < search_value) {
          low = mid + 1;
      }
      else {
        high = mid - 1;
      }
    } 
    System.out.println("You searched for " + Integer.toString(search_value) + " but the value was not found.\n");
    return -1;
  }

  public static void main(String[] args) {
    ArrayList<Integer> arr = setup("sorted_list.csv");
    int search_value = (int) Math.pow(2, 19); // list is 0-based and of length 2^20. Force 20 guesses.
    int index = binarySearch(arr, arr.size(), search_value);
    
    if (index > -1){
      System.out.println("Found value " + Integer.toString(search_value) + " at index " + Integer.toString(index) + "\n");
    }
  }
}