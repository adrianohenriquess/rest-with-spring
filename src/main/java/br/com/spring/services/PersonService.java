package br.com.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.converter.DozerConverter;
import br.com.spring.data.model.Person;
import br.com.spring.data.vo.PersonVO;
import br.com.spring.exception.ResourceNotFoundException;
import br.com.spring.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		 Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não encontramos nenhum objeto com esse Id"));
		 return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Não encontramos nenhum objeto com esse Id"));
		entity.setAdress(person.getAdress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não encontramos nenhum objeto com esse Id"));
		repository.delete(entity);
	}
}
