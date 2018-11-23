package es.bsc.inb.limtox.services;

import java.io.File;

public interface DataBasePopulationService {
	
	public void execute(File source_root_folder);

	public void createAndLoadEntityTypes(File inputEntityStructureFilePath);
}
