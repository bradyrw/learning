import org.junit.jupiter.api.Test;

public class BirthdayCakeTest {

   @Test
   public void basicTest () {
      int[] ar = new int[] {3, 2, 1, 3, 3, 3};
      System.out.println("Num Out: " + BirthdayCake.birthdayCakeCandles(4, ar));

   }
}
