package fr.rauster.fish;

public enum Gender {
    MALE,
    FEMALE;
    public Gender opposite() {
        if (this == Gender.MALE) return Gender.FEMALE;
        else return MALE;
    }
}
