package com.classroom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classroom.entity.Contact;
import com.classroom.repository.ContactRepository;
import com.classroom.services.dto.ContactDTO;

@Service
public class ContactService {
	@Autowired
	ContactRepository repo;
	
	public ContactDTO saved(Contact contact) {
		/*
	 	if(contact.getTelephone().length() != 14) {
			throw new Validation("Telephone Invalid");
		}
		
		if(!contact.getEmail().contains("@")) {
			throw new Validation("Email Invalid");
		}
		*/
		
		Contact ct = repo.save(contact);
		ContactDTO contactDTO = new ContactDTO(ct);

		return contactDTO;
	}
	
	public List<ContactDTO> consultContact() {
		List<Contact> contact = repo.findAll();
		List<ContactDTO> contactsDTO = new ArrayList<ContactDTO>();
		
		for(Contact ct: contact) {
			contactsDTO.add(new ContactDTO(ct));
		}
		
		return contactsDTO;
	}
	
	public ContactDTO consultContactById(Long idcontact) {
		Optional<Contact> opcontact = repo.findById(idcontact);
		Contact ct = opcontact.orElseThrow(
				() -> new EntityNotFoundException("Contact not found"));
		return new ContactDTO(ct);
	}
	
	public Contact consultContacts(Long idcontact) {
		Optional<Contact> opcontact = repo.findById(idcontact);
		Contact ct = opcontact.orElseThrow(
				() -> new EntityNotFoundException("Contact not found"));
		return ct;
	}
	
	public void excludeContact(Long idcontact) {
		//Contact ct = consultContacts(idcontact);
		repo.deleteById(idcontact);
	}
	
	public ContactDTO chageContact(Long idcontact, Contact contact) {
		Contact ct = consultContacts(idcontact);
		BeanUtils.copyProperties(contact, ct, "id");
		/*
		ct.setEmail(contact.getEmail());
		ct.setName(contact.getName());
		ct.setTelephone(contact.getTelephone());
		*/
		return new ContactDTO (repo.save(ct));
	}
	
	public List<ContactDTO> consultContactByEmail(String email){
		return ContactDTO.convertToDTO(repo.findByEmail(email));
	}
}
