package com.example.springboot;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PathsController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(defaultValue = "friend") String name) {
		return greetingFor(LocalTime.now().getHour(), name);
	}

	@GetMapping("/grade")
	public String grade(@RequestParam int score) {
		return gradeFor(score);
	}

	@GetMapping("/temperature")
	public String temperature(@RequestParam int degrees) {
		return temperatureAdvice(degrees);
	}

	@GetMapping("/ticket")
	public String ticket(@RequestParam int age) {
		return ticketPrice(age);
	}

	static String greetingFor(int hour, String name) {
		if (name == null || name.isBlank()) {
			name = "friend";
		}
		if (hour < 12) {
			return "Good morning, " + name + "!";
		} else if (hour < 17) {
			return "Good afternoon, " + name + "!";
		} else if (hour < 21) {
			return "Good evening, " + name + "!";
		} else {
			return "Good night, " + name + "!";
		}
	}

	static String gradeFor(int score) {
		if (score < 0 || score > 100) {
			return "Score must be between 0 and 100.";
		}
		String letter;
		if (score >= 90) {
			letter = "A";
		} else if (score >= 80) {
			letter = "B";
		} else if (score >= 70) {
			letter = "C";
		} else if (score >= 60) {
			letter = "D";
		} else {
			letter = "F";
		}
		if (score >= 60) {
			return "Score " + score + ": grade " + letter + ". You passed!";
		}
		return "Score " + score + ": grade " + letter + ". Time to study more.";
	}

	static String temperatureAdvice(int degrees) {
		if (degrees <= 32) {
			return degrees + "F is freezing. Wear a heavy coat.";
		} else if (degrees <= 60) {
			return degrees + "F is chilly. Bring a jacket.";
		} else if (degrees <= 80) {
			return degrees + "F is mild. Perfect weather.";
		} else {
			return degrees + "F is hot. Stay hydrated.";
		}
	}

	static String ticketPrice(int age) {
		if (age < 0) {
			return "Age cannot be negative.";
		} else if (age <= 5) {
			return "Age " + age + ": free ticket.";
		} else if (age <= 17) {
			return "Age " + age + ": child ticket, $8.";
		} else if (age < 65) {
			return "Age " + age + ": adult ticket, $15.";
		} else {
			return "Age " + age + ": senior ticket, $10.";
		}
	}

}
