package org.assignment.entity;

import lombok.Getter;
import lombok.Setter;

import static org.assignment.constant.StringConstant.COMMA_DELIMITER;
import static org.assignment.constant.StringConstant.LOG_CUSTOMER_COORDINATE;

@Getter
@Setter
public class CustomerEntity extends LocationEntity {
	public CustomerEntity(String coordinates) {
		String[] cord = coordinates.split(COMMA_DELIMITER);
		this.latitude = Double.parseDouble(cord[0]);
		this.longitude = Double.parseDouble(cord[1]);
		System.out.println(LOG_CUSTOMER_COORDINATE + coordinates);
	}
}
