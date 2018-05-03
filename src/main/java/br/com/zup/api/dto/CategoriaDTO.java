package br.com.zup.api.dto;

import org.springframework.hateoas.ResourceSupport;

public class CategoriaDTO extends ResourceSupport{

	private Long code;
	private String description;

	public Long getCode() {
		return code;
	}
	public void setCode(final Long code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
}
