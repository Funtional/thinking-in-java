package generics;

import java.util.List;

/**
 * 超级大国
 */
interface SuperPower {
}

/**
 * X光视力
 */
interface XRayVision extends SuperPower {
    /**
     * 看穿墙壁
     */
    void seeThroughWalls();
}

/**
 * 超级听力
 */
interface SuperHearing extends SuperPower {
    /**
     * 听见细微的声音
     */
    void hearSubtleNoises();
}

/**
 * 超级嗅觉
 */
interface SuperSmell extends SuperPower {
    /**
     * 嗅觉跟踪
     */
    void trackBySmell();
}

/**
 * 超级英雄
 *
 * @param <POWER>
 */
class SuperHero<POWER extends SuperPower> {
    POWER power;

    SuperHero(POWER power) {
        this.power = power;
    }

    POWER getPower() {
        return power;
    }
}

/**
 * 超级侦探
 *
 * @param <POWER>
 */
class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {

    SuperSleuth(POWER power) {
        super(power);
    }

    void see() {
        power.seeThroughWalls();
    }
}

/**
 * 犬英雄
 *
 * @param <POWER>
 */
class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {
    CanineHero(POWER power) {
        super(power);
    }

    void hear() {
        power.hearSubtleNoises();
    }

    void smell() {
        power.hearSubtleNoises();
    }
}

class SuperHearSmell implements SuperHearing, SuperSmell {
    @Override
    public void hearSubtleNoises() {

    }

    @Override
    public void trackBySmell() {

    }
}

class DogBoy extends CanineHero<SuperHearSmell> {

    DogBoy() {
        super(new SuperHearSmell());
    }
}

public class EpicBattle {
    // Bounds in generic methods:
    static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
    }

    static <POWER extends SuperHearing & SuperSmell> void superFind(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
        hero.getPower().trackBySmell();
    }

    public static void main(String[] args) {
        DogBoy dogBoy = new DogBoy();
        useSuperHearing(dogBoy);
        superFind(dogBoy);
        // You can do this:
        List<? extends SuperHearing> audioBoys;
        // But you can't do this:
        // List<? extends SuperHearing & SuperSmell> dogBoys;
    }
}
