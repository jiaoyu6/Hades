package com.haders.controlelr;

import com.haders.service.excelService.ExcelService;
import com.haders.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController("/excel")
public class ExcelController {

    @Resource
    private ExcelService excelService;
    
    @PostMapping(value = "/import-staff-info",produces = MediaType.APPLICATION_JSON_VALUE)
    public String  importStaffExcel (@RequestPart(value = "file") MultipartFile file){
        ResponseData data = new ResponseData();

        excelService.importStaffExcel(file);
        return data.toJson();
    }
}
