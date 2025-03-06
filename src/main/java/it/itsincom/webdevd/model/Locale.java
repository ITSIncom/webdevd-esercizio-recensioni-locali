package it.itsincom.webdevd.model;

public class Locale {
    private String id;
    private String name;
    private String address;
    private String photoPath;

    public Locale(String id, String name, String address, String photoPath) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.photoPath = photoPath;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
