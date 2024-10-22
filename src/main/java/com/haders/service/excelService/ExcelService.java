package com.haders.service.excelService;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.util.ListUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haders.dto.StaffDto;
import com.haders.entity.StaffEntity;
import com.haders.repositories.StaffRepository;
import com.haders.service.excelService.Listeners.ExcelListener;
import com.haders.util.FileUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class ExcelService {
    
    @Resource
    private StaffRepository staffRepository;

    public void importStaffExcel(MultipartFile file) {

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 创建一个文件对象并保存文件
        File newFile = new File("/tmp/" + fileName);
        try {
            file.transferTo(newFile);
            System.out.println("文件保存成功:" + newFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("文件保存失败:" + e.getMessage());
        }
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        // 写法1 
//        EasyExcel.read(newFile.getPath(), StaffDto.class, new PageReadListener<StaffDto>(dataList -> {
//            for (StaffDto demoData : dataList) {
//                Gson gson = new GsonBuilder().serializeNulls().create();
//                log.info("读取到一条数据{}", gson.toJson(demoData));
//            }
//        })).sheet().doRead();

        // 写法二 
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭

        EasyExcel.read(newFile.getPath(), StaffDto.class, new ExcelListener(staffRepository, new StaffEntity())).sheet().doRead();

        // 写法3：
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

        // 写法4
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 一个文件一个reader
//        try (ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build()) {
//            // 构建一个sheet 这里可以指定名字或者no
//            ReadSheet readSheet = EasyExcel.readSheet(0).build();
//            // 读取一个sheet
//            excelReader.read(readSheet);
//        }
    }
}
