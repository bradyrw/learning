import org.junit.jupiter.api.Test;

public class TestChecksum {

   @Test
   public void checksumTest () {

      int[] i = new int [] {4,1,2,3,4};
      Checksum.checksum(i);

   }


}
