package generics;

import java.util.*;

class Frob {//小物品;开关旋钮
}

class Fnorkle {
}

class Quark<Q> {//夸克;快客;夸克奶酪;夸克干酪;赵域
}

class Particle<POSITION, MOMENTUM> {//微尘;粒子系统;顆粒;颗粒度;分子
}


public class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        Map<Frob, Fnorkle> map = new HashMap<>();
        Quark<Fnorkle> quark = new Quark<>();
        Particle<Long, Double> p = new Particle<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }
}
