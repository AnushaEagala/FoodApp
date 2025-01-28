package com.example.resto.cart.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/foods")
public class FoodController {
    List<Map<String, Object>> foods = new ArrayList<>();



    @GetMapping("/hello")
    public String Hello() {
        return "Hello";
    }

    @GetMapping
    public List<Map<String, Object>> getFoods() {
        List<Map<String, Object>> foods = new ArrayList<>();

        Map<String, Object> foodMap1 = new HashMap<>();
        foodMap1.put("id", 1);
        foodMap1.put("name", "Pizza Pepperoni");
        foodMap1.put("price", 10);
        foodMap1.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap1.put("favorite", false);
        foodMap1.put("stars", 4.5);
        foodMap1.put("imageUrl", "../../../assets/images/foods/food-1.jpg");
        foodMap1.put("origins", new String[]{"Italy"});
        foodMap1.put("cookTime", "10-20");

        Map<String, Object> foodMap2 = new HashMap<>();
        foodMap2.put("id", 2);
        foodMap2.put("name", "Meatball");
        foodMap2.put("price", 20);
        foodMap2.put("tags", new String[]{"SlowFood", "Lunch"});
        foodMap2.put("favorite", true);
        foodMap2.put("stars", 4.7);
        foodMap2.put("imageUrl", "../../../assets/images/foods/food-2.jpg");
        foodMap2.put("origins", new String[]{"persia", "middle east", "china"});
        foodMap2.put("cookTime", "20-30");

        Map<String, Object> foodMap3 = new HashMap<>();
        foodMap3.put("id", 3);
        foodMap3.put("name", "Hamburger");
        foodMap3.put("price", 5);
        foodMap3.put("tags", new String[]{"FastFood", "Hamburger"});
        foodMap3.put("favorite", false);
        foodMap3.put("stars", 3.5);
        foodMap3.put("imageUrl", "../../../assets/images/foods/food-3.jpg");
        foodMap3.put("origins", new String[]{"germany", "us"});
        foodMap3.put("cookTime", "10-15");

        Map<String, Object> foodMap4 = new HashMap<>();
        foodMap4.put("id", 4);
        foodMap4.put("name", "Fried Potatoes");
        foodMap4.put("price", 2);
        foodMap4.put("tags", new String[]{"FastFood", "Fry"});
        foodMap4.put("favorite", true);
        foodMap4.put("stars", 3.3);
        foodMap4.put("imageUrl", "../../../assets/images/foods/food-4.jpg");
        foodMap4.put("origins", new String[]{"belgium", "france"});
        foodMap4.put("cookTime", "15-20");

        Map<String, Object> foodMap5 = new HashMap<>();
        foodMap5.put("id", 5);
        foodMap5.put("name", "Chicken Soup");
        foodMap5.put("price", 11);
        foodMap5.put("tags", new String[]{"SlowFood", "Soup"});
        foodMap5.put("favorite", false);
        foodMap5.put("stars", 3.0);
        foodMap5.put("imageUrl", "../../../assets/images/foods/food-5.jpg");
        foodMap5.put("origins", new String[]{"india", "asia"});
        foodMap5.put("cookTime", "40-50");

        Map<String, Object> foodMap6 = new HashMap<>();
        foodMap6.put("id", 6);
        foodMap6.put("name", "Vegetables Pizza");
        foodMap6.put("price", 9);
        foodMap6.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap6.put("favorite", false);
        foodMap6.put("stars", 4.0);
        foodMap6.put("imageUrl", "../../../assets/images/foods/food-6.jpg");
        foodMap6.put("origins", new String[]{"Italy"});
        foodMap6.put("cookTime", "40-50");

        foods.add(foodMap1);
        foods.add(foodMap2);
        foods.add(foodMap3);
        foods.add(foodMap4);
        foods.add(foodMap5);
        foods.add(foodMap6);

        return foods;
    }

    @GetMapping("/search/{searchTerm}")
    public List<Map<String, Object>> searchFoods(@PathVariable("searchTerm") String searchTerm) {
        List<Map<String, Object>> foods = new ArrayList<>();

        Map<String, Object> foodMap1 = new HashMap<>();
        foodMap1.put("id", 1);
        foodMap1.put("name", "Pizza Pepperoni");
        foodMap1.put("price", 10);
        foodMap1.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap1.put("favorite", false);
        foodMap1.put("stars", 4.5);
        foodMap1.put("imageUrl", "../../../assets/images/foods/food-1.jpg");
        foodMap1.put("origins", new String[]{"Italy"});
        foodMap1.put("cookTime", "10-20");

        Map<String, Object> foodMap2 = new HashMap<>();
        foodMap2.put("id", 2);
        foodMap2.put("name", "Meatball");
        foodMap2.put("price", 20);
        foodMap2.put("tags", new String[]{"SlowFood", "Lunch"});
        foodMap2.put("favorite", true);
        foodMap2.put("stars", 4.7);
        foodMap2.put("imageUrl", "../../../assets/images/foods/food-2.jpg");
        foodMap2.put("origins", new String[]{"persia", "middle east", "china"});
        foodMap2.put("cookTime", "20-30");

        Map<String, Object> foodMap3 = new HashMap<>();
        foodMap3.put("id", 3);
        foodMap3.put("name", "Hamburger");
        foodMap3.put("price", 5);
        foodMap3.put("tags", new String[]{"FastFood", "Hamburger"});
        foodMap3.put("favorite", false);
        foodMap3.put("stars", 3.5);
        foodMap3.put("imageUrl", "../../../assets/images/foods/food-3.jpg");
        foodMap3.put("origins", new String[]{"germany", "us"});
        foodMap3.put("cookTime", "10-15");

        Map<String, Object> foodMap4 = new HashMap<>();
        foodMap4.put("id", 4);
        foodMap4.put("name", "Fried Potatoes");
        foodMap4.put("price", 2);
        foodMap4.put("tags", new String[]{"FastFood", "Fry"});
        foodMap4.put("favorite", true);
        foodMap4.put("stars", 3.3);
        foodMap4.put("imageUrl", "../../../assets/images/foods/food-4.jpg");
        foodMap4.put("origins", new String[]{"belgium", "france"});
        foodMap4.put("cookTime", "15-20");

        Map<String, Object> foodMap5 = new HashMap<>();
        foodMap5.put("id", 5);
        foodMap5.put("name", "Chicken Soup");
        foodMap5.put("price", 11);
        foodMap5.put("tags", new String[]{"SlowFood", "Soup"});
        foodMap5.put("favorite", false);
        foodMap5.put("stars", 3.0);
        foodMap5.put("imageUrl", "../../../assets/images/foods/food-5.jpg");
        foodMap5.put("origins", new String[]{"india", "asia"});
        foodMap5.put("cookTime", "40-50");

        Map<String, Object> foodMap6 = new HashMap<>();
        foodMap6.put("id", 6);
        foodMap6.put("name", "Vegetables Pizza");
        foodMap6.put("price", 9);
        foodMap6.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap6.put("favorite", false);
        foodMap6.put("stars", 4.0);
        foodMap6.put("imageUrl", "../../../assets/images/foods/food-6.jpg");
        foodMap6.put("origins", new String[]{"Italy"});
        foodMap6.put("cookTime", "40-50");

        foods.add(foodMap1);
        foods.add(foodMap2);
        foods.add(foodMap3);
        foods.add(foodMap4);
        foods.add(foodMap5);
        foods.add(foodMap6);

        String newSearchTerm = searchTerm.toLowerCase();

        List<Map<String, Object>> targetFoods = new ArrayList<>();
        for (Map<String, Object> foodMap : foods) {
            if(((String)
            foodMap.get("name")).toLowerCase().contains(newSearchTerm)) {
                targetFoods.add(foodMap);
            }
        }
        return targetFoods;
    }

    @GetMapping("/tags")
    public List<Map<String, Object>> getTags() {
        List<Map<String, Object>> tags = new ArrayList<>();
        Map<String, Object> tagMap1 = new HashMap<>();
        tagMap1.put("name", "All");
        tagMap1.put("count", 14);
        tags.add(tagMap1);

        Map<String, Object> tagMap2 = new HashMap<>();
        tagMap2.put("name", "FastFood");
        tagMap2.put("count", 4);
        tags.add(tagMap2);

        Map<String, Object> tagMap3 = new HashMap<>();
        tagMap3.put("name", "Pizza");
        tagMap3.put("count", 2);
        tags.add(tagMap3);

        Map<String, Object> tagMap4 = new HashMap<>();
        tagMap4.put("name", "Lunch");
        tagMap4.put("count", 3);
        tags.add(tagMap4);

        Map<String, Object> tagMap5 = new HashMap<>();
        tagMap5.put("name", "SlowFood");
        tagMap5.put("count", 2);
        tags.add(tagMap5);

        Map<String, Object> tagMap6 = new HashMap<>();
        tagMap6.put("name", "Hamburger");
        tagMap6.put("count", 1);
        tags.add(tagMap6);

        return tags;
    }

    @GetMapping("/tag/{tagName}")
    public List<Map<String, Object>> getFoodsByTag(@PathVariable("tagName") String tagName) {
        List<Map<String, Object>> foods = new ArrayList<>();

        Map<String, Object> foodMap1 = new HashMap<>();
        foodMap1.put("id", 1);
        foodMap1.put("name", "Pizza Pepperoni");
        foodMap1.put("price", 10);
        foodMap1.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap1.put("favorite", false);
        foodMap1.put("stars", 4.5);
        foodMap1.put("imageUrl", "../../../assets/images/foods/food-1.jpg");
        foodMap1.put("origins", new String[]{"Italy"});
        foodMap1.put("cookTime", "10-20");

        Map<String, Object> foodMap2 = new HashMap<>();
        foodMap2.put("id", 2);
        foodMap2.put("name", "Meatball");
        foodMap2.put("price", 20);
        foodMap2.put("tags", new String[]{"SlowFood", "Lunch"});
        foodMap2.put("favorite", true);
        foodMap2.put("stars", 4.7);
        foodMap2.put("imageUrl", "../../../assets/images/foods/food-2.jpg");
        foodMap2.put("origins", new String[]{"persia", "middle east", "china"});
        foodMap2.put("cookTime", "20-30");

        Map<String, Object> foodMap3 = new HashMap<>();
        foodMap3.put("id", 3);
        foodMap3.put("name", "Hamburger");
        foodMap3.put("price", 5);
        foodMap3.put("tags", new String[]{"FastFood", "Hamburger"});
        foodMap3.put("favorite", false);
        foodMap3.put("stars", 3.5);
        foodMap3.put("imageUrl", "../../../assets/images/foods/food-3.jpg");
        foodMap3.put("origins", new String[]{"germany", "us"});
        foodMap3.put("cookTime", "10-15");

        Map<String, Object> foodMap4 = new HashMap<>();
        foodMap4.put("id", 4);
        foodMap4.put("name", "Fried Potatoes");
        foodMap4.put("price", 2);
        foodMap4.put("tags", new String[]{"FastFood", "Fry"});
        foodMap4.put("favorite", true);
        foodMap4.put("stars", 3.3);
        foodMap4.put("imageUrl", "../../../assets/images/foods/food-4.jpg");
        foodMap4.put("origins", new String[]{"belgium", "france"});
        foodMap4.put("cookTime", "15-20");

        Map<String, Object> foodMap5 = new HashMap<>();
        foodMap5.put("id", 5);
        foodMap5.put("name", "Chicken Soup");
        foodMap5.put("price", 11);
        foodMap5.put("tags", new String[]{"SlowFood", "Soup"});
        foodMap5.put("favorite", false);
        foodMap5.put("stars", 3.0);
        foodMap5.put("imageUrl", "../../../assets/images/foods/food-5.jpg");
        foodMap5.put("origins", new String[]{"india", "asia"});
        foodMap5.put("cookTime", "40-50");

        Map<String, Object> foodMap6 = new HashMap<>();
        foodMap6.put("id", 6);
        foodMap6.put("name", "Vegetables Pizza");
        foodMap6.put("price", 9);
        foodMap6.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap6.put("favorite", false);
        foodMap6.put("stars", 4.0);
        foodMap6.put("imageUrl", "../../../assets/images/foods/food-6.jpg");
        foodMap6.put("origins", new String[]{"Italy"});
        foodMap6.put("cookTime", "40-50");

        foods.add(foodMap1);
        foods.add(foodMap2);
        foods.add(foodMap3);
        foods.add(foodMap4);
        foods.add(foodMap5);
        foods.add(foodMap6);


        List<Map<String, Object>> targetFoods = new ArrayList<>();
        for (Map<String, Object> foodMap : foods) {
            if(Arrays.asList((String[]) foodMap.get("tags")).contains(tagName)) {
                targetFoods.add(foodMap);
            }
        }
        return targetFoods;
    }

    @GetMapping("/{foodId}")
    public Map<String, Object> getFoodById(@PathVariable("foodId") int foodId) {
        List<Map<String, Object>> foods = new ArrayList<>();

        Map<String, Object> foodMap1 = new HashMap<>();
        foodMap1.put("id", 1);
        foodMap1.put("name", "Pizza Pepperoni");
        foodMap1.put("price", 10);
        foodMap1.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap1.put("favorite", false);
        foodMap1.put("stars", 4.5);
        foodMap1.put("imageUrl", "../../../assets/images/foods/food-1.jpg");
        foodMap1.put("origins", new String[]{"Italy"});
        foodMap1.put("cookTime", "10-20");

        Map<String, Object> foodMap2 = new HashMap<>();
        foodMap2.put("id", 2);
        foodMap2.put("name", "Meatball");
        foodMap2.put("price", 20);
        foodMap2.put("tags", new String[]{"SlowFood", "Lunch"});
        foodMap2.put("favorite", true);
        foodMap2.put("stars", 4.7);
        foodMap2.put("imageUrl", "../../../assets/images/foods/food-2.jpg");
        foodMap2.put("origins", new String[]{"persia", "middle east", "china"});
        foodMap2.put("cookTime", "20-30");

        Map<String, Object> foodMap3 = new HashMap<>();
        foodMap3.put("id", 3);
        foodMap3.put("name", "Hamburger");
        foodMap3.put("price", 5);
        foodMap3.put("tags", new String[]{"FastFood", "Hamburger"});
        foodMap3.put("favorite", false);
        foodMap3.put("stars", 3.5);
        foodMap3.put("imageUrl", "../../../assets/images/foods/food-3.jpg");
        foodMap3.put("origins", new String[]{"germany", "us"});
        foodMap3.put("cookTime", "10-15");

        Map<String, Object> foodMap4 = new HashMap<>();
        foodMap4.put("id", 4);
        foodMap4.put("name", "Fried Potatoes");
        foodMap4.put("price", 2);
        foodMap4.put("tags", new String[]{"FastFood", "Fry"});
        foodMap4.put("favorite", true);
        foodMap4.put("stars", 3.3);
        foodMap4.put("imageUrl", "../../../assets/images/foods/food-4.jpg");
        foodMap4.put("origins", new String[]{"belgium", "france"});
        foodMap4.put("cookTime", "15-20");

        Map<String, Object> foodMap5 = new HashMap<>();
        foodMap5.put("id", 5);
        foodMap5.put("name", "Chicken Soup");
        foodMap5.put("price", 11);
        foodMap5.put("tags", new String[]{"SlowFood", "Soup"});
        foodMap5.put("favorite", false);
        foodMap5.put("stars", 3.0);
        foodMap5.put("imageUrl", "../../../assets/images/foods/food-5.jpg");
        foodMap5.put("origins", new String[]{"india", "asia"});
        foodMap5.put("cookTime", "40-50");

        Map<String, Object> foodMap6 = new HashMap<>();
        foodMap6.put("id", 6);
        foodMap6.put("name", "Vegetables Pizza");
        foodMap6.put("price", 9);
        foodMap6.put("tags", new String[]{"FastFood", "Pizza", "Lunch"});
        foodMap6.put("favorite", false);
        foodMap6.put("stars", 4.0);
        foodMap6.put("imageUrl", "../../../assets/images/foods/food-6.jpg");
        foodMap6.put("origins", new String[]{"Italy"});
        foodMap6.put("cookTime", "40-50");

        foods.add(foodMap1);
        foods.add(foodMap2);
        foods.add(foodMap3);
        foods.add(foodMap4);
        foods.add(foodMap5);
        foods.add(foodMap6);

        Map<String, Object> targetFoodMap = new HashMap<>();
        for (Map<String, Object> foodMap : foods) {
            if(((Integer)(foodMap.get("id"))) == (foodId)) {
                targetFoodMap = foodMap;
            }
        }
        return targetFoodMap;
    }




}
