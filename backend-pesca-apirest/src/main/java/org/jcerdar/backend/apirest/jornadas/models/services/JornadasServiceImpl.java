package org.jcerdar.backend.apirest.jornadas.models.services;

import java.util.List;

import org.jcerdar.backend.apirest.jornadas.models.dao.JornadasDao;
import org.jcerdar.backend.apirest.jornadas.models.entity.Jornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JornadasServiceImpl implements JornadasService {

	@Autowired
	private JornadasDao jornadasDao; 
	
	@Override
	@Transactional(readOnly = true)
	public List<Jornada> findAll() {
		return (List<Jornada>)jornadasDao.findAll(); 
	}

	@Override
	@Transactional(readOnly = true)
	public Jornada findById(Long id) {
		return jornadasDao.findById(id).orElse(null); 
	}

	@Override
	public Jornada save(Jornada jornada) {
		return jornadasDao.save(jornada); 
	}

	@Override
	public void delete(Long id) {
		jornadasDao.deleteById(id);
	}

}
