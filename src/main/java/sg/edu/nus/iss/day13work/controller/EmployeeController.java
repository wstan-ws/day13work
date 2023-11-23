package sg.edu.nus.iss.day13work.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.day13work.model.Employee;
import sg.edu.nus.iss.day13work.repo.EmployeeRepo;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // autowire helps to instantiate employeerepo
    @Autowired
    EmployeeRepo empRepo;   

    @GetMapping("/list")
    public String employeeList(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);
        return "employeelist";
    }

    @GetMapping("/addnew")
    public String employeeAdd(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        
        return "employeeadd";
    }

    @PostMapping("/saveEmployee")
    // sequence of parameters cannot change, else cannot work
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model model) throws FileNotFoundException {

        if (result.hasErrors()) {
            return "employeeadd";
        }

        Boolean returnResult = empRepo.save(employeeForm);

        // return "redirect:/employees/list";
        model.addAttribute("saveEmployee", employeeForm);
        return "success";
    }

    @GetMapping("/employeedelete/{email}")
    public String deleteEmployee(@PathVariable("email") String email) {

        Employee emp = empRepo.findByEmail(email);

        Boolean bResult = empRepo.delete(emp);

        return "redirect:/employees/list";
    }

    @GetMapping("/employeeupdate/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model) {

        Employee emp = empRepo.findByEmail(email);
        model.addAttribute("employee", emp);

        return "employeeupdate";
    }
}
