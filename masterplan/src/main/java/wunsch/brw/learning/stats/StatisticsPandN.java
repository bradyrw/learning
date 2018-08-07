package wunsch.brw.learning.stats;

import wunsch.brw.learning.model.Process;
import wunsch.brw.learning.model.ProcessAndNotes;

import java.util.Date;

public class StatisticsPandN {

   Date firstCouncilHearing = null;
   Date lastCouncilHearing = null;

   Process earliestStartDate = null;
   Date lastProcessDate = null;
   Process lastProcess = null;

   public Date getFirstCouncilHearing() {
      return firstCouncilHearing;
   }

   public Date getLastCouncilHearing() {
      return lastCouncilHearing;
   }

   public Process getEarliestStartDate() {
      return earliestStartDate;
   }

   public Date getLastProcessDate() {
      return lastProcessDate;
   }

   public Process getLastProcess() {
      return lastProcess;
   }

   public int getDaysBetweenFirstAndLast() {
      return daysBetweenFirstAndLast;
   }

   public int getNumFirstLevelHearings() {
      return numFirstLevelHearings;
   }

   public int getNumCouncilHearings() {
      return numCouncilHearings;
   }

   int daysBetweenFirstAndLast;
   int numFirstLevelHearings;
   int numCouncilHearings;



   public StatisticsPandN (ProcessAndNotes input) {

      StringBuilder bldr = new StringBuilder();
      for (Process p: input.getProcessList()) {
         if ("NPZ Council Hearing".equalsIgnoreCase(p.getDescription())) {
            numCouncilHearings ++;
            if (lastCouncilHearing == null || (p.getStartDate()!= null && lastCouncilHearing.before(p.getStartDate())))
               lastCouncilHearing = p.getStartDate();
            if (firstCouncilHearing == null || (p.getStartDate()!= null && firstCouncilHearing.after(p.getStartDate())))
               firstCouncilHearing = p.getStartDate();
         }
         else if ("PC Hearing".equalsIgnoreCase(p.getDescription())) {
            numFirstLevelHearings ++;
            if (lastCouncilHearing == null || (p.getStartDate()!= null && lastCouncilHearing.before(p.getStartDate())))
               lastCouncilHearing = p.getStartDate();
            if (firstCouncilHearing == null || (p.getStartDate()!= null && firstCouncilHearing.after(p.getStartDate())))
               firstCouncilHearing = p.getStartDate();
         }
         else if ("Zap Hearing".equalsIgnoreCase(p.getDescription())) {
            numFirstLevelHearings ++;
            if (lastCouncilHearing == null || (p.getStartDate()!= null && lastCouncilHearing.before(p.getStartDate())))
               lastCouncilHearing = p.getStartDate();
            if (firstCouncilHearing == null || (p.getStartDate()!= null && firstCouncilHearing.after(p.getStartDate())))
               firstCouncilHearing = p.getStartDate();
         }
         if (p.getStartDate() != null) {
            if (earliestStartDate == null || earliestStartDate.getStartDate().after(p.getStartDate())) {
               earliestStartDate = p;
            }
         }

         if (p.getEndDate() != null) {
            if (lastProcessDate == null || p.getEndDate().after(lastProcessDate)) {
               lastProcess = p;
               lastProcessDate = p.getEndDate();
            }
         }
         else if (p.getStartDate() != null) {
            if (lastProcessDate == null || p.getStartDate().after(lastProcessDate)) {
               lastProcess = p;
               lastProcessDate = p.getStartDate();
            }
         }

      }

      int days = 0;
      if (firstCouncilHearing != null && lastCouncilHearing != null) {
         long diff = lastCouncilHearing.getTime() - firstCouncilHearing.getTime();
         days = (int)(diff / (1000*60*60*24));
      }
      daysBetweenFirstAndLast = days;
   }

}
