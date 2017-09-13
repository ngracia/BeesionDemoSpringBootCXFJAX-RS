package com.beesion.servicios;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.reflections.Reflections;

import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.beesion.dto.ResponseSort;
import com.beesion.model.ClassesModel;
import com.beesion.dto.ResponseGet;
import com.beesion.dto.ResponseAdd;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiciosBeesionImpl implements ServiciosBeesion {

	private static final Log LOGGER = LogFactory.getLog(ServiciosBeesionImpl.class);
	private List<Map<String, String>> listMap = new ArrayList<>();
	private static final String  EMPTY_STRING = "";
	private static final String  COMMA = ", ";
	
	@Override
	public DataHandler jar() {
		
		LOGGER.info("Ejecutando el metodo jar()");
		DataHandler dataHandler = null;
		
		try {
			
			File directory = new File(createDirectory(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()));
			buildJson(buildClassesMap(getClassesFromPackage()));
			Manifest manifest = getManifest();
			
			
			JarOutputStream target = new JarOutputStream(new FileOutputStream("output.jar"), manifest);
			
			LOGGER.info("Ejecutando el metodo addFile()");
			addFiles(new File(directory.getPath()), target);
			target.close();
			
			LOGGER.info("Ejecutando el metodo delete()");
			delete(directory);
			
			  
		}catch(Exception ex) {
			LOGGER.error("El metodo jar() ha devuelto el siguiente error, " + ex.getMessage());
		}
		
		FileDataSource dataSource = new FileDataSource("output.jar");
		return new DataHandler(dataSource);
	}
	
	
	private ClassesModel getClassesFromPackage() {
		LOGGER.info("Ejecutando el metodo getClassesFromPackage()");
		ClassesModel classesModel = new ClassesModel();
		
		try {
			
			Reflections reflections = new Reflections("com.beesion");
			classesModel.setClassesSerializable(reflections.getSubTypesOf(Serializable.class));
			classesModel.setClassesServiciosBeesion(reflections.getSubTypesOf(ServiciosBeesionImpl.class));
			classesModel.setClassesSpringBootServletInitializer(reflections.getSubTypesOf(SpringBootServletInitializer.class));
			classesModel.setClassesModel(reflections.getSubTypesOf(ClassesModel.class));
		
		}catch(Exception ex) {
			LOGGER.error("El metodo getClassesFromPackage() ha devuelto el siguiente error, " + ex.getMessage());
		}
		
		return classesModel;
	}
	
	@SuppressWarnings("unchecked")
	private void buildJson(Map<String, String> classes) {
		
		LOGGER.info("Ejecutando el metodo buildJson()");
		
		try {
			
			JSONObject obj = new JSONObject();
			
			JSONArray classContent = new JSONArray();
			classes.forEach((x,y) -> {
				classContent.add("className:" + x);
				classContent.add("contenido:" + y);
			});
			
			obj.put("classes: ", classContent);
			
			
			try (FileWriter file = new FileWriter("BOOT-INF/classes.json")) {
				file.write(obj.toJSONString());
				LOGGER.info("Successfully Copied JSON Object to File...");
			}
	        
		}catch(Exception ex) {
			LOGGER.error("El metodo buildJson ha devuelto el siguiente error, " + ex.getMessage());
		}
		
	
	}
	
	private Map<String, String> buildClassesMap(ClassesModel classesModel){
		
		LOGGER.info("Ejecutando el metodo buildClassesMap()");
		Map<String, String> classes = new HashMap<>();
		
		try {
			
			classesModel.getClassesSerializable().forEach(p -> {
				String className = p.getSimpleName();
				String contenido = EMPTY_STRING;
				String comma = EMPTY_STRING;
				
				Method[] m = p.getDeclaredMethods();
				for (int i = 0; i < m.length; i++) {
					comma = contenido != EMPTY_STRING ? COMMA : EMPTY_STRING;
					contenido = contenido + comma + m[i].toString();
				}
				
				classes.put(className,contenido);
	          });
			
			
			classesModel.getClassesServiciosBeesion().forEach(p -> {
				String className = p.getSimpleName();
				String contenido = EMPTY_STRING;
				String comma = EMPTY_STRING;
				
				Method[] m = p.getDeclaredMethods();
				for (int i = 0; i < m.length; i++) {
					comma = contenido != EMPTY_STRING ? COMMA : EMPTY_STRING;
					contenido = contenido + comma + m[i].toString();
				}
				classes.put(className,contenido);
			});
			
			
			classesModel.getClassesSpringBootServletInitializer().forEach(p -> {
				String className = p.getSimpleName();
				String contenido = EMPTY_STRING;
				String comma = EMPTY_STRING;
				
				Method[] m = p.getDeclaredMethods();
				for (int i = 0; i < m.length; i++) {
					comma = contenido != EMPTY_STRING ? COMMA : EMPTY_STRING;
					contenido = contenido + comma + m[i].toString();
				}
				classes.put(className,contenido);
			});
			
			classesModel.getClassesModel().forEach(p -> {
				String className = p.getSimpleName();
				String contenido = EMPTY_STRING;
				String comma = EMPTY_STRING;
				
				Method[] m = p.getDeclaredMethods();
				for (int i = 0; i < m.length; i++) {
					comma = contenido != EMPTY_STRING ? COMMA : EMPTY_STRING;
					contenido = contenido + comma + m[i].toString();
				}
				classes.put(className,contenido);
			});
			
		}catch(Exception ex) {
			
			LOGGER.error("El metodo buildClassesMap() ha devuelto el siguiente error, " + ex.getMessage());
		}
		
		return classes;
		
	}
	
	private String createDirectory(String location) {
		
		LOGGER.info("Ejecutando el metodo createDirectory()");
		location = location.replace("\\", "/");
		File source;
		File bootInf= new File("BOOT-INF");
	   
		try{
	    	
	    	bootInf.mkdir();

	    	source = new File(location+"../");
	    	source = new File(source.getCanonicalPath());
	    	
	    	source = source.getName().equals("source") ? new File(location+"../") : new File(location+"../servicios/WEB-INF/");	    	
	    	source = new File(source.getCanonicalPath());
	    	
	    	FileUtils.copyDirectory(source, bootInf);
	    	
	    } 
	    catch(IOException ex){
	    	
	    	LOGGER.error("El metodo createDirectory() ha devuelto el siguiente error, " + ex.getMessage());
	    } 
	    
	    return bootInf.getPath();
	}
	
	private void delete(File path){
		
		try {
			if(path.exists()) {
				File[] l = path.listFiles();
			    for (File f : l){
			        if (f.isDirectory())
			            delete(f);
			        else
			            f.delete();
			    }
			    path.delete(); 
			}
		}catch(Exception ex) {
			
			LOGGER.error("El metodo createDirectory() ha devuelto el siguiente error, " + ex.getMessage());
		}
	}
	
	private Manifest getManifest() {
		
		LOGGER.info("Ejecutando el metodo getManifest()");
		Manifest manifest = new Manifest();
		try {
			manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
			manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_TITLE, this.getClass().getPackage().getImplementationTitle());
		}catch(Exception ex) {
			LOGGER.error("El metodo getManifest() ha devuelto el siguiente error, " + ex.getMessage());
		}
		return manifest;
	}

	@Override
	public ResponseSort sort(final String elements) {
		
		LOGGER.info("Ejecutando el metodo sort()");
		ResponseSort response = new ResponseSort();
		List<Integer> elementList = new ArrayList<>();
		
		try {
			
			String[] elementsArray = elements.split(",");
			
			Arrays.asList(elementsArray).forEach(p -> {
				elementList.add(Integer.valueOf(p));
			});
			
			elementList.sort((p1, p2) -> p1.compareTo(p2));
			response.setList(elementList);
			
		}catch(Exception ex) {
			
			LOGGER.error("El metodo sort() ha devuelto el siguiente error, " + ex.getMessage());
		}
		return response;
	}

	@Override
	public ResponseAdd add(final String value) {
		
		LOGGER.info("Ejecutando el metodo add()");
		ResponseAdd responseType = new ResponseAdd();
		
		try {
			
			String[] valuesArray = value.split(":");
			
			Map<String, String> map = new HashMap<>();
			map.put(valuesArray[0], valuesArray[1]);
			listMap.add(map);
			
			responseType.setDescripcion("Success");
			
		}catch(Exception ex) {
			
			LOGGER.error("El metodo add() ha devuelto el siguiente error, " + ex.getMessage());
			responseType.setDescripcion("Error");
		}
		
		return responseType;
	}

	@Override
	public ResponseGet get(final String value) {
		
		LOGGER.info("Ejecutando el metodo get()");
		ResponseGet result = new ResponseGet();
		
		try {
			
			listMap.forEach(p -> {
				if(p.containsKey(value)) {
					result.setValue(p.get(value));
				}
			});
			
		}catch(Exception ex) {
			
			LOGGER.error("El metodo get() ha devuelto el siguiente error, " + ex.getMessage());
		}
		
		return result;
	}
	
	
	private void addFiles(File source, JarOutputStream target) throws IOException
	{
		BufferedInputStream in = null;
		try
		{
			if (source.isDirectory())
		    {
				String name = source.getPath().replace("\\", "/");
				if (!name.isEmpty())
				{
					if (!name.endsWith("/"))
						name += "/";
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile: source.listFiles()) {
					addFiles(nestedFile, target);
				}
		    	  
		      return;
		    }
	
		    JarEntry entry = new JarEntry(source.getPath().replace("\\", "/"));
		    entry.setTime(source.lastModified());
		    target.putNextEntry(entry);
		    in = new BufferedInputStream(new FileInputStream(source));
	
		    byte[] buffer = new byte[1024];
		    while (true)
		    {
		      int count = in.read(buffer);
		      if (count == -1)
		        break;
		      target.write(buffer, 0, count);
		    }
		    target.closeEntry();
		  }catch(Exception ex) {
			  LOGGER.error("El metodo addFiles() ha devuelto el siguiente error, " + ex.getMessage());
		  }
		  finally
		  {
		    if (in != null)
		      in.close();
		  }
	}

}
