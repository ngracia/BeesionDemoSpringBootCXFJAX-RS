package com.beesion.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "RespuestaType", propOrder = {"descripcion"})
public class ResponseAdd implements Serializable
{

	private static final long serialVersionUID = 1L;

	@XmlElement(required = true)
	protected String descripcion;


	public String getDescripcion()
	{
		return descripcion;
	}


	public void setDescripcion(final String value)
	{
		this.descripcion = value;
	}


	@Override
	public String toString()
	{
		return "RespuestaType [descripcion=" + descripcion + "]";
	}
}
