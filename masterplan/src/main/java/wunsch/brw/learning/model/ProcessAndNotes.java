package wunsch.brw.learning.model;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProcessAndNotes implements Serializable {

   static final long serialVersionUID = 1L;

   private List<Process> processList = new ArrayList<>();
   private ZoningProject project;

   

   public ProcessAndNotes(HtmlTable table) {

      for (HtmlTableRow row : table.getRows()) {

         Iterator<HtmlTableCell> cells = row.getCellIterator();
         if (cells.hasNext()) {
            if ("Process Description".equals(cells.next().asText())) //skip table header
               continue;
            else
               processList.add(new Process(row));
         }

      }
   }

   public List<Process> getProcessList () {
      return processList;
   }

   public void setProject(ZoningProject project) {
      this.project = project;
   }

   public String toString () {
      int numMidMeetings = 0;
      int numCouncilMeetings = 0;
      Date lastMeeting = null;
      Date firstMeeting = null;

      StringBuilder bldr = new StringBuilder();
      for (Process p: processList) {
         if ("NPZ Council Hearing".equals(p.getDescription())) {
            numCouncilMeetings ++;
            if (lastMeeting == null || (p.getStartDate()!= null && lastMeeting.before(p.getStartDate())))
               lastMeeting = p.getStartDate();
            if (firstMeeting == null || (p.getStartDate()!= null && firstMeeting.after(p.getStartDate())))
               firstMeeting = p.getStartDate();
         }
         else if ("PC Hearing".equals(p.getDescription())) {
            numMidMeetings ++;
            if (lastMeeting == null || (p.getStartDate()!= null && lastMeeting.before(p.getStartDate())))
               lastMeeting = p.getStartDate();
            if (firstMeeting == null || (p.getStartDate()!= null && firstMeeting.after(p.getStartDate())))
               firstMeeting = p.getStartDate();
         }
         else if ("Zap Hearing".equals(p.getDescription())) {
            numMidMeetings ++;
            if (lastMeeting == null || (p.getStartDate()!= null && lastMeeting.before(p.getStartDate())))
               lastMeeting = p.getStartDate();
            if (firstMeeting == null || (p.getStartDate()!= null && firstMeeting.after(p.getStartDate())))
               firstMeeting = p.getStartDate();
         }
      }
      bldr.append("MidMeetings: " + numMidMeetings + ", CouncilMeetings: " + numCouncilMeetings);

      int days = 0;
      if (firstMeeting != null && lastMeeting != null) {
         long diff = lastMeeting.getTime() - firstMeeting.getTime();
         days = (int)(diff / (1000*60*60*24));
      }

      bldr.append(", MeetingTime: " + days + " days");
      return bldr.toString();
   }
   
   
}
