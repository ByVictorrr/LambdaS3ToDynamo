package helloworld;

import java.util.Set;

public class Manufacturer {
        private String _name;
        private String link;
        private Set<Device> devices;

        public String getName() {
            return _name;
        }
        public void setName(final String _name) {
            this._name = _name;
        }

        public void setDevices(Set<Device> devices) {
            this.devices = devices;
        }
        public void setLink(String link){
            this.link=link;
        }

        public Set<Device> getDevices() {
            return devices;
        }
}
