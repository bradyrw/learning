package wunsch.brw.learning.model;

import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.Serializable;
import java.util.Iterator;

public class ZoningProjectID implements Serializable {

   static final long serialVersionUID = 1L;

   private int id;
   private String referenceFileName;
   private String permitCase;
   private String description;
   private String type;
   private String projectName;
   private String status;
   private String relatedFolder;

   public int getId() {
      return id;
   }

   public String getReferenceFileName() {
      return referenceFileName;
   }

   public String getPermitCase() {
      return permitCase;
   }

   public String getDescription() {
      return description;
   }

   public String getType() {
      return type;
   }

   public String getProjectName() {
      return projectName;
   }

   public String getStatus() {
      return status;
   }

   public String getRelatedFolder() {
      return relatedFolder;
   }

   public ZoningProjectID (HtmlTableRow row) {
      Iterator<HtmlTableCell> iter = row.getCellIterator();
      id = Integer.parseInt(iter.next().asText());
      permitCase = iter.next().asText();
      referenceFileName = iter.next().asText();
      description = iter.next().asText();
      type = iter.next().asText();
      projectName = iter.next().asText();
      status = iter.next().asText();
      relatedFolder = iter.next().asText();
   }

   public boolean shouldTraverse () {
      return type.contains("Zoning");//&& ("closed".equalsIgnoreCase(status) || "approved".equalsIgnoreCase(status) || "denied".equalsIgnoreCase(status));
   }

   public String toString () {
      if (shouldTraverse())
         return "("+id+") Ref: " + referenceFileName +  ", type: " + type + ", status: " + status;
      else
         return "<"+id+"> Ref: " + referenceFileName +  ", type: " + type + ", status: " + status;
   }


}
