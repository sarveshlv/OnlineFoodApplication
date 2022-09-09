package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capg.Model.Restaurant;
import com.capg.Repository.RestaurantRepository;
import com.capg.Service.RestaurantService;

@SpringBootTest
class RestaurantApplicationTests {

	@Autowired
	RestaurantService restaurantService;
	
	@MockBean
	RestaurantRepository restaurantRepository;
	
	@Test
	void testGetAllRestDetails() {
		Mockito.when(restaurantRepository.findAll()).thenReturn(Stream.of(
				new Restaurant(1,"T1","Loc1","Name1",424234),
				new Restaurant(2,"T1","Loc1","Name1",424234)).collect(Collectors.toList()));
		assertEquals(2,restaurantService.getAllRestDetails().size());
	}
	 
	@Test
	void testGetRestByRestId() {
		Restaurant restaurant = new Restaurant(1,"T1","Loc1","Name1",424234);
		Mockito.when(restaurantRepository.findByRestId(1)).thenReturn(restaurant);
		assertEquals(restaurant,restaurantService.getRestByRestId(1));
	}
	
	@Test
	void testAddOneRest() {
		Restaurant restaurant = new Restaurant(1,"T1","Loc1","Name1",424234);
		Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
		assertEquals("Restaurant with ID "+restaurant.getRestId()+" has been added.",
				restaurantService.addOneRest(restaurant));
	}
	
	@Test
	void testUpdateRest() {
		Restaurant restaurant = new Restaurant(1,"T1","Loc1","Name1",424234);
		Mockito.when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
		assertEquals(restaurantRepository.save(restaurant),restaurantService.updateRest(restaurant));
	}
	
	@Test
	void testDeleteByRestId() {
		Restaurant restaurant = new Restaurant(1,"T1","Loc1","Name1",424234);
		int restId = restaurant.getRestId();
		assertEquals("Restaurant with ID "+restId+" has been deleted.",
				restaurantService.deleteByRestId(1));
	}
	
}
