package wunsch.brw.learning.model;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.Serializable;
import java.util.Iterator;

public class PeopleDetails implements Serializable {

   static final long serialVersionUID = 1L;

   public String getApplicant() {
      return applicant;
   }

   public String getBilledTo() {
      return billedTo;
   }

   private ZoningProject project;
   private String applicant;
   private String billedTo;

   private transient String applicantName;
   private transient String applicantFirm;
   private transient String applicantAddress;
   private transient boolean applicantSplit;

   private transient String clientName;
   private transient String clientFirm;
   private transient String clientAddress;
   private transient boolean clientSplit;

   PeopleDetails (ZoningProject proj) {
      this.project = proj;
   }

   public PeopleDetails(HtmlTable inputTable) {

      for (HtmlTableRow row : inputTable.getRows()) {

         Iterator<HtmlTableCell> cells = row.getCellIterator();

         while (cells.hasNext()) {
            HtmlTableCell cell = cells.next();
            if ("Applicant".equals(cell.asText()))
               applicant = cells.next().asText();
            if ("Billed To".equals(cell.asText()))
               billedTo = cells.next().asText();
         }
      }
   }

   public void setProject(ZoningProject proj) {
      this.project = proj;
   }

   public String getApplicantName () {
      splitApplicant();
      return this.applicantName;
   }
   public String getApplicantFirm () {
      splitApplicant();
      return this.applicantFirm;
   }
   public String getApplicantAddress () {
      splitApplicant();
      return this.applicantAddress;
   }
   public String getClienttName () {
      splitClient();
      return this.clientName;
   }
   public String getClientFirm () {
      splitClient();
      return this.clientFirm;
   }
   public String getClientAddress () {
      splitClient();
      return this.clientAddress;
   }

   private void splitApplicant () {
      if (applicantSplit)
         return;

      applicantSplit = true;
      applicantAddress = "";
      applicantName = "";
      applicantFirm = "";

      if (applicant != null) {
         String[] appSplit = applicant.split("\n");
         if (appSplit.length > 0) {
            if (appSplit.length > 1)
               applicantAddress = appSplit[1].trim();

            String[] personSplit = appSplit[0].split("\\(");

            if (personSplit.length > 1) {
               applicantName = personSplit[1];
               applicantName = applicantName.replace(")","");
               applicantFirm = personSplit[0].trim();
            }
            else if (personSplit.length > 0) {
               applicantFirm = personSplit[0].trim();
            }
         }
      }
      if (applicantFirm.toLowerCase().contains("t & brown"))
         applicantFirm = "Armbrust & Brown";
      if (applicantFirm.toLowerCase().contains("drenner"))
         applicantFirm = "Drenner Group";
      if (applicantFirm.toLowerCase().contains("land strategies"))
         applicantFirm = "Land Strategies";
      if (applicantFirm.toLowerCase().contains("bury"))
         applicantFirm = "Bury & Partners";
      if (applicantName.toLowerCase().contains("hugo elizondo"))
         applicantName = "Hugo Elizondo Jr. P.E.";
   }

   private void splitClient () {
      if (clientSplit)
         return;

      clientSplit = true;
      clientAddress = "";
      clientName = "";
      clientFirm = "";

      if (billedTo != null) {
         String[] appSplit = billedTo.split("\n");
         if (appSplit.length > 0) {
            if (appSplit.length > 1)
               clientAddress = appSplit[1].trim();

            String[] personSplit = appSplit[0].split("\\(");

            if (personSplit.length > 1) {
               clientName = personSplit[1];
               clientName = clientName.replace(")","");
               clientFirm = personSplit[0].trim();
            }
            else if (personSplit.length > 0) {
               clientFirm = personSplit[0].trim();
            }
         }
      }
      if (clientFirm.toLowerCase().contains("t & brown"))
         clientFirm = "Armbrust & Brown";
      if (clientFirm.toLowerCase().contains("drenner"))
         clientFirm = "Drenner Group";
      if (clientFirm.toLowerCase().contains("land strategies"))
         clientFirm = "Land Strategies";
      if (clientFirm.toLowerCase().contains("bury"))
         clientFirm = "Bury & Partners";
      if (clientName.toLowerCase().contains("hugo elizondo"))
         clientName = "Hugo Elizondo Jr. P.E.";
   }
}
