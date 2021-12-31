package com.greatlearning.debate_event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.debate_event.entities.Students_Info;
import com.greatlearning.debate_event.services.Services;

@Controller
@RequestMapping("/students")
public class Event_Controller {
	//autowire service class
	
		@Autowired
		private Services  studentservice;
		
		
		@RequestMapping("/list")
		public String listStudents(Model model) {
			List<Students_Info> students = studentservice.findAll();
			model.addAttribute("students", students);
			return "studentlist";
		}
		
		@RequestMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel) {

			Students_Info student = new Students_Info();
			theModel.addAttribute("student", student);

			// send over to our form
			return "newform";
		}

		@RequestMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {

			// get the student from the service
			Students_Info std = studentservice.findById(theId);

			// set Student as a model attribute to pre-populate the form
			theModel.addAttribute("student", std);

			// send over to our form
			return "newform";
		}

		@PostMapping("/save")
		public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
				@RequestParam("department") String department, @RequestParam("country") String country) {

			System.out.println(id);
			Students_Info std;
			if (id != 0) {
				std = studentservice.findById(id);
				std.setName(name);
				std.setCountry(country);
				std.setDepartment(department);
			} else
				std = new Students_Info(name, department, country);
			// save the Student
			studentservice.save(std);

			// use a redirect to prevent duplicate submissions
			return "redirect:/students/list";

		}

		@RequestMapping("/delete")
		public String delete(@RequestParam("id") int theId) {

			// delete the student
			studentservice.deleteById(theId);

			return "redirect:/students/list";

		}


}
