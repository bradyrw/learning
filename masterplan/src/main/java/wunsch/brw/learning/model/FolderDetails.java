package wunsch.brw.learning.model;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import wunsch.brw.learning.model.ZoningProject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FolderDetails implements Serializable {

   static final long serialVersionUID = 1L;

   private ZoningProject project;

   //Permit/Case:
   private String caseNo;
   //Reference File Name:
   private String referenceFileName;
   private String description;
   private String type;
   private String workType;
   private String projName;
   private String status;
   private Date appDate;
   private Date issued;
   private Date expDate;

   public String getCaseNo() {
      return caseNo;
   }

   public String getReferenceFileName() {
      return referenceFileName;
   }

   public String getDescription() {
      return description;
   }

   public String getType() {
      return type;
   }

   public String getWorkType() {
      return workType;
   }

   public String getProjName() {
      return projName;
   }

   public String getStatus() {
      return status;
   }

   public Date getAppDate() {
      return appDate;
   }

   public Date getIssued() {
      return issued;
   }

   public Date getExpDate() {
      return expDate;
   }

   public String getFolder() {
      return folder;
   }

   private String folder;
//
//
//         2018-131060 ZC
//
//   C14-2018-0090
//
//   Description:
//   The Applicant proposes to rezone approximately 1.38 acres from SF-6-CO to SF-6
//
//   Sub Type:
//   Zoning/Rezoning
//
//   Work Type:
//
//   Project Name:
//   Denizen Two
//
//   Status:
//   In Review
//
//   Application Date:
//   Aug 3, 2018
//
//   Issued:
//
//   Expiration Date:
//   Jul 29, 2019
//
//   Related Folder:
//   No


   SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");

   public FolderDetails(List<HtmlDivision> details) {

      for (HtmlDivision div: details) {

         String[] detail = div.asText().split(":\n");

         if (detail[0].contains("Permit"))
            this.caseNo = parseValue(detail);
         else if (detail[0].contains("Reference"))
            this.referenceFileName = parseValue(detail);
         else if (detail[0].contains("Description"))
            this.description = parseValue(detail);
         else if (detail[0].contains("Sub Type"))
            this.type = parseValue(detail);
         else if (detail[0].contains("Work Type"))
            this.workType = parseValue(detail);
         else if (detail[0].contains("Name"))
            this.projName = parseValue(detail);
         else if (detail[0].contains("Status"))
            this.status = parseValue(detail);
         else if (detail[0].contains("Application"))
            this.appDate = parseDate(detail);
         else if (detail[0].contains("Issued"))
            this.issued = parseDate(detail);
         else if (detail[0].contains("Expiration"))
            this.expDate = parseDate(detail);
         else if (detail[0].contains("Folder"))
            this.folder = parseValue(detail);
         else
            System.out.println("Unknown Field: " + detail[0]);

      }
   }
   public void setProject (ZoningProject proj) {
      this.project = proj;
   }

   private String parseValue (String [] from) {

      if (from == null)
         return null;
      if (from.length > 1)
         return from[1];
      return null;
   }

   private Date parseDate (String [] datearr) {
      if (datearr.length < 2)
         return null;
      String dateStr = datearr[1];
      try {

         if (dateStr == null || dateStr.isEmpty())
            return null;
         else
            return formatter.parse(dateStr);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }
}
