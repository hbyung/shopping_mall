package study.shopping_mall.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String postcode;
    private String address;
    private String detailAddress;

    protected Address() {
    }

    public Address(String postcode, String address, String detailAddress) {
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
    }
}
