package es.bsc.inb.limtox.services;
import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.bsc.inb.limtox.daos.DocumentDao;
import es.bsc.inb.limtox.daos.DocumentSourceRepository;
import es.bsc.inb.limtox.daos.EntityInstanceRepository;
import es.bsc.inb.limtox.model.Document;
import es.bsc.inb.limtox.model.DocumentSource;
import es.bsc.inb.limtox.model.EntityInstance;
import es.bsc.inb.limtox.model.EntityInstanceFound;
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
