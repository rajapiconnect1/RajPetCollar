package io.swagger.api;

import io.swagger.model.ArrayofPetLocations;
import io.swagger.model.PetLocation;

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
public class PetLocationHistoryApiController implements PetLocationHistoryApi {

	private static final Logger log = LoggerFactory.getLogger(PetLocationHistoryApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public PetLocationHistoryApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ArrayofPetLocations> getLocationHistoryByPetId(
			@ApiParam(value = "pass pet id for searching the location of Pets. ", required = true) @PathVariable("petId") String petId) {

		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {

				/*ArrayofPetLocations petlocations = new ArrayofPetLocations();

				PetLocation petLocation = new PetLocation();
				petLocation.setPetId("Pet123");
				petLocation.setLangitude("123.2345");
				petLocation.setLattitude("12.23455");
				petLocation.setTimestamp(OffsetDateTime.now());

				PetLocation petLocation1 = new PetLocation();
				petLocation1.setPetId("Pet564");
				petLocation1.setLangitude("5343.2345");
				petLocation1.setLattitude("343.23455");
				petLocation1.setTimestamp(OffsetDateTime.now());

				petlocations.add(petLocation);
				petlocations.add(petLocation1);*/
				
				SimpleDB simpleDB = SimpleDB.getInstance();
				ArrayofPetLocations petlocations  = simpleDB.getPetLocationHistory(petId);

				return new ResponseEntity<ArrayofPetLocations>(petlocations, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ArrayofPetLocations>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ArrayofPetLocations>(HttpStatus.NOT_IMPLEMENTED);

	}

}
