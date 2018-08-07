package wunsch.brw.learning.util;

import java.util.Comparator;

public class FuzzyPersonComparator implements Comparator {

   public int compare (Object a, Object b) {
      String one = makeAdjustments((String)a);
      String two = makeAdjustments((String)b);
      return one.compareTo(two);
   }

   private String makeAdjustments (String input) {
      String output = input.toLowerCase();
      output = output.replace("\\s*&\\s*", " ");
      output = output.replace("\\s.*and\\s.*", " ");
      output = output.replace(",?+\\s.*llc.?+\\s*", "");
      output = output.replace(",?+\\s.*inc.?+\\s*", "");


      return output;
   }
}
