package wunsch.brw.learning.model;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class FolderInfo implements Serializable {

   static final long serialVersionUID = 1L;

   private ZoningProject project;
   private  HashMap<String, String> folderInfo = new HashMap<>();


   public FolderInfo(HtmlTable table) {

      for (HtmlTableRow row : table.getRows()) {

         Iterator<HtmlTableCell> cells = row.getCellIterator();

         String key = null;
         String value = null;
         if (cells.hasNext())
            key = cells.next().asText();
         if (cells.hasNext())
            value = cells.next().asText();

         if ("Description".equals(key))
            continue; //don't store table header

         if (key != null && !key.isEmpty() && value != null && !value.isEmpty())
            this.folderInfo.put(key,value);
      }

   }

   public void setProject (ZoningProject proj) {
      this.project = proj;
   }

   public String toString () {
      return "FolderInfo with: " + folderInfo.size() + " values...";
   }
}
