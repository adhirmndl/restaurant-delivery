package org.assignment.utils;

import org.assignment.entity.CustomerEntity;
import org.assignment.entity.RestaurantEntity;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assignment.constant.StringConstant.*;

public class CommonUtils {

	private static final Random random;

	static {
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}


	private CommonUtils() {
		throw new IllegalStateException(INSTANCE_CREATION_ERROR);
	}

	public static Double getDistanceFromLatLonInKm(Double lat1, Double lon1, Double lat2, Double lon2) {
		Double r = 6371.0; // Radius of the earth in km
		double dLat = deg2rad(lat2 - lat1);  // deg2rad below
		double dLon = deg2rad(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return r * c; // Distance in km
	}

	public static Double deg2rad(Double deg) {
		return deg * (Math.PI / 180);
	}

	// taking distance in kilometers
	public static double calculateTravelTime(Double distance) {
		// assume that average speed of 20km/hr
		return 3600 * distance / 20;
	}

	/*
	 * this method will return random coordinates which already predefined, collected from Google Maps
	 */
	public static String getRandomCoordinates() {
		List<String> points = new ArrayList<>();
		points.add("12.973291, 77.547089"); //Vijayanagar
		points.add("12.965487, 77.576151"); //Old Tharagupet
		points.add("12.968414, 77.578943"); //Kumbarpet, Mamulpet, Chickpet
		points.add("12.967260, 77.582017"); //24, Ganigara A Ln, Ganigarpet, Nagarathpete
		points.add("12.966637, 77.575634"); //Aliyas Faruk Fruits, XH8G+M76, Sethu Rao Street
		points.add("12.957575, 77.598489"); //Lakshmiamma Garden, Shanti Nagar, Bengaluru,
		points.add("12.961597, 77.592632"); //Srinivas Colony, Sudhama Nagar, Bengaluru
		points.add("12.949964, 77.573601"); //Metro Station National College, Shankarapura
		points.add("12.941044, 77.621111"); //CA-12, 20th Main Rd, Next to Koramangala Police station
		points.add("12.937437, 77.627488"); //Spar Hypermarket 1,30, 100 Feet Rd, AVS Layout,
		points.add("12.932245, 77.613458"); //Hosur Rd, Koramangala Industrial Layout,
		points.add("12.942092, 77.615033"); //323, 2nd H Main Rd,Koramangala 8th Block
		points.add("12.944676, 77.626913"); //Gowda Muniswamy Garden, Ejipura, Bengaluru
		points.add("12.932001, 77.634491"); //D Solitaires, S.T. Bed, 1st Block Koramangala
		points.add("12.935974, 77.621561"); //52, 60 Feet Rd, KHB Colony, 5th Block
		points.add("12.934713491553513, 77.61272555091968"); //Restaurant
		points.add("12.935315765015698, 77.61285796970836"); //Sea Meen Restaurant
		points.add("12.934650793043957, 77.61342254670089"); //Gramin | Veg Restaurant
		points.add("12.935006317271533, 77.61390534434103"); //Badmaash Koramangala
		points.add("12.93369924041274, 77.61528936424278"); //Royal Treat
		points.add("12.936376126470158, 77.62144771636379"); //China Pearl
		points.add("12.934075679250189, 77.61938777976583"); //Tandoor Hut

		int index = random.nextInt(points.size());
		return points.get(index);
	}

	// avg time will return in seconds
	public static int generateAvgTime() {
		// min 5 min (300 seconds) and max 1hr (3600 seconds)
		return random.nextInt(3300) + 300;
	}

	private static boolean isValidPath(String str, String[] words) {
		for (String word : words) {
			if (!str.contains(word)) {
				return false;
			}
		}
		StringBuilder s = new StringBuilder();
		List<String> seq = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) == C.charAt(0) || str.charAt(i) == R.charAt(0)) && s.length() > 0) {
				StringBuilder n = s;
				if (n.charAt(0) == C.charAt(0)) {
					n.setCharAt(0, R.charAt(0));
					if (!seq.contains(n.toString())) {
						return false;
					}
				}
				seq.add(s.toString());
				s = new StringBuilder();
			}
			s.append(str.charAt(i));
		}
		seq.add(s.toString());
		return true;
	}

	public static List<String> getPath(final int n, final String[] paths, final String currentPath) { // example of N = 3
		List<String> pathList = new ArrayList<>();
		if (n == 0) {
			if (isValidPath(currentPath, paths)) {
				pathList.add(currentPath);
				return pathList;
			}
		} else {
			for (String path : paths) {
				List<String> t = getPath(n - 1, paths, currentPath + path);
				pathList.addAll(t);
			}
		}
		return pathList;
	}

	public static void pathPrint(String path) {
		StringBuilder s = new StringBuilder(LOG_VISITING_PATH);
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == R.charAt(0) || path.charAt(i) == C.charAt(0)) {
				s.append(LOG_INDICATOR);
			}
			s.append(path.charAt(i));
		}
		// printing path to console.
		System.out.println(s);
	}

	public static int calcTotalTravelTime(Map<String, RestaurantEntity> restaurantMap, Map<String, CustomerEntity> customerMap, String path, double startLat, double startlong) {
		StringBuilder s = new StringBuilder();
		double timeSpend = 0;
		double lastLat = startLat;
		double lastLong = startlong;

		for (int i = 0; i < path.length(); i++) {
			if ((path.charAt(i) == R.charAt(0) || path.charAt(i) == C.charAt(0)) && (s.length() > 0)) {
				if (s.toString().startsWith(C)) {
					CustomerEntity customer = customerMap.get(s.toString());
					double time = calculateTravelTime(getDistanceFromLatLonInKm(lastLat, lastLong, customer.getLatitude(), customer.getLongitude()));
					lastLat = customer.getLatitude();
					lastLong = customer.getLongitude();
					timeSpend += time;
					s = new StringBuilder();
				} else if (s.toString().startsWith(R)) {
					RestaurantEntity restaurant = restaurantMap.get(s.toString());
					double time = calculateTravelTime(getDistanceFromLatLonInKm(lastLat, lastLong, restaurant.getLatitude(), restaurant.getLongitude()));
					lastLat = restaurant.getLatitude();
					lastLong = restaurant.getLongitude();
					if (restaurant.getAvgTimeToMakeMeals() > timeSpend) {
						// if average time is more than travel time, delivery boy have to wait to take meals
						timeSpend = restaurant.getAvgTimeToMakeMeals();
					} else {
						timeSpend += time;
					}
					s = new StringBuilder();
				}
			}
			s.append(path.charAt(i));
		}
		// it is guaranteed that last node definitely customer.
		CustomerEntity customer = customerMap.get(s.toString());
		double time = calculateTravelTime(getDistanceFromLatLonInKm(lastLat, lastLong, customer.getLatitude(), customer.getLongitude()));
		timeSpend += time;
		return (int) timeSpend;
	}
}
