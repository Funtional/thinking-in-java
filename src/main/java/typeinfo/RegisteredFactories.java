package typeinfo;

import typeinfo.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 14.4 注册工厂
 *
 * @author Cheng Cheng
 * @date 2017-11-28 16:03
 */
public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}

class Part { //部分;片段;一点;成员;成分

    private static Random rand = new Random(47);

    /**
     * 保存各个组件的工程对象，createRandom方法可以调用工厂对象的create方法生成Part类的对象
     */
    static List<Factory<? extends Part>> partFactories = new ArrayList<>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning. 不能创建泛型数组
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CabinAirFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
    }

    /**
     * 如果某个类应该由createRandom()方法创建，那么它就包含一个内部Factory类
     * @return
     */
    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class Filter extends Part { // 滤器;过滤器;滤光器;滤声器;滤波器;筛选(过滤)程序
}

class FuelFilter extends Filter { //Fuel 燃料;(尤指使争论等继续或更加激烈的)刺激性言行
    // Create a Class Factory for each specific type:
    public static class Factory implements typeinfo.factory.Factory<FuelFilter> {

        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}

class AirFilter extends Filter { // 空气;空中;天空;(飞行的)空中
    public static class Factory implements typeinfo.factory.Factory<AirFilter> {

        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class CabinAirFilter extends Filter { //(轮船上工作或生活的)隔间;(飞机的)座舱;(通常为木制的)小屋，小棚屋
    public static class Factory implements typeinfo.factory.Factory<CabinAirFilter> {

        @Override
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}

class OilFilter extends Filter {//石油;原油;燃油;润滑油;食用油
    public static class Factory implements typeinfo.factory.Factory<OilFilter> {

        @Override
        public OilFilter create() {
            return new OilFilter();
        }
    }
}

class Belt extends Part {//腰带;皮带;传送带;传动带;地带;地区
}

class FanBelt extends Belt {//迷;狂热爱好者;狂热仰慕者;风扇;扇子
    public static class Factory implements typeinfo.factory.Factory<FanBelt> {

        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

class GeneratorBelt extends Belt {//发电机;生成器;发生器;编辑器;生成元
    public static class Factory implements typeinfo.factory.Factory<GeneratorBelt> {

        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}

class PowerSteeringBelt extends Belt { //Steering (车辆等的)转向装置
    public static class Factory implements typeinfo.factory.Factory<PowerSteeringBelt> {

        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}