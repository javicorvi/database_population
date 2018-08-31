package es.bsc.inb.limtox.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;



@PropertySource({ "classpath:database_population.properties" })
@Service
public class MainServiceImpl {

	static final Logger dataBasePopulationLog = Logger.getLogger("dataBasePopulationLog");
	
	@Autowired
	DataBasePopulationService dataBasePopulationService;
	
	@Autowired
	ElasticSearchService elasticSearchService;
	
	
	public void execute(String propertiesParametersPath) {
		
		dataBasePopulationLog.info("Tagging articles with properties :  " +  propertiesParametersPath);
		Properties propertiesParameters = this.loadPropertiesParameters(propertiesParametersPath);
		dataBasePopulationLog.info("Input directory with the articles to tag : " + propertiesParameters.getProperty("inputDirectory"));
		
		String inputDirectoryPath = propertiesParameters.getProperty("inputDirectory");
		File inputDirectory = new File(inputDirectoryPath);
		
		if(!inputDirectory.exists()) {
	    	return ;
	    }
		
		//for differents sources that are in the folder
		for (File  file : inputDirectory.listFiles()) { 
			try {
				//if(file.isDirectory()) {
				String directory_name = file.getName();
				//dataBasePopulationService.execute(file);
				dataBasePopulationService.execute(file);
				//}
			}catch(Exception e) {
				dataBasePopulationLog.error("Error inserting into database document : " + file);
			}
		}
	}
	
//	public void indexArticles() {
//		File root = new File(env.getProperty("limtox.input.folder"));
//		File findinds_folder = new File(root + File.separator + "findings");
//		//Collection<File> files = FileUtils.listFiles(findinds_folder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
//		/*for (File file : files) {
//			try {
//				if (file.getName().endsWith(".json")) {
//					ObjectMapper objectMapper = new ObjectMapper();
//					PubMedDocument document = objectMapper.readValue(file, PubMedDocument.class);*/
//					//dataBasePopulationService.execute(file);
//					//elasticSearchService.index(document);
//					elasticSearchService.index(null);
//			/*	}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}*/
//	}
	
	/**
	  * Load Properties
	  * @param properitesParametersPath
	  */
	 public Properties loadPropertiesParameters(String properitesParametersPath) {
		 Properties prop = new Properties();
		 InputStream input = null;
		 try {
			 input = new FileInputStream(properitesParametersPath);
			 // load a properties file
			 prop.load(input);
			 return prop;
		 } catch (IOException ex) {
			 ex.printStackTrace();
		 } finally {
			 if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
		}
		return null;
	 }
	
}
