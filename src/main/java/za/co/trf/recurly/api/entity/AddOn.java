package za.co.trf.recurly.api.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="add_on")
public class AddOn {

	@XmlElement(name="add_on_code")
	public String 	addOnCode;

	@XmlElement(name="unit_amount_in_cents")
	public Integer 	unitAmountInCents;

	@XmlElement(name="quantity")
	public Integer	quantity;

    public AddOn() {
    }

    public AddOn(String addOnCode, Integer quantity) {
        this.addOnCode = addOnCode;
        this.quantity = quantity;
    }

}