package model;

/**
 *
 */

public enum DataFiles {
    VIDEO("cars.mp4"),
    IMAGE_POLICE("1.jpg"),
    IMAGE_1("8.jpg"),
    IMAGE_2("17.jpg"),
    IMAGE_3("18.jpg"),
    IMAGE_4("21.jpg");
    private final String path;
    DataFiles(String path) {
        this.path = path;
    }

    public String getPath() {
        return "//dataFiles//" + this.path;
    }
}
