package wunsch.brw.learning.stats;

import wunsch.brw.learning.model.ZoningProject;

import java.text.SimpleDateFormat;
import java.util.List;

public class StatUtils {

   static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


   public static void csvValidProjects () {


   }

   public static void projectsToCSV (List<ZoningProject> projects) {
      projectsToCSV(projects,  new ProjectFilter());
   }

   public static void projectsToCSV (List<ZoningProject> projects, ProjectFilter filter) {

      System.out.println (
            "Row, " +
                  "Reference, " +
                  "Project Name, " +
                  "Description," +
                  "Status, " +
                  "Application Date, " +
                  "Completed Date, " +
                  "Issued?," +
                  "Applicant Firm," +
                  "Applicant Name," +
                  "Client Name," +
                  "Client Firm," +
                  "Total Fees," +
                  "First Hearing," +
                  "Last Hearing," +
                  "Num ZAP/PC," +
                  "Num Council");

      for (ZoningProject proj: projects) {
         if (!filter.passesFilter(proj))
            continue;

         StatisticsPandN pandN = new StatisticsPandN(proj.getProcessAndNotes());
         System.out.println(
               proj.getId().getId() + "," +
                     proj.getId().getReferenceFileName() + "," +
                     "\"" + proj.getId().getProjectName() +  "\"," +
                     "\"" + proj.getId().getDescription() +  "\"," +
                     "\"" + proj.getId().getStatus()  +  "\"," +
                     (proj.getDetails().getAppDate() == null ? "NULL": formatter.format(proj.getDetails().getAppDate()))+ "," +
                     (proj.getDetails().getIssued() == null ?
                           (pandN.getLastProcessDate() == null ? "NULL" :  formatter.format(pandN.getLastProcessDate()))
                        +",N," : formatter.format(proj.getDetails().getIssued()) + ",Y,") +
                     "\"" + proj.getPeopleDetails().getApplicantFirm() +  "\"," +
                     "\"" + proj.getPeopleDetails().getApplicantName() +  "\"," +
                     "\"" + proj.getPeopleDetails().getClienttName() + "\"," +
                     "\"" + proj.getPeopleDetails().getClientFirm() +  "\"," +
                     "\"$"+ (proj.getFees() == null ? "0.0" : proj.getFees().getTotalFees()) + "\"," +
                     (pandN.getFirstCouncilHearing() == null ? "" : formatter.format(pandN.getFirstCouncilHearing())) + "," +
                     (pandN.getLastCouncilHearing() == null ? "" : formatter.format(pandN.getLastCouncilHearing())) + "," +
                     pandN.getNumFirstLevelHearings() + "," +
                     pandN.getNumCouncilHearings());
      }
   }

   public static class ProjectFilter {

      public boolean passesFilter (ZoningProject project) {
         return project.shouldTraverse();
      }
   }
}
