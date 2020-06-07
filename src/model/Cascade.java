package model;

/**
 *
 */

public enum Cascade {
    CAR("myhaar.xml"),
    NUMBER_CAR("haarcascade_russian_plate_number.xml"),
    FLOW("cars.xml");
    private final String path;
    Cascade(String path) {
        this.path = path;
    }
    public String getPath() {
        return "//haarCascades//" + this.path;
    }
}
