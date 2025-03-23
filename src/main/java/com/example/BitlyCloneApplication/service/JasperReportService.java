package com.example.BitlyCloneApplication.service;

import com.example.BitlyCloneApplication.model.ClickEvent;
import com.example.BitlyCloneApplication.model.URLMapping;
import com.example.BitlyCloneApplication.repository.ClickRepo;
import com.example.BitlyCloneApplication.repository.UrlMappingRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {

    @Autowired
    ClickRepo clickRepo;
    @Autowired
    UrlMappingRepo urlMappingRepo;

    public String exportReport(String reportFormat,String reportName) throws FileNotFoundException, JRException {
        List<ClickEvent> clickEventList=new ArrayList<>();
        List<URLMapping> urlMappingList=new ArrayList<>();

        if("urlmappings.jrxml".equalsIgnoreCase(reportName)){
            urlMappingList=urlMappingRepo.findAll();
        }
        if("clickevent.jrxml".equalsIgnoreCase(reportName)){
            clickEventList= clickRepo.findAll();
        }

        Map<String,Object> parameters=new HashMap<>();
        parameters.put("created by","amit dhakal");
        File file= ResourceUtils.getFile("classpath:"+reportName);
        JasperReport report= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(urlMappingList);
        JasperPrint jasperPrint= JasperFillManager.fillReport(report,parameters,dataSource);
if(reportFormat.equalsIgnoreCase("PDF")){
    JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Amit-PC\\Desktop\\ACCORD INNOVASTIONS"+"\\"+"urlmappings"+".pdf");
    return "report generated in path:";
}else if(reportFormat.equalsIgnoreCase("HTML")){
    JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\Amit-PC\\Desktop\\ACCORD INNOVASTIONS"+"\\"+"urlmappings"+".html");
    return "report generated in path:";
}
else{
    return "report format not found:";
}
    }
}
