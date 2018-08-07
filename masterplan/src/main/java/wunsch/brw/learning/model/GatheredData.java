package wunsch.brw.learning.model;

import wunsch.brw.learning.model.ZoningProject;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GatheredData implements Serializable {

   static final long serialVersionUID = 1L;

   private LinkedList<ZoningProject> projects = new LinkedList<>();


   public void addProject(ZoningProject proj) {
      if (proj != null)
         projects.add(proj);
   }

   public List<ZoningProject> getProjects () {
      return projects;
   }


   public void dump () {
      for (ZoningProject zp: projects) {
         System.out.println(zp);
      }
   }

   public void persist () {
      try {
         FileOutputStream fos = new FileOutputStream("/Users/brady/zoningProjects.ser");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(projects);
         oos.close();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void load () {
      try {
         FileInputStream fis = new FileInputStream("/Users/brady/zoningProjects.ser");
         ObjectInputStream ois = new ObjectInputStream(fis);
         projects = (LinkedList<ZoningProject>) ois.readObject();
         ois.close();
      }
      catch (Exception e) {
      e.printStackTrace();
      }
   }
}
