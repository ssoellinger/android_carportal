package eaustria.net.autoportal.eaustria.net.model;

import java.io.Serializable;
import org.osmdroid.util.GeoPoint;

/**
 * Created by bmayr on 09.03.15.
 */
public class Car implements Serializable {
    private int id;
    private String brand;
    private String manufacturer;
    private GeoPoint position;

    public Car(int id, String marke, String hersteller, GeoPoint standort) {
        this.id = id;
        this.brand = marke;
        this.manufacturer = hersteller;
        this.position = standort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public GeoPoint getPosition() {
        return position;
    }

    public void setPosition(GeoPoint position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return this.id + " " + this.manufacturer + ", " + this.getBrand();
    }
}
