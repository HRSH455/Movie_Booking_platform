

package com.examly.springapp;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.lang.reflect.Field;
import jakarta.persistence.ManyToOne;



@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

@Autowired
private MockMvc mockMvc;


    @Test
	@Order(1)
    public void backend_day15_testControllerFolder() {

		String directoryPath = "src/main/java/com/examly/springapp/controller";

		File directory = new File(directoryPath);
 
		assertTrue(directory.exists() && directory.isDirectory());
 
	}
    
    @Test
    @Order(2)
	public void backend_day15_testControllerFileTest() {

		String filePath = "src/main/java/com/examly/springapp/controller/TestController.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
    

   @Test
   @Order(3)
   public void backend_day15_testWelcomeApi() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/api/test/welcome"))
			.andExpect(MockMvcResultMatchers.status().isOk());
       }

   
   
   @Test
   @Order(4)
   public void backend_day16_testEntityFolder() {

   		String directoryPath = "src/main/java/com/examly/springapp/entity";

   		File directory = new File(directoryPath);
    
   		assertTrue(directory.exists() && directory.isDirectory());
    
   	}
	
	@Test
    @Order(5)
	public void backend_day16_testentityFileMovie() {

		String filePath = "src/main/java/com/examly/springapp/entity/Movie.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	@Order(6)
	public void backend_day16_testGetAllMovieList() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/test/movie")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andReturn();
	}
	
	@Test
	@Order(7)
 	public void backend_day17_testcontrollerMovieFile() {
 
		String filePath = "src/main/java/com/examly/springapp/controller/MovieController.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
    @Test
	@Order(8)
 	public void backend_day17_testserviceMovieFile() {
 
		String filePath = "src/main/java/com/examly/springapp/service/MovieService.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
    }
	
	@Test
	@Order(9)
	public void backend_day17_testAddMovie() throws Exception {
	String movieData = "{\"id\": 201, \"title\": \"Inception\", \"genre\": \"Sci-Fi\", \"duration\": 148}";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/movie")
				.contentType(MediaType.APPLICATION_JSON)
				.content(movieData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.title").value("Inception"))
				.andReturn();
	}


	@Test
    @Order(10)
    void backend_day18_testGetAllMovie() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movie")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[?(@.title == 'Inception')]").exists())
                .andReturn();
    }

	@Test
	@Order(11)
 	public void backend_day19_testrepositoryFile() {
 
		String filePath = "src/main/java/com/examly/springapp/repository/BookingRepo.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
    }


	@Test
	@Order(12)
 	public void backend_day19_testBookingFile() {
 
		String filePath = "src/main/java/com/examly/springapp/entity/Booking.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	

//Day 15 many to one api chcking
@Test
	    public void backend_day20_testBookingHasManyToOneAnnotation() {
	        try {
	            // Use reflection to get the Class object for the Course class
	            Class<?> bookingClass = Class.forName("com.examly.springapp.entity.Booking");

	            // Get all declared fields in the Course class
	            Field[] declaredFields = bookingClass.getDeclaredFields();

	            // Check each field for the @OneToOne annotation
	            boolean hasmanyToOne = false;
	            for (Field field : declaredFields) {
	                if (field.isAnnotationPresent(ManyToOne.class)) {
	                	hasmanyToOne = true;
	                    break; // Stop checking once we find one field with @OneToMany
	                }
	            }
		
		
	            // If no field with @OneToMany is found, fail the test
	            if (!hasmanyToOne) {
	                fail("No field with @ManyToOne annotation found in Feedback class.");
	            }

	        } catch (ClassNotFoundException e) {
	            // If the class is not found, fail the test
	            fail("Class not found: " + e.getMessage());
	        }
	    }


	
	@Test
	@Order(15)
 	public void backend_day21_testServiceBookingFile() {
 
		String filePath = "src/main/java/com/examly/springapp/service/BookingService.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	
	@Test
	@Order(16)
	public void backend_day22_testExceptionFile() {
		 
		String filePath = "src/main/java/com/examly/springapp/exception/InsufficientSeatCountException.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
	@Test
	@Order(17)
 	public void backend_day24_testcontrollerBookingFile() {
 
		String filePath = "src/main/java/com/examly/springapp/controller/BookingController.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	

	//CRUD Booking api


    
    @Test
	@Order(19)
 	public void backend_day25_testEntityUserFile() {
 
		String filePath = "src/main/java/com/examly/springapp/entity/User.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(20)
 	public void backend_day26_testCorsUserFile() {
 
		String filePath = "src/main/java/com/examly/springapp/configuration/CrosConfig.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
    
	
}
