package com.almin.country.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.almin.country.entity.Country;
import com.almin.country.service.CountryService;

import view.CountryDataPdfExport;

@Controller
public class CountryController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CountryService service;

	// ZA PDF
	@GetMapping("/pdf")
	public ModelAndView exportPdf() {
		ModelAndView mv = new ModelAndView();
		mv.setView(new CountryDataPdfExport());
		// read data from dataBase
		List<Country> list = service.getAllCountry();
		// send to pdfImpl class
		mv.addObject("list", list);
		return mv;
	}

	@GetMapping("/")
	public String getHome(Model model) {
		List<Country> listOfAllCountry = service.getAllCountry();
		model.addAttribute("list", listOfAllCountry);
		return "home";
	}

	@GetMapping("/newCountry")
	public String createNewCountry(Model model) {
		Country country = new Country();
		model.addAttribute("country", country);
		return "new_country";
	}

	@PostMapping("/save")
	public String insertCountry(@ModelAttribute("country") Country country) {
		service.saveCountry(country);
		return "redirect:/";

	}

	@GetMapping("/edit/{id}")
	public ModelAndView editCountry(@PathVariable(name = "id") Integer id) {
		ModelAndView mv = new ModelAndView("edit_country");
		Country country = service.getOneCountry(id);
		mv.addObject("country", country);
		return mv;
	}

	@GetMapping("/delete/{id}")
	public String deleteCountry(@PathVariable(name = "id") Integer id) {
		service.deleteCountry(id);
		return "redirect:/";
	}

	@GetMapping("findOne")
	@ResponseBody
	public Country findOneCountry(Integer id) {
		return service.getOneCountry(id);
	}

	// filter
	@RequestMapping(path = { "/", "/search" })
	public String filter(Model model, String keyword) {

		if (keyword != null) {
			List<Country> list = service.filterCountry(keyword);
			model.addAttribute("list", list);
		} else {
			List<Country> list = service.getAllCountry();
			model.addAttribute("list", list);
		}
		return "home";
	}

	// Sort
	@RequestMapping("/sortDesc")
	public String sortList(Model model) {
		List<Country> list = service.sortDecending();
		model.addAttribute("list", list);
		return "home";
	}

	@RequestMapping("/sortAsc")
	public String sortListAscending(Model model) {
		List<Country> list = service.sortAscending();
		model.addAttribute("list", list);
		return "home";
	}

	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {

		String returnValue = "home";
		try {
			service.saveImage(imageFile);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error saving photos", e);
			returnValue = "error";
		}

		return returnValue;
	}

}
