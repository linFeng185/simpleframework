package com.lin.designmode.eventmode;

/**
 * 单击事件监听
 * @author lin
 * @date 2022/3/16 22:07
 **/
public class SingleClickEventListener implements EventListener{
    @Override
    public void processEvent(Event event) {
        if ("SingleClick".equals(event.getType())){
            System.out.println("触发了单击事件");
        }
    }
}
