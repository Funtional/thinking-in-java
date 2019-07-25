package typeinfo;

import typeinfo.pets.Pets;

/**
 * @author Cheng Cheng
 * @date 2017-11-28 11:47
 */
public class PetCount2 {
    public static void main(String[] args) {
        PetCount.countPets(Pets.creator);
        // output
        //EgyptianMau Gerbil Cymric EgyptianMau Cymric EgyptianMau Pug Rat Mutt Cymric Manx Manx Manx Cymric EgyptianMau Pug Hamster Cymric Gerbil Pug
        //{EgyptianMau=4, Pug=3, Cymric=5, Rat=1, Cat=12, Manx=8, Rodent=4, Mutt=1, Gerbil=2, Dog=4, Pet=20, Hamster=1}
    }
}