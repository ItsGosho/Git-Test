package com.masdefect.controller;

import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.parser.JSONParser;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class PlanetsController {
    @Autowired
    private PlanetService planetService;

    public String importDataFromJSON(String fileContent) throws IOException, JAXBException {
        FileParser jsonParser=new JSONParser();
        PlanetImportJSONDto[] planets = jsonParser.read(PlanetImportJSONDto[].class,fileContent);
        List<String> toReturn=new LinkedList<>();
        for (PlanetImportJSONDto planet : planets) {
            try{
                this.planetService.create(planet);
                toReturn.add(String.format("Successfully imported Planet %s.",planet.getName()));
            }catch (Exception ex){
                toReturn.add(String.format("Error: Invalid data."));
            }
        }
        String finalRet="";
        for (String s : toReturn) {
            finalRet+=String.format("%s%n",s);
        }
        return finalRet;
    }

    public String planetsWithNoPeopleTeleportedToThem(){
        return null;
    }
}
