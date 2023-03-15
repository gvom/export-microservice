package dev.export.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.export.models.User;
import dev.export.services.ImportService;
import dev.export.services.UserService;

@RestController
public class ImportController {

	private ImportService importService =  new ImportService();
	
	@ResponseBody
	@RequestMapping(value = "/import", method = RequestMethod.POST)
    public ResponseEntity<String> importUsers(@RequestBody List<User> users)
    {		
		Integer importId = importService.startImportation(users);			
		return new ResponseEntity<String>("Importação iniciada com sucesso. ID da Importação: " + importId, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/import/status/{import-id}", method = RequestMethod.GET)
    public ResponseEntity<String> importStatus(@PathVariable String importId)
    {
		Integer status = importService.getImportStatus(Integer.parseInt(importId));
		if(status != null) {		
			String statusResp = (status == 100 ? "Finalizado" : "Em Progresso, " + status + "%");
			return new ResponseEntity<String>("Status da importação: " + statusResp, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
}
