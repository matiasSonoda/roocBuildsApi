package com.roocbuilds.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum JobClass {
    LORD_KNIGHT("Lord Knight"),
    PALADIN("Paladin"),
    SNIPER("Sniper"),
    MINSTREL("Minstrel"),
    GYPSY("Gypsy"),
    ASSASIN_CROSS("Assasin Cross"),
    STALKER("Stalker"),
    HIGH_PRIEST("High Priest"),
    CHAMPION("Champion"),
    HIGH_WIZZARD("High Wizzard"),
    PROFESSOR("Professor"),
    MASTERSMITH("Mastersmith"),
    BIOCHEMIST("Biochemist");

    private final String nameClass;

    JobClass(String nameClass){
        this.nameClass = nameClass;
    }

    @JsonValue
    public String getNameClass(){
        return nameClass;
    }
}
