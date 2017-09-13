package com.beesion.dto;

import java.io.Serializable;

public class ResponseGet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
