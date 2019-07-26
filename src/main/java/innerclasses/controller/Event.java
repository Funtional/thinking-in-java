package innerclasses.controller;

/**
 * 事件封装
 *
 * @author Cheng Cheng
 * @date 2017-10-18 15:28
 */
public abstract class Event {
    private long eventTime;
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        this.start();
    }

    public void start() {
        this.eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready() {
        return System.nanoTime() >= this.eventTime;
    }

    public abstract void action();
}