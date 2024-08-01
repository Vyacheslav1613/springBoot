package ru.netology.springgiga.profile;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";

    }
}
