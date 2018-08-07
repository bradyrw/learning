package wunsch.brw.learning;

import wunsch.brw.learning.model.GatheredData;
import wunsch.brw.learning.model.ZoningProject;
import wunsch.brw.learning.stats.SortedCounts;
import wunsch.brw.learning.stats.StatUtils;
import wunsch.brw.learning.util.FuzzyPersonComparator;

import java.util.*;

public class CalculateStatistics {


   private TreeMap<String,List<ZoningProject>> firms = new TreeMap<String,List<ZoningProject>>(new FuzzyPersonComparator());
   private TreeMap<String,List<ZoningProject>> people = new TreeMap<String,List<ZoningProject>>(new FuzzyPersonComparator());

   public void load () {
      GatheredData data = new GatheredData();
      data.load();

      int nullpeople = 0;
      int badprojects = 0;

      for (ZoningProject proj: data.getProjects())
      {
         if (!proj.getId().shouldTraverse()) {
            badprojects ++;
         }
         else if (proj.getPeopleDetails() == null) {
            System.out.println("Null People: " + proj.getId());
            nullpeople++;
         }
         else {
            String applicant = proj.getPeopleDetails().getApplicantName();
            String firm = proj.getPeopleDetails().getApplicantFirm();

            List<ZoningProject> projects = firms.get(firm);
            if (projects == null)
               projects = new ArrayList<>();
            projects.add(proj);
            firms.put(firm, projects);

            projects = people.get(applicant);
            if (projects == null)
               projects = new ArrayList<>();
            projects.add(proj);
            people.put(applicant,projects);

         }
      }

      SortedCounts firmSorted = new SortedCounts(firms);
      SortedCounts peopleSorted = new SortedCounts(people);

      for (Map.Entry<String, List<ZoningProject>> firm: firmSorted.entrySet()){
         System.out.println ("<"+firm.getValue().size()+ "> Firm: " + firm.getKey());
         if (firm.getKey().isEmpty()) {
            for (ZoningProject proj: firm.getValue()) {
               if (proj.getPeopleDetails().getApplicantName().isEmpty()) {
                  System.out.println("    # " + proj.getId());
                  System.out.println("    # " + proj.getPeopleDetails().getApplicant());
               }
               System.out.println ("   = " + proj.getPeopleDetails().getApplicantName());
            }
         }

      }
      for (Map.Entry<String, List<ZoningProject>> person: peopleSorted.entrySet()){
         System.out.println ("<"+person.getValue().size()+ "> Person: " + person.getKey());
         if (person.getKey().toLowerCase().isEmpty()) {
            for (ZoningProject proj: person.getValue()) {
               if (proj.getPeopleDetails().getApplicantFirm().isEmpty()) {
                  System.out.println("    # " + proj.getId());
                  System.out.println("    # " + proj.getPeopleDetails().getApplicant());
               }
               else
                  System.out.println ("   = " + proj.getPeopleDetails().getApplicantFirm());
            }
         }
      }

      System.out.println("Loaded Projects: " + data.getProjects().size());
      System.out.println(" - null people: " + nullpeople);
      System.out.println(" - bad projects: " + badprojects);


      StatUtils.projectsToCSV(data.getProjects());

   }


   public static void main (String [] args) {
      CalculateStatistics calc = new CalculateStatistics();
      calc.load();
   }

}
