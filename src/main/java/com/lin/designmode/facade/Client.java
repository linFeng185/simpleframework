package com.lin.designmode.facade;

/**
 * @author lin
 * @date 2022/1/12 23:35
 **/
public class Client {
    public static void main(String[] args) {
        LabourContractor labourContractor = new LabourContractor();
        labourContractor.buildHouse();
    }
}
