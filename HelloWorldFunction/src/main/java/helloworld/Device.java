package helloworld;

import java.lang.reflect.Field;

/**
 *  Model of a device in Dynamodb
 */
public class Device {
    protected String name, os, manufacturer, notes, image;

    public void setName(String name) {
        this.name = name;
    }
    public void setOs(String os) {
        this.os = os;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setImage(String image){this.image=image;}
    public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getClass().getDeclaredField(fieldName);
        field.set(this,value);
    }

}
