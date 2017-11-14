package wunsch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Endpoint {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
    @RequestMapping("/poke")
    public String handleConvertRequest(@RequestParam(value="with", required=false) String input) {
        return "Poked with: " + (input == null ? " nothing!" : " " + input);
    }
    @RequestMapping("/repoke")
    public String handleAnotherRequest() {
        return "Repoked!";
    }
}
