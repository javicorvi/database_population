package es.bsc.inb.limtox.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
		try {
			dataBasePopulationLog.info("Documents Database Population with properties :  " +  propertiesParametersPath);
			Properties propertiesParameters = this.loadPropertiesParameters(propertiesParametersPath);
			dataBasePopulationLog.info("Input directory with the articles to tag : " + propertiesParameters.getProperty("inputDirectory"));
			dataBasePopulationLog.info("Entity Types structure path : " + propertiesParameters.getProperty("inputEntityStructureFilePath"));
			dataBasePopulationLog.info("Output directory : " + propertiesParameters.getProperty("outputDirectory"));
			
			String inputDirectoryPath = propertiesParameters.getProperty("inputDirectory");
			String inputEntityStructureFilePath = propertiesParameters.getProperty("inputEntityStructureFilePath");
			String outputDirectoryPath = propertiesParameters.getProperty("outputDirectory");
			
			File inputDirectory = new File(inputDirectoryPath);
			if(!inputDirectory.exists()) {
		    	return ;
		    }
			
			File outputDirectory = new File(outputDirectoryPath);
		    if(!outputDirectory.exists())
		    	outputDirectory.mkdirs();
			
		    
		    File inputEntityStructureFile = new File(inputEntityStructureFilePath);
			if(!inputEntityStructureFile.exists()) {
		    	return ;
		    }
		    dataBasePopulationService.createEntityTypes(inputEntityStructureFile);
		    
		    
			List<String> filesProcessed = readFilesProcessed(outputDirectoryPath); 
		    BufferedWriter filesPrecessedWriter = new BufferedWriter(new FileWriter(outputDirectoryPath + File.separator + "list_files_processed.dat", true));
		    File[] files =  inputDirectory.listFiles();
			for (File folders_files : files) {
				dataBasePopulationLog.info("Processing folder : " + folders_files.getPath());
				if(folders_files!=null && folders_files.isDirectory()) {
					for (File file_to_populate : folders_files.listFiles()) {
						if(file_to_populate.getName().endsWith(".json") && filesProcessed!=null && !filesProcessed.contains(folders_files.getName()+"/"+file_to_populate.getName())){
							try {
								dataBasePopulationLog.info("Processing document : " + folders_files.getName()+"/"+file_to_populate.getName());
								
								
								dataBasePopulationService.execute(file_to_populate);
								
								
								filesPrecessedWriter.write(folders_files.getName()+"/"+file_to_populate.getName()+"\n");
								filesPrecessedWriter.flush();
							}catch(Exception e) {
								dataBasePopulationLog.error("Error inserting into database document : " + file_to_populate);
							}
						}
					}
				}else {
					
				}
			}
			filesPrecessedWriter.close();
		}  catch (Exception e) {
			dataBasePopulationLog.error("Generic error in the database population step",e);
		} 
	}
	
	
	private List<String> readFilesProcessed(String outputDirectoryPath) {
		try {
			List<String> files_processed = new ArrayList<String>();
			if(Files.isRegularFile(Paths.get(outputDirectoryPath + File.separator + "list_files_processed.dat"))) {
				FileReader fr = new FileReader(outputDirectoryPath + File.separator + "list_files_processed.dat");
			    BufferedReader br = new BufferedReader(fr);
			    
			    String sCurrentLine;
			    while ((sCurrentLine = br.readLine()) != null) {
			    	files_processed.add(sCurrentLine);
				}
			    br.close();
			    fr.close();
			}
			return files_processed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
