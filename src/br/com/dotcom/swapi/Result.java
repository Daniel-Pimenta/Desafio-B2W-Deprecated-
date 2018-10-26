package br.com.dotcom.swapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
	{ 
		"name", "rotation_period", "orbital_period", "diameter", "climate", "gravity", "terrain",
		"surface_water", "population", "residents", "films", "created", "edited", "url" 
	}
)
public class Result {

	@JsonProperty("name")
	private String name;
	@JsonProperty("rotation_period")
	private String rotationPeriod;
	@JsonProperty("orbital_period")
	private String orbitalPeriod;
	@JsonProperty("diameter")
	private String diameter;
	@JsonProperty("climate")
	private String climate;
	@JsonProperty("gravity")
	private String gravity;
	@JsonProperty("terrain")
	private String terrain;
	@JsonProperty("surface_water")
	private String surfaceWater;
	@JsonProperty("population")
	private String population;
	@JsonProperty("residents")
	private List<String> residents = null;
	@JsonProperty("films")
	private List<String> films = null;
	@JsonProperty("created")
	private String created;
	@JsonProperty("edited")
	private String edited;
	@JsonProperty("url")
	private String url;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("rotation_period")
	public String getRotationPeriod() {
		return rotationPeriod;
	}

	@JsonProperty("rotation_period")
	public void setRotationPeriod(String rotationPeriod) {
		this.rotationPeriod = rotationPeriod;
	}

	@JsonProperty("orbital_period")
	public String getOrbitalPeriod() {
		return orbitalPeriod;
	}

	@JsonProperty("orbital_period")
	public void setOrbitalPeriod(String orbitalPeriod) {
		this.orbitalPeriod = orbitalPeriod;
	}

	@JsonProperty("diameter")
	public String getDiameter() {
		return diameter;
	}

	@JsonProperty("diameter")
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	@JsonProperty("climate")
	public String getClimate() {
		return climate;
	}

	@JsonProperty("climate")
	public void setClimate(String climate) {
		this.climate = climate;
	}

	@JsonProperty("gravity")
	public String getGravity() {
		return gravity;
	}

	@JsonProperty("gravity")
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	@JsonProperty("terrain")
	public String getTerrain() {
		return terrain;
	}

	@JsonProperty("terrain")
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	@JsonProperty("surface_water")
	public String getSurfaceWater() {
		return surfaceWater;
	}

	@JsonProperty("surface_water")
	public void setSurfaceWater(String surfaceWater) {
		this.surfaceWater = surfaceWater;
	}

	@JsonProperty("population")
	public String getPopulation() {
		return population;
	}

	@JsonProperty("population")
	public void setPopulation(String population) {
		this.population = population;
	}

	@JsonProperty("residents")
	public List<String> getResidents() {
		return residents;
	}

	@JsonProperty("residents")
	public void setResidents(List<String> residents) {
		this.residents = residents;
	}

	@JsonProperty("films")
	public List<String> getFilms() {
		return films;
	}

	@JsonProperty("films")
	public void setFilms(List<String> films) {
		this.films = films;
	}

	@JsonProperty("created")
	public String getCreated() {
		return created;
	}

	@JsonProperty("created")
	public void setCreated(String created) {
		this.created = created;
	}

	@JsonProperty("edited")
	public String getEdited() {
		return edited;
	}

	@JsonProperty("edited")
	public void setEdited(String edited) {
		this.edited = edited;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
