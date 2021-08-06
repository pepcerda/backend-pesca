package org.jcerdar.backend.apirest.jornadas.models.services;

import java.util.List;
import java.util.Optional;

import org.jcerdar.backend.apirest.jornadas.models.entity.Jornada;

public interface JornadasService {

	public List<Jornada> findAll(); 
	
	public Jornada findById(Long id); 
	
	public Jornada save(Jornada jornada); 
	
	public void delete(Long id); 
	
}
