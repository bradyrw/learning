package wunsch.brw.learning.model;

import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class Process implements Serializable {
   static final long serialVersionUID = 1L;

   SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");

   private String description;

   public String getDescription() {
      return description;
   }

   public String getStatus() {
      return status;
   }

   public Date getStartDate() {
      return startDate;
   }

   public Date getScheduledEndDate() {
      return scheduledEndDate;
   }

   public Date getEndDate() {
      return endDate;
   }

   public String getAssignedStaff() {
      return assignedStaff;
   }

   public int getNoAttempts() {
      return noAttempts;
   }

   private String status;
   private Date startDate;
   private Date scheduledEndDate;
   private Date endDate;
   private String assignedStaff;
   private int noAttempts = 0;

   //Process Description
   // Status
   //Start Date
   //Scheduled End Date
   // End Date
   //Assigned Staff
   //# of Attempts
   public Process(HtmlTableRow row) {
      Iterator<HtmlTableCell> cells = row.getCellIterator();

      if (cells.hasNext())
         description = cells.next().asText();
      if (cells.hasNext())
         status = cells.next().asText();
      if (cells.hasNext())
         startDate = parseDate(cells.next().asText());
      if (cells.hasNext())
         scheduledEndDate = parseDate(cells.next().asText());
      if (cells.hasNext())
         endDate = parseDate(cells.next().asText());
      if (cells.hasNext())
         assignedStaff = cells.next().asText();
      if (cells.hasNext()) {
         String attmeptsStr = cells.next().asText();
         if (attmeptsStr != null && !attmeptsStr.isEmpty())
            noAttempts = Integer.parseInt(attmeptsStr);
      }
   }

   private Date parseDate (String dateStr) {
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


   public String toString () {
      return "Proc: " + description + ", starting: " + startDate + ", ending: "+ endDate;
   }
}
