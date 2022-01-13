package com.lin.designmode.facade;

import com.lin.designmode.facade.subclasses.BrickLayer;
import com.lin.designmode.facade.subclasses.BrickWorker;
import com.lin.designmode.facade.subclasses.Mason;

/**
 * 外观角色
 * @author lin
 * @date 2022/1/12 23:22
 **/
public class LabourContractor {
    private Mason mason = new Mason();
    private BrickWorker brickWorker = new BrickWorker();
    private BrickLayer brickLayer = new BrickLayer();
    public void buildHouse(){
        mason.mix();
        brickWorker.carry();
        brickLayer.neat();
    }
}
