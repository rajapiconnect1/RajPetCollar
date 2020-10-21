package io.swagger.api;

import io.swagger.model.ArrayofOwnerContact;
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
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.threeten.bp.OffsetDateTime;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-10-17T14:10:41.837Z[GMT]")
@Controller
public class StoreLocationsApiController implements StoreLocationsApi {

	private static final Logger log = LoggerFactory.getLogger(StoreLocationsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	SimpleDB db = SimpleDB.getInstance();
	
	

	@org.springframework.beans.factory.annotation.Autowired
	public StoreLocationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Void> storeLocationsPost(@ApiParam(value = "") @Valid @RequestBody PetLocation body) {
		
		//String accept = request.getHeader("Accept");

		try {

			System.out.println("body " + body.toString());
			File file = new File("PetLocations.txt");

			// creates the file
			file.createNewFile();

			// creates a FileWriter Object
			FileWriter writer = new FileWriter(file, true);

			// Writes the content to the file
			PetLocation petLocation = new PetLocation();
			petLocation.setPetId(body.getPetId());
			petLocation.setLangitude(body.getLangitude());
			petLocation.setLattitude(body.getLattitude());
			petLocation.setTimestamp(body.getTimestamp());
			
			db.addPetLocationRecord(petLocation);
			
			writer.write(body.getPetId());
			writer.flush();
			writer.close();
			
			db.displayRecord();

			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);

		} catch (Exception e) {
			 log.error(e.getMessage(), e);
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		
	}
	
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
	

}


