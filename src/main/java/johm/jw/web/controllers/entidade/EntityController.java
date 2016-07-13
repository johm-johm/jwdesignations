/**
 * 
 */
package johm.jw.web.controllers.entidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import johm.jw.modelo.entidade.Entidade;
import johm.jw.repositorio.entidade.EntidadeRepositorio;
import johm.jw.web.dto.MessageDto;
import johm.jw.web.dto.MessageDto.MessageType;

/**
 * @author johm
 *
 */
@RestController
public class EntityController {
	
	private @Autowired EntidadeRepositorio entityRepository;
	
	@RequestMapping(value = "/entidades", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<Entidade> entities = (List<Entidade>) entityRepository.findAll();
		if (entities == null || entities.isEmpty()) {
			return new ResponseEntity<MessageDto>(new MessageDto(MessageType.WARN, "Entidades não encontrada"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Iterable<Entidade>>(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/entidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEntidade(@PathVariable("id") Long id) {
		if (id == null ) {
			return new ResponseEntity<MessageDto>(new MessageDto(MessageType.ERROR, "Entidade não encontrada"), HttpStatus.BAD_REQUEST);
		}
		Entidade entities = (Entidade) entityRepository.findOne(id);
		if (entities == null ) {
			return new ResponseEntity<MessageDto>(new MessageDto(MessageType.WARN, "Entidade não encontrada"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Entidade>(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/entidade", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Entidade entity) {
		if (entity == null) {
			return new ResponseEntity<MessageDto>(new MessageDto(MessageType.ERROR, "Entidade não informada"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Entidade>(entityRepository.save(entity), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/entidade/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (id == null ) {
			return new ResponseEntity<MessageDto>(new MessageDto(MessageType.ERROR, "Entidade não encontrada"), HttpStatus.BAD_REQUEST);
		}
		entityRepository.delete(id);
		return new ResponseEntity<MessageDto>(new MessageDto(MessageType.INFO, "Entidade não encontrada"), HttpStatus.OK);
	}

}
