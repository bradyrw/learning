import java.util.HashMap;
import java.util.Map;

public class CountLines {

   /*
  * Complete the count_words function below.
  */
   static void count_words(String[] lines) {
       /*
         * Write your code here.
         */

      HashMap<String, Integer> counts = new HashMap<>();
      int totalWords = 0;
      int uniqueWords = 0;
      int maxTimes = 0;
      String mostUsed = "";

      for (int i = 0; i < lines.length; i++) {

         String curr = lines[i].toLowerCase();
         curr = curr.replaceAll("[^a-z \']", " ");
         curr = curr.replaceAll("^\\s+","");

         System.out.println(curr);
         System.out.println(lines[i]);

         for (String word: curr.split("\'*\\s+\'*")) {
            System.out.println(word);
            totalWords ++;
            Integer count = counts.get(word);
            if (count == null) {
               uniqueWords ++;
               count = 1;
            }
            else {
               count ++;
            }
            counts.put(word, count);
         }
      }

      for (Map.Entry<String,Integer> entry: counts.entrySet()) {
         if (entry.getValue() >= maxTimes) {
            maxTimes = entry.getValue();
            mostUsed = entry.getKey();
         }
      }
      System.out.println(totalWords);
      System.out.println(uniqueWords);
      System.out.println(mostUsed);
      System.out.println(maxTimes);

   }


}
