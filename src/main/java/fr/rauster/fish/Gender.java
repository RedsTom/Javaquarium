package fr.rauster.fish;

import java.util.Random;

public enum Gender {
    MALE,
    FEMALE;
    
    public static Gender random() {
        return new Random().nextBoolean() ? FEMALE : MALE;
    }
    public Gender opposite() {
        if (this == Gender.MALE) return Gender.FEMALE;
        else return MALE;
    }
}
