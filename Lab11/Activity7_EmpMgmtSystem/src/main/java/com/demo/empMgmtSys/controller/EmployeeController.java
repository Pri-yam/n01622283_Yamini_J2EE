package com.demo.empMgmtSys.controller;

import com.demo.empMgmtSys.model.Employee;
import com.demo.empMgmtSys.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model, @ModelAttribute("msg") String msg, @ModelAttribute("error") String error){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {

        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee,
                               BindingResult result,
                               Model model,
                               RedirectAttributes ra) {

        if(result.hasErrors()){
            return employee.getId() == null? "addEmployee":"update_employee";
        }
        try {
            employeeService.saveEmployee(employee);
        } catch (DataIntegrityViolationException ex) {
            // Add custom field error
            result.addError(new FieldError("employee", "email",
                    "This email is already in use. Please use a different email."));
            return employee.getId() == null ? "addEmployee" : "update_employee";
        }

        // add success message
        ra.addFlashAttribute("msg",
                employee.getId() == null ? "Employee created successfully." : "Employee updated successfully.");
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model, RedirectAttributes ra) {

        try {
            // get employee from the service
            Employee employee = employeeService.getEmployeeById(id);
            // set employee as a model attribute to pre-populate the form
            model.addAttribute("employee", employee);
            return "update_employee";
        } catch(RuntimeException notFound){
            ra.addFlashAttribute("error", "Employee with ID " + id + " was not found.");
            return "redirect:/";
        }
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id, RedirectAttributes ra) {
        try {
            // call delete employee method
            this.employeeService.deleteEmployeeById(id);
            return "redirect:/";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Cannot delete employee with ID " + id + ".");
        }
        return "redirect:/";
    }

    //------ Find by ID -------
    // Show find form
    @GetMapping("/findEmployee")
    public String showFindEmployeeForm() {
        return "find_employee";
    }
}
