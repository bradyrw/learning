package wunsch.brw.learning.model;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class Fees implements Serializable{

   static final long serialVersionUID = 1L;

   private ZoningProject project;
   private HashMap<String, String> feeInfo = new HashMap<>();
   private double totalFees = 0;


   public Fees(HtmlTable table) {

      for (HtmlTableRow row : table.getRows()) {

         Iterator<HtmlTableCell> cells = row.getCellIterator();

         String key = null;
         String value = null;
         if (cells.hasNext())
            key = cells.next().asText();
         if (cells.hasNext())
            value = cells.next().asText();

         if ("Fee Description".equals(key))
            continue; //don't store table header

         if (key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
            double cost = Double.parseDouble(value.replaceAll("[^\\d.]+", ""));
            totalFees += cost;
            this.feeInfo.put(key, value);
         }
      }

   }

   public double getTotalFees () {
      return this.totalFees;
   }

   public void setProject (ZoningProject proj) {
      this.project = proj;
   }

   public String toString () {
      return "Fees cost: "+ totalFees + " with: " + feeInfo.size() + " values...";
   }
}
