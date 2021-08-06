package org.jcerdar.backend.apirest.jornadas.controllers;

import java.util.List;

import org.jcerdar.backend.apirest.jornadas.models.entity.Jornada;
import org.jcerdar.backend.apirest.jornadas.models.services.JornadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins= "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class JornadaRestController {

	@Autowired
	private JornadasService jornadasService; 

	
	@GetMapping("/jornadas")
	public List<Jornada> index() {
		return jornadasService.findAll(); 
	}
	
	@GetMapping("/jornadas/{id}")
	public Jornada show(@PathVariable Long id) {
		return jornadasService.findById(id); 
	}
	
	@PostMapping("/jornadas")
	@ResponseStatus(HttpStatus.CREATED)
	public Jornada create(@RequestBody Jornada jornada) {
		return jornadasService.save(jornada); 
	}
	
	@PutMapping("/jornadas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Jornada update(@RequestBody Jornada jornada, @PathVariable Long id) {
		Jornada jornadaActual = jornadasService.findById(id); 
		jornadaActual.setDirViento(jornada.getDirViento()); 
		jornadaActual.setDuracion(jornada.getDuracion()); 
		jornadaActual.setFaseLuna(jornada.getFaseLuna()); 
		jornadaActual.setFechaSalida(jornada.getFechaSalida()); 
		jornadaActual.setFuerzaViento(jornada.getFuerzaViento()); 
		jornadaActual.setModalidad(jornada.getModalidad()); 
		jornadaActual.setObservaciones(jornada.getObservaciones()); 
		jornadaActual.setResultado(jornada.getResultado()); 
		
		return jornadasService.save(jornadaActual); 
	}
	
	@DeleteMapping("/jornadas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		jornadasService.delete(id); 
	}
}
