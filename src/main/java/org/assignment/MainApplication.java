package org.assignment;

import org.assignment.entity.CustomerEntity;
import org.assignment.entity.OrderEntity;
import org.assignment.entity.RestaurantEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assignment.utils.CommonUtils.generateAvgTime;
import static org.assignment.utils.CommonUtils.getRandomCoordinates;
import static org.assignment.service.OrderService.processOrder;

public class MainApplication {

	public static void main(String[] args) {
		System.out.println("---------------------------- [ Start Application ] ------------------------------------");
		System.out.println("Avg speed: 20 KM/Hr");
		System.out.println("R0 => Restaurant 0 , R1 => Restaurant 1 , R2 => Restaurant 2 , C0 => Customer 0, C1 => Customer 1 , C2 => Customer 2");
		List<OrderEntity> batchOrder = new ArrayList<>();
		RestaurantEntity restaurant1 = new RestaurantEntity(generateAvgTime(), getRandomCoordinates());

		RestaurantEntity restaurant2 = new RestaurantEntity(generateAvgTime(), getRandomCoordinates());

		CustomerEntity customer1 = new CustomerEntity(getRandomCoordinates());
		CustomerEntity customer2 = new CustomerEntity(getRandomCoordinates());

		batchOrder.add(new OrderEntity(restaurant1, customer1));
		batchOrder.add(new OrderEntity(restaurant2, customer2));

		System.out.println("---------------------------- [ Solution ] ------------------------------------");
		processOrder(batchOrder, getRandomCoordinates());
	}
}