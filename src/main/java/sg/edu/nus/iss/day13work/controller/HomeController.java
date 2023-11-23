package sg.edu.nus.iss.day13work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/pagea")
    public String pageA(Model model, HttpSession session) {
        
        if (session.getAttribute("myFullName") != null) {
            model.addAttribute("sessionData", session.getAttribute("myFullName").toString());
        } else {
            model.addAttribute("sessionData", "There is no session object now");
        }

        return "pagea";
    }

    @PostMapping("/pagea")
    public String pageAPost(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session) {

        String myFullName = form.getFirst("fullName");
        System.out.printf("My full name is %s\n", myFullName);

        session.setAttribute("myFullName", myFullName);

        model.addAttribute("myName", session.getAttribute("myFullName").toString());
        return "pageb";
    } 

    @GetMapping("/pageb")
    public String pageB(Model model, HttpSession session) {
        
        String myFullName = session.getAttribute("myFullName").toString();

        model.addAttribute("myName", myFullName);
        return "pagec";
    }

    @PostMapping("/pagec")
    public String pageC(Model model, HttpSession session) {

        session.invalidate();
        model.addAttribute("sessionData", "There is no session object now");

        return "pagea";
    }
    
}
