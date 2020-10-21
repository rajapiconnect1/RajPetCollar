/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.21).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ArrayofPetLocations;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-10-21T01:40:20.612Z[GMT]")
@Api(value = "petLocationHistory", description = "the petLocationHistory API")
public interface PetLocationHistoryApi {

    @ApiOperation(value = "Returns history of Pet Locations.", nickname = "getLocationHistoryByPetId", notes = "Returns history of Pet Locations.", response = ArrayofPetLocations.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = ArrayofPetLocations.class),
        @ApiResponse(code = 400, message = "Pet Location History not found") })
    @RequestMapping(value = "/petLocationHistory/{petId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ArrayofPetLocations> getLocationHistoryByPetId(@ApiParam(value = "pass pet id for searching the location of Pets. ",required=true) @PathVariable("petId") String petId
);

}

