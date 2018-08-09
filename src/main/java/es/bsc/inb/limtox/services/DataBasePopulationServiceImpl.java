package es.bsc.inb.limtox.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.bsc.inb.limtox.daos.ChemicalCompoundDao;
import es.bsc.inb.limtox.daos.CytochromeDao;
import es.bsc.inb.limtox.daos.DocumentDao;
import es.bsc.inb.limtox.daos.HepatotoxicityTermDao;
import es.bsc.inb.limtox.daos.MarkerDao;
import es.bsc.inb.limtox.daos.SectionDao;
import es.bsc.inb.limtox.model.ChemicalCompound;
import es.bsc.inb.limtox.model.ChemicalCompoundSentence;
import es.bsc.inb.limtox.model.Cytochrome;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
import es.bsc.inb.limtox.model.HepatotoxicityTermSentence;
import es.bsc.inb.limtox.model.Marker;
import es.bsc.inb.limtox.model.MarkerSentence;
import es.bsc.inb.limtox.model.PubMedDocument;
import es.bsc.inb.limtox.model.Section;
import es.bsc.inb.limtox.model.Sentence;
import es.bsc.inb.limtox.util.GzipUtil;
@PropertySource({ "classpath:limtox.properties" })
@Service
public class DataBasePopulationServiceImpl implements DataBasePopulationService{
	
	@Autowired
    private Environment env;
	
	@Autowired
	@Qualifier("documentDaoJPAImpl")
	private DocumentDao documentDao;
	
	@Autowired
	@Qualifier("sectionDaoJPAImpl")
	private SectionDao sectionDao;
	
	@Autowired
	@Qualifier("hepatotoxicityTermDaoJPAImpl")
	private HepatotoxicityTermDao hepatotoxicityTermDao;
	
	@Autowired
	@Qualifier("markerDaoJPAImpl")
	private MarkerDao markerDao;
	
	@Autowired
	@Qualifier("chemicalCompoundJPADaoImpl")
	private ChemicalCompoundDao chemicalCompoundDao;
	
	@Autowired
	@Qualifier("cytochromeDaoJPAImpl")
	private CytochromeDao cytochromeDao;
	
	@Override
	public void execute(File source_root_folder) {
		File findinds_folder = new File(source_root_folder + File.separator + "findings");
		Collection<File> files = FileUtils.listFiles(findinds_folder, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			try {
				if (file.getName().endsWith(".json")) {
					ObjectMapper objectMapper = new ObjectMapper();
					PubMedDocument document = objectMapper.readValue(file, PubMedDocument.class);
					for (Sentence sentence : document.getSentences()) {
						
						
						for (ChemicalCompoundSentence chemicalCompoundSentence : sentence.getChemicalCompoundSentences()) {
							chemicalCompoundSentence.setSentence(sentence);
							ChemicalCompound chemicalCompound = chemicalCompoundDao.findByName(chemicalCompoundSentence.getChemicalCompound().getName());
							if(chemicalCompound!=null) {
								chemicalCompoundSentence.setChemicalCompound(chemicalCompound);
								chemicalCompoundSentence.setSentence(sentence);
							}
						}
						for (HepatotoxicityTermSentence hepatotoxicitySentence : sentence.getHepatotoxicityTermSentences()) {
							HepatotoxicityTerm hepatotoxicityTerm = hepatotoxicityTermDao.findByTerm(hepatotoxicitySentence.getHepatotoxicityTerm().getTerm());
							if(hepatotoxicityTerm!=null) {
								hepatotoxicitySentence.setHepatotoxicityTerm(hepatotoxicityTerm);
								hepatotoxicitySentence.setSentence(sentence);
							}
						}
						for (MarkerSentence markerSentence : sentence.getMarkerSentences()) {
							markerSentence.setSentence(sentence);
							Marker marker = markerDao.findByIdentifier(markerSentence.getMarker().getMarker_identifier());
							if(marker!=null) {
								markerSentence.setMarker(marker);
								markerSentence.setSentence(sentence);
							}
						}
//						for (CytochromeSentence cytochromeSentence : sentence.getCytochromeSentences()) {
//							markerSentence.setSentence(sentence);
//							Marker marker = markerDao.findByIdentifier(cytochromeSentence.getMarker().getMarker_identifier());
//							if(marker!=null) {
//								markerSentence.setMarker(marker);
//								markerSentence.setSentence(sentence);
//							}
//						}
//						for (ChemicalCompoundCytochromeSentence chemicalCompoundCytochromeSentence : sentence.getChemicalCompoundCytochromeSentences()) {
//							chemicalCompoundCytochromeSentence.setSentence(sentence);
//						}
//						for (HepatotoxicityTermChemicalCompoundSentence hepatotoxicityTermChemicalCompoundSentence : sentence.getHepatotoxicityTermChemicalCompoundSentences()) {
//							hepatotoxicityTermChemicalCompoundSentence.setSentence(sentence);
//						}
//						for (MarkerChemicalCompoundSentence markerChemicalCompoundSentence : sentence.getMarkerChemicalCompoundSentences()) {
//							markerChemicalCompoundSentence.setSentence(sentence);
//						}
						Section section = sectionDao.findByName(sentence.getSection().getName());
						if(section==null) {
							section = sectionDao.create(section);
						}
						sentence.setSection(section);
						sentence.setDocument(document);
					}
					documentDao.save(document);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


}
