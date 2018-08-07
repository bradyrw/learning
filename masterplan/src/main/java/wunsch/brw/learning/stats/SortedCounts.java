package wunsch.brw.learning.stats;

import wunsch.brw.learning.model.ZoningProject;

import java.util.*;

public class SortedCounts {

   private Map<String,List<ZoningProject>> unsorted;
   private TreeMap<String,List<ZoningProject>> sorted;


   public SortedCounts(Map<String, List<ZoningProject>> input) {
      unsorted = input;
      sorted = new TreeMap<String,List<ZoningProject>>(new ValueComparator(unsorted));
      sorted.putAll(input);
   }

   private class ValueComparator implements Comparator {
      Map map;

      private  ValueComparator (Map map) {
         this.map = map;
      }

      public int compare(Object keyA, Object keyB) {

         List<ZoningProject> valueA = (List<ZoningProject>) map.get(keyA);
         List<ZoningProject> valueB = (List<ZoningProject>) map.get(keyB);

         if (valueA.size() > valueB.size())
            return -1;
         if (valueB.size() > valueA.size())
            return 1;
         return ((String)keyA).compareTo((String)keyB);

      }
   }

   public Set<Map.Entry<String,List<ZoningProject>>> entrySet () {
      return sorted.entrySet();
   }
}
