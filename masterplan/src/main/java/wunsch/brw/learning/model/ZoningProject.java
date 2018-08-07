package wunsch.brw.learning.model;

import java.io.Serializable;

public class ZoningProject implements Serializable {

   static final long serialVersionUID = 1L;

   private final ZoningProjectID id;
   private FolderDetails details;
   private Fees fees;

   public ZoningProjectID getId() {
      return id;
   }

   public FolderDetails getDetails() {
      return details;
   }

   public Fees getFees() {
      return fees;
   }

   public FolderInfo getFolderInfo() {
      return folderInfo;
   }

   public PeopleDetails getPeopleDetails() {
      if (peopleDetails == null)
         peopleDetails = new PeopleDetails(this);  //return empty details if it wasn't in the data
      return peopleDetails;
   }

   public ProcessAndNotes getProcessAndNotes() {
      return processAndNotes;
   }

   private FolderInfo folderInfo;
   private PeopleDetails peopleDetails;
   private ProcessAndNotes processAndNotes;

   public ZoningProject (ZoningProjectID id) {
      this.id = id;
   }

   public void setDetails(FolderDetails details) {
      this.details = details;
      details.setProject(this);
   }

   public String toString () {
      StringBuilder builder = new StringBuilder("==== "+ id.toString() + "=====");
      if (details != null) {
         builder.append("\n   -    Name: " + details.getProjName());
         builder.append("\n   - Applied: " + details.getAppDate());
         builder.append("\n   -  Issued: " + details.getIssued());
      }
      if (peopleDetails != null) {
         builder.append("\n   ** Applicant: " + peopleDetails.getApplicant());
         if (peopleDetails.getBilledTo() != null)
            builder.append("\n   **  BilledTo: " + peopleDetails.getBilledTo());
      }
      if (folderInfo != null) {
         builder.append("\n    ^^ " + folderInfo);
      }
      if (fees != null) {
         builder.append("\n    $$ " + fees);
      }
      if (processAndNotes != null) {
         builder.append("\n    ## " + processAndNotes);
      }

      return builder.toString();
   }

   public boolean shouldTraverse () {
      return id.shouldTraverse();
   }

   public void setPeopleDetails(PeopleDetails peopleDetails) {
      peopleDetails.setProject(this);
      this.peopleDetails = peopleDetails;
   }

   public void setFees(Fees fees) {
      fees.setProject(this);
      this.fees = fees;
   }

   public void setFolderInfo(FolderInfo folderInfo) {
      folderInfo.setProject(this);
      this.folderInfo = folderInfo;
   }

   public void setProcessAndNotes(ProcessAndNotes processAndNotes) {
      processAndNotes.setProject(this);
      this.processAndNotes = processAndNotes;
   }
}
