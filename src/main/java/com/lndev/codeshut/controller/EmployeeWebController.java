package com.lndev.codeshut.controller;

import com.lndev.codeshut.dto.EmployeeDto;
import com.lndev.codeshut.entities.Employee;
import com.lndev.codeshut.repository.EmployeeRepository;
import com.lndev.codeshut.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class EmployeeWebController {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @GetMapping("/employees/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        return "add";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@Valid @ModelAttribute("employee") EmployeeDto employeeDto , BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "add";
        }
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employe enregistre avec succes!");
        return "redirect:/employees/add";
    }

    @GetMapping("/divide/{a}divideBy{b}")
    public int divide(@PathVariable int a, @PathVariable int b) {
        return a / b;
    }

}
