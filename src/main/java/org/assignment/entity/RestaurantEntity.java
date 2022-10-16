package org.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.assignment.constant.StringConstant.*;
import static org.assignment.constant.StringConstant.STR_TIME_TAKEN;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEntity extends LocationEntity {
	long avgTimeToMakeMeals; // avg time to make meals in second

	public RestaurantEntity(long avgTimeToMakeMeals, String coordinates) {
		String[] cord = coordinates.split(COMMA_DELIMITER);
		this.avgTimeToMakeMeals = avgTimeToMakeMeals;
		this.latitude = Double.parseDouble(cord[0]);
		this.longitude = Double.parseDouble(cord[1]);
		int hours = (int) (avgTimeToMakeMeals / 3600);
		int minutes = (int) ((avgTimeToMakeMeals % 3600) / 60);
		int seconds = (int) (avgTimeToMakeMeals % 60);
		String timeString = String.format(STR_TIME_FORMAT, hours, minutes, seconds);
		System.out.println(LOG_RESTAURANT_COORDINATE + coordinates + LOG_MEAL_PREP_TIME + timeString);
	}
}
