package br.com.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.data.vo.PersonVO;
import br.com.spring.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping
	public List<PersonVO> findAll() {
		return service.findAll();
	}

	@RequestMapping("/{id}")
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return service.findById(id);
	}

	@PostMapping
	public PersonVO create(@RequestBody PersonVO PersonVO) throws Exception {
		return service.create(PersonVO);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO PersonVO) throws Exception {
		return service.update(PersonVO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
