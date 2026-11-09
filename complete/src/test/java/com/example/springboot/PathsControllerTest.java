package com.example.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PathsControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void greetingUsesTimeOfDay() {
		assertEquals("Good morning, Ty!", PathsController.greetingFor(8, "Ty"));
		assertEquals("Good afternoon, Ty!", PathsController.greetingFor(14, "Ty"));
		assertEquals("Good evening, Ty!", PathsController.greetingFor(19, "Ty"));
		assertEquals("Good night, friend!", PathsController.greetingFor(23, ""));
	}

	@Test
	public void greetingPathDefaultsName() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/greeting?name=Ty"))
				.andExpect(status().isOk());
	}

	@Test
	public void gradePassAndFail() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/grade?score=95"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Score 95: grade A. You passed!")));
		mvc.perform(MockMvcRequestBuilders.get("/grade?score=45"))
				.andExpect(content().string(equalTo("Score 45: grade F. Time to study more.")));
		mvc.perform(MockMvcRequestBuilders.get("/grade?score=150"))
				.andExpect(content().string(equalTo("Score must be between 0 and 100.")));
	}

	@Test
	public void temperatureAdvice() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/temperature?degrees=20"))
				.andExpect(content().string(equalTo("20F is freezing. Wear a heavy coat.")));
		mvc.perform(MockMvcRequestBuilders.get("/temperature?degrees=72"))
				.andExpect(content().string(equalTo("72F is mild. Perfect weather.")));
		mvc.perform(MockMvcRequestBuilders.get("/temperature?degrees=95"))
				.andExpect(content().string(equalTo("95F is hot. Stay hydrated.")));
	}

	@Test
	public void ticketPrices() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/ticket?age=4"))
				.andExpect(content().string(equalTo("Age 4: free ticket.")));
		mvc.perform(MockMvcRequestBuilders.get("/ticket?age=30"))
				.andExpect(content().string(equalTo("Age 30: adult ticket, $15.")));
		mvc.perform(MockMvcRequestBuilders.get("/ticket?age=70"))
				.andExpect(content().string(equalTo("Age 70: senior ticket, $10.")));
		mvc.perform(MockMvcRequestBuilders.get("/ticket?age=-1"))
				.andExpect(content().string(equalTo("Age cannot be negative.")));
	}

}
