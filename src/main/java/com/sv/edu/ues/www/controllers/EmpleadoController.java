package com.sv.edu.ues.www.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sv.edu.ues.www.models.Empleado;
import com.sv.edu.ues.www.service.IEmpleadoService;

@Controller
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "nombre", "asc", model);
	}
    
	@GetMapping("/mostrarNuevoEmpleadoForm")
	public String mostrarNuevoEmpleadoForm(Model model){
		Empleado empleadito=new Empleado();
		model.addAttribute("empleadoNuevo",empleadito);
		return "nuevoEmpleado";
	}
	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(@ModelAttribute("empleado")Empleado empleadoObj) {
		this.empService.guardarEmpleado(empleadoObj);
		return "redirect:/";
	}
	@GetMapping("/mostrarformParaActualizar/{id}")
	public String mostrarformParaActuañizarDatos(@PathVariable(value="id")Long id,Model model) {
		Empleado empleado=this.empService.obtenerEmpleadoById(id);
		model.addAttribute("emp",empleado);
		return "actualizarEmpleado";
	}
	@GetMapping("/eliminarEmpleado/{id}")
	public String eliminarDatosEmpleado(@PathVariable(value="id")Long id) {
	this.empService.eliminarEmpleadoById(id);
		
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Empleado> page = empService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Empleado> listEmpleados = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listEmpleados", listEmpleados);
		return "index";
	}
	
}
