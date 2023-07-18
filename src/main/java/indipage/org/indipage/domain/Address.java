package indipage.org.indipage.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String metroGovernment;
    private String baseGovernment;
    private String city;
    private String town;
    private String roadName;
    private String detail;

    public String toString() {
        StringBuilder fullAddressBuilder = new StringBuilder();

        if (metroGovernment != null) {
            fullAddressBuilder.append(metroGovernment).append(" ");
        }
        if (baseGovernment != null) {
            fullAddressBuilder.append(baseGovernment).append(" ");
        }
        if (city != null) {
            fullAddressBuilder.append(city).append(" ");
        }
        if (town != null) {
            fullAddressBuilder.append(town).append(" ");
        }
        if (roadName != null) {
            fullAddressBuilder.append(roadName).append(" ");
        }
        if (detail != null) {
            fullAddressBuilder.append(detail);
        }

        return fullAddressBuilder.toString().trim();
    }
}