package wunsch.brw.learning;

public class StaticMethodProblems {

   /**
    * "If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
    * The sum of these multiples is 23.  Find the sum of all the multiples of 3 or 5 below 1000."
    *
    * @param value positive integer that represents ceiling for calculation
    * @return sum of numbers that are multiples of either 3 or 5 below the provided ceiling
    */
   public static int problem1 (int value){
      int total = 0;
      for (int i = 1; i < value; i++)
      {
         if (i%3 == 0 || i%5 == 0) {
            System.out.println(i);
            total += i;
         }
      }
      System.out.println("total: " + total);
      return total;
   }
}
