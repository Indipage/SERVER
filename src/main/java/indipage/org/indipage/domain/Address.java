package indipage.org.indipage.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String MetroGovernment;
    private String BaseGovernment;
    private String City;
    private String Town;
    private String RoadName;
    private String BuildingNumber;
    private String detail;

}