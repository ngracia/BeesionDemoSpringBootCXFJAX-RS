package com.beesion.model;

import java.io.Serializable;
import java.util.Set;

import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.beesion.servicios.ServiciosBeesion;
import com.beesion.servicios.ServiciosBeesionImpl;

public class ClassesModel {
	
	private Set<Class<? extends Serializable>> classesSerializable;
	private Set<Class<? extends ServiciosBeesionImpl>> classesServiciosBeesion ;
	private Set<Class<? extends SpringBootServletInitializer>> classesSpringBootServletInitializer;
	private Set<Class<? extends ClassesModel>> classesModel;

	
	public Set<Class<? extends ClassesModel>> getClassesModel() {
		return classesModel;
	}

	public void setClassesModel(Set<Class<? extends ClassesModel>> classesModel) {
		this.classesModel = classesModel;
	}

	public Set<Class<? extends Serializable>> getClassesSerializable() {
		return classesSerializable;
	}
	
	public void setClassesSerializable(Set<Class<? extends Serializable>> classesSerializable) {
		this.classesSerializable = classesSerializable;
	}
	
	public Set<Class<? extends ServiciosBeesionImpl>> getClassesServiciosBeesion() {
		return classesServiciosBeesion;
	}
	
	public void setClassesServiciosBeesion(Set<Class<? extends ServiciosBeesionImpl>> classesServiciosBeesion) {
		this.classesServiciosBeesion = classesServiciosBeesion;
	}
	
	public Set<Class<? extends SpringBootServletInitializer>> getClassesSpringBootServletInitializer() {
		return classesSpringBootServletInitializer;
	}
	
	public void setClassesSpringBootServletInitializer(
			Set<Class<? extends SpringBootServletInitializer>> classesSpringBootServletInitializer) {
		this.classesSpringBootServletInitializer = classesSpringBootServletInitializer;
	}
	
}
