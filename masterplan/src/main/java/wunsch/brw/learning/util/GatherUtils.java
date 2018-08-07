package wunsch.brw.learning.util;

import com.gargoylesoftware.htmlunit.html.*;

import java.util.Iterator;
import java.util.List;

public class GatherUtils {

   public static void findInputs (HtmlPage page){
      List<HtmlForm> forms = page.getForms();
      for (HtmlForm form: forms) {
         if (form.getId().equalsIgnoreCase("smartguilde"))
         {

         }
         System.out.println("formid: " + form.getId());
         System.out.println( "formaa: " + form.getOnSubmitAttribute());
      }
    }
   public static void doSearch (HtmlPage page) {
      DomElement de = page.getElementById("d_1400695220645");
      de.focus();
      HtmlInput intputBox = (HtmlInput)page.getHtmlElementById("d_1400695220645");
      intputBox.setValueAttribute("C14");
      de = page.getElementById("d_1400695220646");
      try {
         de.click();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static boolean isPropertyDetails (HtmlTable inputTable) {
      for (HtmlTableRow row : inputTable.getRows()) {

         Iterator<HtmlTableCell> cells = row.getCellIterator();

         while (cells.hasNext()) {
           if ("Street Type".equals(cells.next().asText()))
              return true;
         }
      }
      return false;
   }

   public static boolean isPeopleDetails (HtmlTable inputTable) {
      for (HtmlTableRow row : inputTable.getRows()) {

         Iterator<HtmlTableCell> cells = row.getCellIterator();

         while (cells.hasNext()) {
            if ("People Type".equals(cells.next().asText()))
               return true;
         }
      }
      return false;
   }

   public static boolean isFolderFees (HtmlTable inputTable) {
      for (HtmlTableRow row : inputTable.getRows()) {

         Iterator<HtmlTableCell> cells = row.getCellIterator();

         while (cells.hasNext()) {
            if ("Fee Description".equals(cells.next().asText()))
               return true;
         }
      }
      return false;
   }

   public static boolean isProcessAndNotes (HtmlTable inputTable) {
      for (HtmlTableRow row : inputTable.getRows()) {
         if (row.getCells().size() > 5 ) {
            if ("Process Description".equals(row.getCell(0).asText()))
               return true;
         }
      }
      return false;
   }

   public static boolean isFolderInfo (HtmlTable inputTable) {
      for (HtmlTableRow row : inputTable.getRows()) {
         return row.getCells().size() == 2;
      }
      return false;
   }

   public static boolean isFolderAttachment (HtmlTable inputTable) {

      for (HtmlTableRow row : inputTable.getRows()) {
         if (row.getCells().size() == 3) {
            if ("Description".equals(row.getCell(0).asText()))
               return true;
         }
      }
      return false;
   }
}
