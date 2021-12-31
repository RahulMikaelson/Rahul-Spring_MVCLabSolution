package com.greatlearning.debate_event.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.debate_event.entities.Students_Info;

@Service
public interface Services {
	
	public List<Students_Info> findAll();

	// Find student record by using its id
	public Students_Info findById(int id);

	// Insert and update method
	public void save(Students_Info student);

	// Delete method
	public void deleteById(int id);


}
