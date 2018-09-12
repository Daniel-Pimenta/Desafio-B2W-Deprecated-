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
@JsonPropertyOrder({ "count", "next", "previous", "results" })
public class Planeta {

	@JsonProperty("count")
	private Integer count;
	@JsonProperty("next")
	private Object next;
	@JsonProperty("previous")
	private Object previous;
	@JsonProperty("results")
	private List<Result> results = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("count")
	public Integer getCount() {
		return count;
	}

	@JsonProperty("count")
	public void setCount(Integer count) {
		this.count = count;
	}

	@JsonProperty("next")
	public Object getNext() {
		return next;
	}

	@JsonProperty("next")
	public void setNext(Object next) {
		this.next = next;
	}

	@JsonProperty("previous")
	public Object getPrevious() {
		return previous;
	}

	@JsonProperty("previous")
	public void setPrevious(Object previous) {
		this.previous = previous;
	}

	@JsonProperty("results")
	public List<Result> getResults() {
		return results;
	}

	@JsonProperty("results")
	public void setResults(List<Result> results) {
		this.results = results;
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