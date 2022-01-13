package com.lin.service.solo;

import com.lin.entity.bo.HeadLine;
import com.lin.entity.dto.Result;

import java.util.List;

/**
 * @author lin
 * @date 2022/1/13 22:31
 **/
public interface HeadLineService {
    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
