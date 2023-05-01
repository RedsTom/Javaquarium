package fr.rauster;

public enum FishType {
    GROUPER(Diet.CARNIVORE, SexualityType.HERMAPHRODITE_WITH_AGE), //mérou
    TUNA(Diet.CARNIVORE, SexualityType.MONO_SEXUAL), //thon
    CLOWNFISH(Diet.CARNIVORE, SexualityType.OPPORTUNIST_HERMAPHRODITE), //poisson clown
    SOLE(Diet.HERBIVORE, SexualityType.OPPORTUNIST_HERMAPHRODITE), //sole
    BASS(Diet.HERBIVORE, SexualityType.HERMAPHRODITE_WITH_AGE), //bar
    CARP(Diet.HERBIVORE, SexualityType.MONO_SEXUAL); //carpe
    
    private final Diet diet;
    private final SexualityType sexualityType;
    FishType(Diet diet, SexualityType sexualityType) {
        this.diet = diet;
        this.sexualityType = sexualityType;
    }
    public Diet getDiet() {
        return diet;
    }
    public SexualityType getSexualityType() {
        return sexualityType;
    }
}
