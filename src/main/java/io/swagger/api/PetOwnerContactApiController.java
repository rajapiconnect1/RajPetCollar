package io.swagger.api;

import io.swagger.model.ArrayofOwnerContact;
import io.swagger.model.ArrayofPetLocations;
import io.swagger.model.PetLocation;
import io.swagger.model.PetOwner;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.db.SimpleDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.threeten.bp.OffsetDateTime;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-10-17T14:10:41.837Z[GMT]")
@Controller
public class PetOwnerContactApiController implements PetOwnerContactApi {

	private static final Logger log = LoggerFactory.getLogger(PetOwnerContactApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public PetOwnerContactApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ArrayofOwnerContact> getLocationHistoryByPetNameOwnerName(
			@ApiParam(value = "pass pet name for searching the location of Pets. ", required = true) @PathVariable("petName") String petName) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				
				
			/*	ArrayofOwnerContact petOwners = new ArrayofOwnerContact();
				
            	PetOwner petOwner = new PetOwner();
			
            	petOwner.setOwnerId("OWNER-1234");
            	petOwner.setOwnerName("James");
            	petOwner.setStreet("Lyman Street");
            	petOwner.setCity("Northborough");
            	petOwner.setState("MA");
            	petOwner.setZip("01752");
            	
            	PetOwner petOwner1 = new PetOwner();
    			
            	petOwner1.setOwnerId("OWNER-1234");
            	petOwner1.setOwnerName("James");
            	petOwner1.setStreet("Lyman Street");
            	petOwner1.setCity("Northborough");
            	petOwner1.setState("MA");
            	petOwner1.setZip("01752");
			
            	
				
				
            	petOwners.add(petOwner);
            	petOwners.add(petOwner1);*/
            	
            	SimpleDB simpleDB = SimpleDB.getInstance();
            	ArrayofOwnerContact petOwners  = simpleDB.getPetOwnerContact(petName);

				
				return new ResponseEntity<ArrayofOwnerContact>(petOwners,HttpStatus.ACCEPTED);
				
				
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ArrayofOwnerContact>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ArrayofOwnerContact>(HttpStatus.NOT_IMPLEMENTED);
	}

}
