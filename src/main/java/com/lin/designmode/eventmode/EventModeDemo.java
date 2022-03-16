package com.lin.designmode.eventmode;

/**
 * @author lin
 * @date 2022/3/16 22:14
 **/
public class EventModeDemo {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        eventSource.register(new SingleClickEventListener());
        eventSource.register(new DoubleClickEventListener());
        Event event = new Event();
        event.setType("SingleClick");
        eventSource.publishEvent(event);
        event.setType("DoubleClick");
        eventSource.publishEvent(event);
    }
}
