package es.bsc.inb.limtox.services;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.bsc.inb.limtox.daos.DocumentDao;
import es.bsc.inb.limtox.daos.DocumentSourceRepository;
import es.bsc.inb.limtox.daos.EntityInstanceRepository;
import es.bsc.inb.limtox.daos.EntityTypeRepository;
import es.bsc.inb.limtox.model.Document;
import es.bsc.inb.limtox.model.DocumentSource;
import es.bsc.inb.limtox.model.EntityAssociationSentence;
import es.bsc.inb.limtox.model.EntityInstance;
import es.bsc.inb.limtox.model.EntityInstanceFound;
import es.bsc.inb.limtox.model.EntityType;
import es.bsc.inb.limtox.model.RelevantDocumentTopicInformation;
import es.bsc.inb.limtox.model.RelevantSectionTopicInformation;
import es.bsc.inb.limtox.model.RelevantSentenceTopicInformation;
import es.bsc.inb.limtox.model.Section;
import es.bsc.inb.limtox.model.Sentence;

@Service
public class DataBasePopulationServiceImpl implements DataBasePopulationService{
	
	static final Logger dataBasePopulationLog = Logger.getLogger("dataBasePopulationLog");
	
	@Autowired
	private DocumentDao documentRepository;

	@Autowired
	private DocumentSourceRepository documentSourceRepository;
	
	@Autowired
	private EntityInstanceRepository entityInstanceRepository;
	
	@Autowired
	private EntityTypeRepository entityTypeRepository;
	
	
	public void execute(File documentFile) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Document document = objectMapper.readValue(documentFile, Document.class);
			dataBasePopulationLog.info("Processig document " + documentFile);
			if(document.getDocumentSource()!=null) {
				DocumentSource documentSource = documentSourceRepository.findByName(document.getDocumentSource().getName());
				if(documentSource==null) {
					documentSourceRepository.save(document.getDocumentSource());
				}else {
					document.setDocumentSource(documentSource);
				}
			}
			for (RelevantDocumentTopicInformation relevantTopicInformation : document.getRelevantTopicsInformation()) {
				relevantTopicInformation.setDocument(document);
			}
			
			for (Section section : document.getSections()) {
				for (RelevantSectionTopicInformation relevantTopicInformation : section.getRelevantTopicsInformation()) {
					relevantTopicInformation.setSection(section);
				}
				for (EntityInstanceFound entitiesInstanceFound : section.getEntitiesInstanceFound()) {
					EntityInstance entityInstance = entityInstanceRepository.findByValue(entitiesInstanceFound.getEntityInstance().getValue());
					if(entityInstance==null) {
						entityInstanceRepository.save(entitiesInstanceFound.getEntityInstance());
					}else {
						entitiesInstanceFound.setEntityInstance(entityInstance);
					}
					entitiesInstanceFound.setSection(section);
				}
				
				for (Sentence sentence : section.getSentences()) {
					for (RelevantSentenceTopicInformation relevantTopicInformation : sentence.getRelevantTopicsInformation()) {
						relevantTopicInformation.setSentence(sentence);
					}
					for (EntityInstanceFound entitiesInstanceFound : sentence.getEntitiesInstanceFound()) {
						EntityInstance entityInstance = entityInstanceRepository.findByValue(entitiesInstanceFound.getEntityInstance().getValue());
						if(entityInstance==null) {
							EntityType entityType = entityTypeRepository.findByName(entitiesInstanceFound.getEntityInstance().getEntityTypeName());
							if(entityType!=null) {
								entitiesInstanceFound.getEntityInstance().setEntityType(entityType);
								entityInstanceRepository.save(entitiesInstanceFound.getEntityInstance());
							}else {
								//error la entity type debe existir
							}
							
						}else {
							entitiesInstanceFound.setEntityInstance(entityInstance);
						}
						entitiesInstanceFound.setSentence(sentence);
					}
					
					for (EntityAssociationSentence entityAssociationSentence : sentence.getEntitiesAssociationsInstanceFound()) {
						entityAssociationSentence.setSentence(sentence);
					}
					
					sentence.setSection(section);
				}
				section.setDocument(document);
			}
			
			/*for (Sentence sentence : document.getSentences()) {
				for (ChemicalCompoundSentence chemicalCompoundSentence : sentence.getChemicalCompoundSentences()) {
					chemicalCompoundSentence.setSentence(sentence);
					ChemicalCompound chemicalCompound = chemicalCompoundDao.findByName(chemicalCompoundSentence.getChemicalCompound().getName());
					if(chemicalCompound==null) {
						chemicalCompound = chemicalCompoundDao.save(chemicalCompoundSentence.getChemicalCompound());
					}
					chemicalCompoundSentence.setChemicalCompound(chemicalCompound);
					chemicalCompoundSentence.setSentence(sentence);
					if(chemicalCompoundSentence.getOcurrences()!=null) {
						for (Ocurrence ocurrence : chemicalCompoundSentence.getOcurrences()) {
							ocurrence.setChemicalCompoundSentence(chemicalCompoundSentence);
						}
					}
				}*/
//				for (HepatotoxicityTermSentence hepatotoxicitySentence : sentence.getHepatotoxicityTermSentences()) {
//					HepatotoxicityTerm hepatotoxicityTerm = hepatotoxicityTermDao.findByTerm(hepatotoxicitySentence.getHepatotoxicityTerm().getOriginal_entry());
//					if(hepatotoxicityTerm!=null) {
//						hepatotoxicitySentence.setHepatotoxicityTerm(hepatotoxicityTerm);
//						hepatotoxicitySentence.setSentence(sentence);
//					}
//				}
//				for (MarkerSentence markerSentence : sentence.getMarkerSentences()) {
//					markerSentence.setSentence(sentence);
//					Marker marker = markerDao.findByIdentifier(markerSentence.getMarker().getMarker_identifier());
//					if(marker!=null) {
//						markerSentence.setMarker(marker);
//						markerSentence.setSentence(sentence);
//					}
//				}
//				for (CytochromeSentence cytochromeSentence : sentence.getCytochromeSentences()) {
//					markerSentence.setSentence(sentence);
//					Marker marker = markerDao.findByIdentifier(cytochromeSentence.getMarker().getMarker_identifier());
//					if(marker!=null) {
//						markerSentence.setMarker(marker);
//						markerSentence.setSentence(sentence);
//					}
//				}
//				for (ChemicalCompoundCytochromeSentence chemicalCompoundCytochromeSentence : sentence.getChemicalCompoundCytochromeSentences()) {
//					chemicalCompoundCytochromeSentence.setSentence(sentence);
//				}
//				for (HepatotoxicityTermChemicalCompoundSentence hepatotoxicityTermChemicalCompoundSentence : sentence.getHepatotoxicityTermChemicalCompoundSentences()) {
//					hepatotoxicityTermChemicalCompoundSentence.setSentence(sentence);
//				}
//				for (MarkerChemicalCompoundSentence markerChemicalCompoundSentence : sentence.getMarkerChemicalCompoundSentences()) {
//					markerChemicalCompoundSentence.setSentence(sentence);
//				}
//				Section section = sectionDao.findByName(sentence.getSection().getName());
//				if(section==null) {
//					section = sectionDao.create(section);
//				}
				//sentence.setSection(section);
			/*	sentence.setDocument(document);
			}*/
			documentRepository.save(document);
		}catch(Exception e) {
			dataBasePopulationLog.error("Error inserting into database document : " + documentFile, e);
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	protected String readJsonFile(String file_path) throws IOException {
		try{
			if(Files.isRegularFile(Paths.get(file_path))) {
				FileReader fr = new FileReader(file_path);
			    BufferedReader br = new BufferedReader(fr);
			
			    StringBuilder textBuilder = new StringBuilder();
			    String line;
			    while ((line=br.readLine())!=null) {
			    	textBuilder.append(line);
			    }
			    br.close();
			    fr.close();
			    return textBuilder.toString();
			}  else {
				
			}
	    }catch(IOException ex){
	       ex.printStackTrace();   
	    }
		return null;
	}
	
	public HashMap<String, EntityType> loadEntityTypes(File inputEntityStructureFilePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			//this.entitiesTypes =  objectMapper.readValue(inputEntityStructureFilePath, new TypeReference<Map<String, EntityType>>(){});
			
			String json_string = readJsonFile(inputEntityStructureFilePath.getAbsolutePath());
			JsonNode rootNode = objectMapper.readTree(json_string);
			//JsonNode data = rootNode.path("hepatotoxicity");
			return objectMapper.readValue(rootNode.toString(), new TypeReference<Map<String, EntityType>>(){});
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void createEntityTypes(File inputEntityStructureFilePath) {
		HashMap<String, EntityType> entitiesTypes = this.loadEntityTypes(inputEntityStructureFilePath);
		for (EntityType entityType : entitiesTypes.values()) {
			/*if(entityTypeRepository.findByName(entityType.getName())==null) {
				entityTypeRepository.save(entityType);
			}*/
			
		}
	}
	
	
//	@Override
//	public void execute(File source_root_folder) {
//		File findinds_folder = new File(source_root_folder + File.separator + "findings");
//		Collection<File> files = FileUtils.listFiles(findinds_folder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
//		for (File file : files) {
//			try {
//				if (file.getName().endsWith(".json")) {
//					ObjectMapper objectMapper = new ObjectMapper();
//					PubMedDocument document = objectMapper.readValue(file, PubMedDocument.class);
//					for (Sentence sentence : document.getSentences()) {
//						
//						
//						for (ChemicalCompoundSentence chemicalCompoundSentence : sentence.getChemicalCompoundSentences()) {
//							chemicalCompoundSentence.setSentence(sentence);
//							ChemicalCompound chemicalCompound = chemicalCompoundDao.findByName(chemicalCompoundSentence.getChemicalCompound().getName());
//							if(chemicalCompound!=null) {
//								chemicalCompoundSentence.setChemicalCompound(chemicalCompound);
//								chemicalCompoundSentence.setSentence(sentence);
//							}
//						}
//						for (HepatotoxicityTermSentence hepatotoxicitySentence : sentence.getHepatotoxicityTermSentences()) {
//							HepatotoxicityTerm hepatotoxicityTerm = hepatotoxicityTermDao.findByTerm(hepatotoxicitySentence.getHepatotoxicityTerm().getTerm());
//							if(hepatotoxicityTerm!=null) {
//								hepatotoxicitySentence.setHepatotoxicityTerm(hepatotoxicityTerm);
//								hepatotoxicitySentence.setSentence(sentence);
//							}
//						}
//						for (MarkerSentence markerSentence : sentence.getMarkerSentences()) {
//							markerSentence.setSentence(sentence);
//							Marker marker = markerDao.findByIdentifier(markerSentence.getMarker().getMarker_identifier());
//							if(marker!=null) {
//								markerSentence.setMarker(marker);
//								markerSentence.setSentence(sentence);
//							}
//						}
////						for (CytochromeSentence cytochromeSentence : sentence.getCytochromeSentences()) {
////							markerSentence.setSentence(sentence);
////							Marker marker = markerDao.findByIdentifier(cytochromeSentence.getMarker().getMarker_identifier());
////							if(marker!=null) {
////								markerSentence.setMarker(marker);
////								markerSentence.setSentence(sentence);
////							}
////						}
////						for (ChemicalCompoundCytochromeSentence chemicalCompoundCytochromeSentence : sentence.getChemicalCompoundCytochromeSentences()) {
////							chemicalCompoundCytochromeSentence.setSentence(sentence);
////						}
////						for (HepatotoxicityTermChemicalCompoundSentence hepatotoxicityTermChemicalCompoundSentence : sentence.getHepatotoxicityTermChemicalCompoundSentences()) {
////							hepatotoxicityTermChemicalCompoundSentence.setSentence(sentence);
////						}
////						for (MarkerChemicalCompoundSentence markerChemicalCompoundSentence : sentence.getMarkerChemicalCompoundSentences()) {
////							markerChemicalCompoundSentence.setSentence(sentence);
////						}
//						Section section = sectionDao.findByName(sentence.getSection().getName());
//						if(section==null) {
//							section = sectionDao.create(section);
//						}
//						sentence.setSection(section);
//						sentence.setDocument(document);
//					}
//					documentDao.save(document);
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}


}
