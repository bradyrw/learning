package wunsch.brw.learning;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import wunsch.brw.learning.model.*;
import wunsch.brw.learning.util.GatherUtils;
import wunsch.brw.learning.util.PageAnchors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class RunGatherData {

   static {
      java.util.logging.Logger.getLogger("com.gargoylesoftware").
            setLevel(Level.OFF);
      System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
   }

   private GatheredData data = new GatheredData();


   public RunGatherData() {

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

   // ==================================================== Traverse A Project Details Page.. Create ZoningProject
   private void gatherDetails (ZoningProject project, HtmlPage page) {
      List<HtmlDivision> divs = page.getByXPath( "//div[@class='inline mediumcolumn']");
      project.setDetails(new FolderDetails(divs));

      // HtmlDivision division =  page.getFirstByXPath("//*[@id=\"sgControls\"]/div[1]");
      List<HtmlTable> tables = page.getByXPath("//table");

      for (HtmlTable table: tables) {
         if (GatherUtils.isProcessAndNotes(table)) {
            ProcessAndNotes processAndNotes = new ProcessAndNotes(table);
            project.setProcessAndNotes(processAndNotes);
         }
         else if (GatherUtils.isFolderFees(table)) {
            Fees fees = new Fees(table); //fees
            project.setFees(fees);
         }
         else if (GatherUtils.isPeopleDetails(table)) {
            PeopleDetails peopleDetails = new PeopleDetails(table);
            project.setPeopleDetails(peopleDetails);
         }
         else if (GatherUtils.isPropertyDetails(table))
            continue;
         else if (GatherUtils.isFolderInfo(table)) {
            FolderInfo folderInfo = new FolderInfo(table);
            project.setFolderInfo(folderInfo);
         }

      }

      Iterator<HtmlTable> tableIter = tables.iterator();
      //may not be there.. need to rethink.. how to figure out what table it is
      tableIter.next(); //property details

   }
   // ==================================================== If its a project, create the project click it to start collection
   private void startProjectCollection(HtmlTableRow row, PageAnchors anchors) {
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
         return; //not a project
      }
     try {
         Integer.parseInt(row.getCell(0).asText());
     }
     catch (Exception e) {
         System.out.println("not a valid row.." + row.getCell(0).asText());
         return;
     }

      ZoningProject proj = new ZoningProject(new ZoningProjectID(row));
      data.addProject(proj);
      System.out.println(proj);

      if (proj.shouldTraverse()) {
         HtmlTableCell cell = row.getCell(1);
         try {
            System.out.println("Clicking on: " + cell.asText());
            HtmlAnchor toClickOn = anchors.find(cell.asText());
            HtmlPage detailsPage = toClickOn.click();
            Thread.sleep(1000);
            gatherDetails(proj, detailsPage);
            // gatheredData.addProject(gatherDetails(detailsPage));
            //System.out.println("After Data Gathering:");
            //System.out.println(proj);
         }
         catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   // ==================================================== Iterate the table of Projects
   private void iterateProjectsInTable (HtmlPage page, PageAnchors anchors) {
      HtmlTable table = page.getFirstByXPath("//table");

      int i = 0;
      for (final HtmlTableRow row : table.getRows()) {
         i++;
         startProjectCollection(row,anchors);
      }
   }

   // ==================================================== Traverse A Project Details Page.. Create ZoningProject
   private void processOnePage(HtmlPage _page) {

         List<HtmlAnchor> anchorsRaw = _page.getAnchors();
         PageAnchors anchors = new PageAnchors(anchorsRaw);

         iterateProjectsInTable(_page, anchors);
   }

   // ==================================================== Start at main page.  Search and get a page of results
   public void gather() {

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


         List<HtmlPage> toSearch = new ArrayList<HtmlPage>();
         toSearch.add(searchResults);

         List<HtmlAnchor> anchorsRaw = searchResults.getAnchors();
         PageAnchors anchors = new PageAnchors(anchorsRaw);
         HtmlAnchor nextPageAnchor = anchors.find("next");

         int pg = 1;
         while (nextPageAnchor != null) {
            System.out.println("page: " + (pg++) );
            searchResults = nextPageAnchor.click();
            toSearch.add(searchResults);
            anchorsRaw = searchResults.getAnchors();
            anchors = new PageAnchors(anchorsRaw);
            nextPageAnchor = null;
            nextPageAnchor = anchors.find("next");
         }
         pg = 1;
         for (HtmlPage singlePage: toSearch) {
            System.out.println("page: " + (pg++) );
            processOnePage(singlePage);
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void persist () {
      this.data.persist();
   }


   public static void main (String [] args) {

      RunGatherData dataGatherer = new RunGatherData();

      dataGatherer.gather();
      System.out.println("Dumping!!");
      dataGatherer.data.dump();
      dataGatherer.persist();
   }
}
