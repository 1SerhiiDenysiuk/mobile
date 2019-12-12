package com.example.mobiletasks;

public class Equipment {
    private final String equipmentType;
    private final String equipmentName;
    private final String price;
    private final String workingArea;
    private final String description;
    private final String photoUrl;

    public Equipment(final String equipmentType, final String equipmentName, final String price,
                     final String workingArea, final String description, final String photoUrl) {
        this.equipmentType = equipmentType;
        this.equipmentName = equipmentName;
        this.price = price;
        this.workingArea = workingArea;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getPrice() {
        return price;
    }

    public String getWorkingArea() {
        return workingArea;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}