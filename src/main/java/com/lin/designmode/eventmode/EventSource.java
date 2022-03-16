package com.lin.designmode.eventmode;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件源
 * @author lin
 * @date 2022/3/16 22:10
 **/
public class EventSource {

    private List<EventListener> listeners = new ArrayList<>();

    /**
     * 注册监听器
     * @param eventListener
     */
    public void register(EventListener eventListener){
        listeners.add(eventListener);
    }

    /**
     * 发布事件
     * @param event
     */
    public void publishEvent(Event event){
        for (EventListener eventListener : listeners){
            eventListener.processEvent(event);
        }
    }
}
