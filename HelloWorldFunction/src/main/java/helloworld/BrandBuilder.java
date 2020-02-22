package helloworld;

import java.util.ArrayList;
import java.util.List;

/**
 * Helps build a brand
 *
 */
public class BrandBuilder {

    private List<Device> devices;

    public BrandBuilder(Device ... devices){
        this.devices = new ArrayList<Device>();
        for (Device d: devices){
            this.devices.add(d);
        }
    }

    public BrandBuilder append(Device ... devices){
        for (Device d: devices){
           this.devices.add(d);
        }
        return this;
    }




}
