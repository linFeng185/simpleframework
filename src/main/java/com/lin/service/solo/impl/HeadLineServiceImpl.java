package com.lin.service.solo.impl;

import com.lin.entity.bo.HeadLine;
import com.lin.entity.dto.Result;
import com.lin.service.solo.HeadLineService;
import org.simpleframework.croe.annotation.Service;

import java.util.List;

/**
 * @author lin
 * @date 2022/1/13 22:33
 **/
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        return null;
    }
}
