package innerclasses.controller;

import java.util.LinkedList;
import java.util.List;

/**
 * 控制器
 *
 * @author Cheng Cheng
 * @date 2017-10-18 15:39
 */
public class Controller {
    private List<Event> eventList = new LinkedList<>();

    public void addEvent(Event e) {
        eventList.add(e);
    }

    public void run() {
        while (eventList.size() > 0) {
            for (Event e : new LinkedList<>(eventList)) {
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}