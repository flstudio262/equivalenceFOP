package com.testHibernate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.service.CINService;

@Controller
public class CINController {
	 private CINService cinService;
	 
	 @Autowired
	 public void setCINService(CINService cinService) {
        this.cinService = cinService;
	 }
	 
	 @RequestMapping("/cin/show/{id}")
	 public String getCIN(@PathVariable String id, Model model){
		 model.addAttribute("cin", cinService.getById(Long.valueOf(id)));
		 return "pages/enregistrement/showCIN";
	 }
	 
	 @RequestMapping({"/CINList", "/CIN"})
	 public String listCIN(Model model){
        model.addAttribute("listCIN", cinService.listAll());
        return "pages/enregistrement/CINList";
	 }	
	 @GetMapping("/newCIN")
	 public String ajouterCIN(Model model) {
		 model.addAttribute("listCIN", cinService.listAll());
		 model.addAttribute("cinForm", new CINForm());
		 return "pages/enregistrement/newCIN";		
	 }
	
	 @RequestMapping(value = "/saveCIN", method = RequestMethod.POST)
	 public String saveOrUpdateCIN(@Valid  @ModelAttribute CINForm cinForm, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "pages/enregistrement/newCIN";
		 }
	
		 CIN savedCIN = cinService.saveOrUpdateCINForm(cinForm);

		 return "redirect:cin/show/" + savedCIN.getId();
	 }
	 
	 @RequestMapping("/cin/delete/{id}")
	 public String delete(@PathVariable String id){
        cinService.delete(Long.valueOf(id));
        return "redirect:/CINList";
	 }
	

}