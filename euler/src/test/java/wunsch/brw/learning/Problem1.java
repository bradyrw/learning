package wunsch.brw.learning;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Problem1 {


   @Test
   public void calculate() {
      assertEquals(StaticMethodProblems.problem1(10), 23); //problem definition
     System.out.println(StaticMethodProblems.problem1(1000)); //answer for website
   }

   @Test
   public void testReferences () {

      Map<Integer, ArrayList<Integer>> foo = new HashMap<>();

      ArrayList<Integer> refAdd = new ArrayList<>();
      foo.put(1, refAdd);

      refAdd.add(123);
      refAdd.add(456);

      for (Integer val: foo.get(1)) {
         System.out.println("IN LIST: " + val);
      }
   }

}
