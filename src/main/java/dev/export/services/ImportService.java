package dev.export.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import dev.export.models.User;

@Service
public class ImportService {
	
	private static final Integer POOL_SIZE = 10;
	private final ExecutorService delegate = Executors.newFixedThreadPool(POOL_SIZE);
	
	private Integer currentImport = 0;
	private List<ThreadService> imports = new ArrayList<ThreadService>();
	
	public Integer startImportation(List<User> users){
		currentImport++;
		
		ThreadService p = new ThreadService(users, currentImport);
		this.imports.add(p);
		delegate.execute(p);
		
		return currentImport;
    }
	
	public Integer getImportStatus(Integer importId) {
		
		for(ThreadService p : imports) {
			if(p.getImportId().equals(importId)) {
				return p.getPercentage();
			}
		}
		
		return null;
	}
	
	private void clearCache() {
		imports.clear();
	}
}
