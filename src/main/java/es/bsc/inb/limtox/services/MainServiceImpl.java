package es.bsc.inb.limtox.services;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.bsc.inb.limtox.model.PubMedDocument;



@PropertySource({ "classpath:limtox.properties" })
@Service
public class MainServiceImpl {
	@Autowired
    private Environment env;
	
	@Autowired
	DataBasePopulationService dataBasePopulationService;
	
	@Autowired
	ElasticSearchService elasticSearchService;
	
	
	public void execute() {
		File root = new File(env.getProperty("limtox.input.folder"));
		//for differents sources that are in the folder
		for (File  file : root.listFiles()) { 
			try {
				if(file.isDirectory()) {
					String directory_name = file.getName();
					//dataBasePopulationService.execute(file);
					dataBasePopulationService.execute(root);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void indexArticles() {
		File root = new File(env.getProperty("limtox.input.folder"));
		File findinds_folder = new File(root + File.separator + "findings");
		Collection<File> files = FileUtils.listFiles(findinds_folder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			try {
				if (file.getName().endsWith(".json")) {
					ObjectMapper objectMapper = new ObjectMapper();
					PubMedDocument document = objectMapper.readValue(file, PubMedDocument.class);
					//dataBasePopulationService.execute(file);
					elasticSearchService.index(document);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
