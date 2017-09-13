package com.beesion.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ListElements")
@XmlType(name = "", propOrder = {"list"})
public class ResponseSort implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = true, name="")
	private List<Integer> list;
	
	public List<Integer> getList(List<Integer> elements) {
		return elements;
	}
	
	public void setList(List<Integer> elements) {
		this.list = elements;
	}
}
