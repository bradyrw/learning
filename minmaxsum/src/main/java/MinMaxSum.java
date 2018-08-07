import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class MinMaxSum {

   /*
    * Complete the miniMaxSum function below.
    */
   static void miniMaxSum(int[] arr) {
        /*
         * Write your code here.
         */

      int largest = 0;
      long smallest = Long.MAX_VALUE;

      long littleTotal = 0;
      long bigTotal = 0;

      for (int i = 0; i < arr.length; i++)
      {
         if (largest == 0) {
            largest = arr[i];
         }
         else if (arr[i] > largest) {
            littleTotal += largest;
            largest = arr[i];
         }
         else {
            littleTotal += arr[i];
         }

         if (smallest == Long.MAX_VALUE) {
            smallest = arr[i];
         }
         else if (arr[i] < smallest) {
            bigTotal += smallest;
            smallest = arr[i];
         }
         else {
            bigTotal += arr[i];
         }
      }

      System.out.println(littleTotal + " " + bigTotal);

   }

   private static final Scanner scan = new Scanner(System.in);

   public static void main(String[] args) {
      int[] arr = new int[5];

      String[] arrItems = scan.nextLine().split(" ");

      for (int arrItr = 0; arrItr < 5; arrItr++) {
         int arrItem = Integer.parseInt(arrItems[arrItr].trim());
         arr[arrItr] = arrItem;
      }

      miniMaxSum(arr);
   }
}
