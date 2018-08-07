package wunsch.brw.learning.util;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

import java.util.List;

public class PageAnchors {

   List<HtmlAnchor> anchors;

   public PageAnchors(List<HtmlAnchor> anchors) {
      this.anchors = anchors;
   }

   public HtmlAnchor find (String name) {
      for (HtmlAnchor anchor: anchors) {
         if (name.equals(anchor.getFirstChild().asText()))
            return anchor;
      }
      return null;
   }
}
