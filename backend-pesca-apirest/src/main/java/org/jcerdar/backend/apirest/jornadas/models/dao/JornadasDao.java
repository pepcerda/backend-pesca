package org.jcerdar.backend.apirest.jornadas.models.dao;

import org.jcerdar.backend.apirest.jornadas.models.entity.Jornada;
import org.springframework.data.repository.CrudRepository;

public interface JornadasDao extends CrudRepository<Jornada, Long>{

}
