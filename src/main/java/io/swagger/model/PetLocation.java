package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PetLocation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-10-17T14:10:41.837Z[GMT]")


public class PetLocation   {
  @JsonProperty("petId")
  private String petId = null;

  @JsonProperty("langitude")
  private String langitude = null;

  @JsonProperty("lattitude")
  private String lattitude = null;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  public PetLocation petId(String petId) {
    this.petId = petId;
    return this;
  }

  /**
   * Get petId
   * @return petId
  **/
  @ApiModelProperty(example = "pet-123", required = true, value = "")
      @NotNull

    public String getPetId() {
    return petId;
  }

  public void setPetId(String petId) {
    this.petId = petId;
  }

  public PetLocation langitude(String langitude) {
    this.langitude = langitude;
    return this;
  }

  /**
   * Get langitude
   * @return langitude
  **/
  @ApiModelProperty(example = "Widget Adapter", required = true, value = "")
      @NotNull

    public String getLangitude() {
    return langitude;
  }

  public void setLangitude(String langitude) {
    this.langitude = langitude;
  }

  public PetLocation lattitude(String lattitude) {
    this.lattitude = lattitude;
    return this;
  }

  /**
   * Get lattitude
   * @return lattitude
  **/
  @ApiModelProperty(example = "Widget Adapter", required = true, value = "")
      @NotNull

    public String getLattitude() {
    return lattitude;
  }

  public void setLattitude(String lattitude) {
    this.lattitude = lattitude;
  }

  public PetLocation timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  **/
  @ApiModelProperty(example = "2016-08-29T09:12:33.001Z", required = true, value = "")
      @NotNull

    @Valid
    public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PetLocation petLocation = (PetLocation) o;
    return Objects.equals(this.petId, petLocation.petId) &&
        Objects.equals(this.langitude, petLocation.langitude) &&
        Objects.equals(this.lattitude, petLocation.lattitude) &&
        Objects.equals(this.timestamp, petLocation.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(petId, langitude, lattitude, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PetLocation {\n");
    
    sb.append("    petId: ").append(toIndentedString(petId)).append("\n");
    sb.append("    langitude: ").append(toIndentedString(langitude)).append("\n");
    sb.append("    lattitude: ").append(toIndentedString(lattitude)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
