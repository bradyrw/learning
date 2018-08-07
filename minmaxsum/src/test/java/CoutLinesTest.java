import org.junit.jupiter.api.Test;

public class CoutLinesTest {

   @Test
   public void count () {

      String a = "I'm a test file. It's a good thing to be. It's a job, and I like my job.";
      String b = "A good test file makes sure that all of its goals are met. I'm good that way, since I make sure that all the things are tested.";
      String c = "\"That's cool\" you say. \"That's right, it's cool\" I say. It's a bit arrogant, but there you go.";
      String d = "I like clean test data like \"it's a 'good test'\".";

            String [] input = new String [] {a,b,c,d};
      CountLines.count_words(input);
   }
}
