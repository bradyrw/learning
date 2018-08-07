package wunsch.brw.learning;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.jupiter.api.Test;
import wunsch.brw.learning.model.*;
import wunsch.brw.learning.util.PageAnchors;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;


public class Problem1 {

   static GatheredData gatheredData = new GatheredData();

   static {
      java.util.logging.Logger.getLogger("com.gargoylesoftware").
      setLevel(Level.OFF);
      System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
   }




   private HtmlForm getFormFromPage(HtmlPage page) {
      List<HtmlForm> forms = page.getForms();
      boolean clicked = false;
      for (HtmlForm form : forms) {
         if ("smartguide".equals(form.getId())) {
            return form;
         }
      }
      return null;
   }


   private void gatherDetails (ZoningProject project, HtmlPage page) {
      List<HtmlDivision> divs = page.getByXPath( "//div[@class='inline mediumcolumn']");
      project.setDetails(new FolderDetails(divs));

      // HtmlDivision division =  page.getFirstByXPath("//*[@id=\"sgControls\"]/div[1]");
      List<HtmlTable> tables = page.getByXPath("//table");

      Iterator<HtmlTable> tableIter = tables.iterator();
      FolderInfo folderInfo = new FolderInfo(tableIter.next());
      project.setFolderInfo(folderInfo);
      tableIter.next();
      PeopleDetails peopleDetails = new PeopleDetails(tableIter.next());
      project.setPeopleDetails(peopleDetails);
      Fees fees = new Fees(tableIter.next()); //fees
      project.setFees(fees);




   }

   private void handleRow (HtmlTableRow row, PageAnchors anchors) {
//      cell(0):
//      cell(1): Permit/Case
//      cell(2): Reference File Name
//      cell(3): Description
//      cell(4): Sub Type / Work Type
//      cell(5): Project Name
//      cell(5): Status
//      cell(7): Related Folder
      if (row.getCell(0).asText() == null || row.getCell(0).asText().isEmpty()) {
         System.out.println("Table Header");
         return;
      }

      ZoningProject proj = new ZoningProject(new ZoningProjectID(row));
      System.out.println(proj);

      if (proj.shouldTraverse()) {
         HtmlTableCell cell = row.getCell(1);
         try {
            System.out.println("Clicking on: " + cell.asText());
            HtmlAnchor toClickOn = anchors.find(cell.asText());
            HtmlPage detailsPage = toClickOn.click();
            Thread.sleep(2000);
            gatherDetails(proj, detailsPage);
           // gatheredData.addProject(gatherDetails(detailsPage));
            System.out.println("After Data Gathering:");
            System.out.println(proj);
         }
         catch (Exception e) {
            e.printStackTrace();
         }
      }
   }




   private boolean traverseResult(HtmlPage page) {
     // System.out.println(page.asXml());

      HtmlTable table = (HtmlTable) page.getFirstByXPath("//table");
      table.getHeader();
      List<HtmlAnchor> anchorsRaw = page.getAnchors();
      PageAnchors anchors = new PageAnchors(anchorsRaw);

      for (final HtmlTableRow row : table.getRows()) {
         handleRow(row,anchors);
      }

      HtmlForm form = getFormFromPage(page);


      return true;
   }


   @Test
   public void calculateStats() {

      final WebClient webClient = new WebClient();
      final HtmlPage page;
      HtmlPage searchResults = null;

      try {
         page = webClient.getPage("https://abc.austintexas.gov/web/permit/public-search-other");

         HtmlForm form = getFormFromPage(page);

         HtmlTextInput txField = form.getInputByName("d_1400695220645");
         if (txField == null) return;
         txField.setText("c14");

         List<HtmlInput> inputs = form.getInputsByValue("Submit");
         for (HtmlInput input: inputs) {
            System.out.println(" - name : " + input.getNameAttribute());
            if ("d_1400695220646".equals(input.getNameAttribute())) {
               System.out.println("clicking...");
               searchResults = input.click();
               Thread.sleep(4000);
               break;
            }
         }

       //  while (traverseResult(searchResults))
         //   System.out.println("===================== Another page: ========================");

         traverseResult(searchResults);
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
