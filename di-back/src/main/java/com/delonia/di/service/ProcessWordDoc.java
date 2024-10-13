package com.delonia.di.service;

import com.delonia.di.dto.ClientInfo;
import com.documents4j.api.DocumentType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.Conversion;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.documents4j.local.Documents4jLocalServices;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.jaxb.NamespacePrefixMapperUtils;


import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


@Getter
@Setter
@Slf4j

public class ProcessWordDoc {

    public static void generatePdf(ClientInfo clientInfo, String templatePath, String outputPdfPath) throws Exception {

        // Load the .docx file
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(templatePath));


        // Prepare variable replacements
        Map<String, String> variables = new HashMap<>();
        variables.put("Nombre", clientInfo.getNombre());
        variables.put("Apellido1", clientInfo.getApellido1());
        variables.put("Apellido2", clientInfo.getApellido2());

        variables.put("colour", "green");
        variables.put("icecream", newlineToBreakHack("chocolate\nor strawberry"));

        // Prepare the document for variable replacement
        VariablePrepare.prepare(wordMLPackage);

        // Replace placeholders with actual data
        wordMLPackage.getMainDocumentPart().variableReplace(variables);

        // Configure the PDF output settings
        FOSettings foSettings = Docx4J.createFOSettings();
        foSettings.setOpcPackage(wordMLPackage);

        // Set Apache FOP configuration (optional)
        foSettings.setApacheFopMime("application/pdf");

        // Create the output stream for the PDF file
        try (OutputStream os = Files.newOutputStream(Paths.get(outputPdfPath))) {
            // Convert .docx to PDF
            Docx4J.toFO(foSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
        }

        System.out.println("PDF generated successfully at: " + outputPdfPath);
    }


    private static String newlineToBreakHack(String r) {

        StringTokenizer st = new StringTokenizer(r, "\n\r\f"); // tokenize on the newline character, the carriage-return character, and the form-feed character
        StringBuilder sb = new StringBuilder();

        boolean firsttoken = true;
        while (st.hasMoreTokens()) {
            String line = (String) st.nextToken();
            if (firsttoken) {
                firsttoken = false;
            } else {
                sb.append("</w:t><w:br/><w:t>");
            }
            sb.append(line);
        }
        return sb.toString();
    }

}
