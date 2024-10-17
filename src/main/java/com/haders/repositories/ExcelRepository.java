package com.haders.repositories;

import com.haders.dto.ExcelDto;
import com.haders.dto.StaffDto;

import java.util.List;

public interface ExcelRepository {
    void save(List<ExcelDto> cachedDataList);
}
