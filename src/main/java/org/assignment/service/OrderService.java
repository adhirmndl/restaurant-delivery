package org.assignment.service;

import org.assignment.entity.CustomerEntity;
import org.assignment.entity.OrderEntity;
import org.assignment.entity.RestaurantEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static org.assignment.constant.StringConstant.*;
import static org.assignment.utils.CommonUtils.*;

public class OrderService {


	public static void processOrder(List<OrderEntity> batchOrder, String currentCoordinates) {
		HashMap<String, RestaurantEntity> restaurantMap = new HashMap<>();
		HashMap<String, CustomerEntity> customerMap = new HashMap<>();
		String[] node = new String[batchOrder.size() * 2];
		for (int i = 0; i < batchOrder.size(); i++) {
			restaurantMap.put(R + i, batchOrder.get(i).getRestaurant());
			customerMap.put(C + i, batchOrder.get(i).getCustomer());
			node[i * 2] = R + i;
			node[i * 2 + 1] = C + i;
		}
		// we assume current position of aman is start
		String[] cord = currentCoordinates.split(COMMA_DELIMITER);
		double startLat = Double.parseDouble(cord[0]);
		double startLon = Double.parseDouble(cord[1]);

		int minTimeTaken = MAX_VALUE;
		String bestPath = EMPTY_STRING;

		List<String> paths = getPath(node.length, (new HashSet<>(Arrays.asList(node))).toArray(new String[0]), EMPTY_STRING);

		for (String path : paths) {
			int t = calcTotalTravelTime(restaurantMap, customerMap, path, startLat, startLon);
			if (minTimeTaken > t) {
				minTimeTaken = t;
				bestPath = path;
			}
		}
		pathPrint(bestPath);
		int hours = minTimeTaken / 3600;
		int minutes = (minTimeTaken % 3600) / 60;
		int seconds = minTimeTaken % 60;

		String timeString = String.format(STR_TIME_FORMAT, hours, minutes, seconds);
		System.out.println(STR_TIME_TAKEN + timeString);
	}
}
