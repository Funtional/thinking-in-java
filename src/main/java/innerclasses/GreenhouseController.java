package innerclasses;

import innerclasses.controller.Event;

/**
 * @author Cheng Cheng
 * @date 2017-10-18 18:27
 */
public class GreenhouseController {
    public static void main(String... args) {
        GreenhouseControls gc = new GreenhouseControls();
//        gc.addEvent(gc.new Bell(990000000));

        Event[] eventList = {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new FanOn(1000),
                gc.new FanOff(1200),
                gc.new ThermostatDay(1400)
        };

//        gc.addEvent(gc.new Restart(989999990, eventList));
//        if (args.length == 1) {
//            gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0])));
//        }
        gc.addEvent(eventList[0]);
        gc.run();
    }
}