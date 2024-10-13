package com.delonia.di.rest;

import com.delonia.di.dto.ClientInfo;
import com.delonia.di.service.ProcessWordDoc;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    @GetMapping("/hoststate")
    public Map<String, String> getHostState() {
        Map<String, String> result = new HashMap<>();
        result.put("host", "ok");
        return result;
    }

    @GetMapping("/genTest")
    public Map<String, String> genTest() throws Exception {

        ClientInfo clientInfo = new ClientInfo("Pups", "Klops", "Perez");
        ProcessWordDoc.generatePdf(clientInfo, "ClientInfo.docx", "ClientInfo.pdf");


        Map<String, String> result = new HashMap<>();
        result.put("host", "ok");
        return result;
    }


}
