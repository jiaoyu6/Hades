package com.haders.service.excelService.Listeners;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.haders.dto.ExcelDto;
import com.haders.entity.StaffEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

// 有个很重要的点 ExcelListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class ExcelListener implements ReadListener<ExcelDto> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<ExcelDto> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private JpaRepository excelRepository;

    private Object entity;
    
    private List<Object> toSaveDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param excelRepository
     */
    public ExcelListener(JpaRepository excelRepository, Object entity) {
        this.excelRepository = excelRepository;
        this.entity = entity;
    }
    
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelDto data, AnalysisContext context) {
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            toSaveDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

        }
    }

    /**
     * 做一个类型转换 可能涉及到业务
     * @param cachedDataList
     * @param toSaveData
     */
    private void covertDtoToEntity(List<ExcelDto> cachedDataList, Object toSaveData) {
        cachedDataList.forEach(dto -> {
            StaffEntity staffEntity = new StaffEntity();
            BeanUtils.copyProperties(dto, staffEntity);
            toSaveDataList.add(staffEntity);
        });
        
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        covertDtoToEntity(cachedDataList, entity);

        log.info("{}条数据，开始存储数据库！", toSaveDataList.size());
        excelRepository.saveAll(toSaveDataList);
        log.info("存储数据库成功！");
    }
}