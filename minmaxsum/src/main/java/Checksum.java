public class Checksum {


   static int checksum (int[] arrayOfDigits) {
      int total = 0;

      boolean multiply = false;

      for (int i = arrayOfDigits.length -1; i >=0; i--) {
         if (multiply) {

            int intermediate = arrayOfDigits[i] * 5;
            int digitSum = 0;

            while (intermediate > 0) {
               digitSum += intermediate % 10;
               intermediate = intermediate / 10;
            }
            total+= digitSum;
            multiply = false;
         }
         else {
            total += arrayOfDigits[i];


            multiply = true;
         }

      }

      return total;
   }
}
