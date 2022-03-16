package com.lin.designmode.eventmode;

/**
 * 双击事件监听
 * @author lin
 * @date 2022/3/16 22:09
 **/
public class DoubleClickEventListener implements EventListener {
    @Override
    public void processEvent(Event event) {
        if("DoubleClick".equals(event.getType())){
            System.out.println("双击事件被触发");
        }
    }
}
