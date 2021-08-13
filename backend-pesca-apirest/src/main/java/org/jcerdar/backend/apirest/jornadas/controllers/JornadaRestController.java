package org.jcerdar.backend.apirest.jornadas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jcerdar.backend.apirest.jornadas.models.entity.Jornada;
import org.jcerdar.backend.apirest.jornadas.models.services.JornadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
	public ResponseEntity<?> show(@PathVariable Long id) {
		Jornada jornada = null; 
		Map<String, Object> response = new HashMap<>(); 
		try {
			jornada = jornadasService.findById(id);
		} 
		catch(DataAccessException e) {
			response.put("mensaje", "Error al lanzar la consulta a la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		
		if(jornada == null) {
			response.put("mensaje", "La jornada con ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Jornada>(jornada, HttpStatus.OK); 
	}
	
	@PostMapping("/jornadas")
	public ResponseEntity<?> create(@RequestBody Jornada jornada) {
		Jornada jornadaNueva = null;  
		Map<String, Object> response = new HashMap<>(); 
		try {
			jornadaNueva = jornadasService.save(jornada);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al crear la jornada");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(HttpMessageNotReadableException e) {
			response.put("mensaje", "Error al crear la jornada");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La jornada ha sido creada con éxito!");
		response.put("jornada", jornadaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	@PutMapping("/jornadas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Jornada jornada, @PathVariable Long id) {
		Jornada jornadaActual = jornadasService.findById(id); 
		Jornada jornadaActualizada = null; 
		Map<String, Object> response = new HashMap<>(); 
		
		if(jornadaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el ID de la jornada no existe en la base de datos"); 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		try {
			jornadaActual.setDirViento(jornada.getDirViento()); 
			jornadaActual.setDuracion(jornada.getDuracion()); 
			jornadaActual.setFaseLuna(jornada.getFaseLuna()); 
			jornadaActual.setFechaSalida(jornada.getFechaSalida()); 
			jornadaActual.setFuerzaViento(jornada.getFuerzaViento()); 
			jornadaActual.setModalidad(jornada.getModalidad()); 
			jornadaActual.setObservaciones(jornada.getObservaciones()); 
			jornadaActual.setResultado(jornada.getResultado());
		
			jornadaActualizada = jornadasService.save(jornadaActual); 
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al crear la jornada");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La jornada ha sido creada con éxito!");
		response.put("jornada", jornadaActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
				
	}
	
	@DeleteMapping("/jornadas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>(); 
		try {
			jornadasService.delete(id); 
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al lanzar la consulta a la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La jornada ha sido eliminada con éxito"); 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); 
	}
}
