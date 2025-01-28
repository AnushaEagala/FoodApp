package com.example.resto.Restaurant;

import com.example.resto.user.UpdateUserRequest;
import com.example.resto.user.User;
import com.example.resto.user.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantFileStorageService fileStorageService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantFileStorageService fileStorageService) {
        this.restaurantService = restaurantService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/owner")
    public List<Restaurant> getRestaurantsByOwnerId(Principal connectedUser) {
        // Assuming the Principal contains the user ID, otherwise adjust accordingly
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        // Fetch user details by email and get the owner ID (this requires additional user service/repository)
        int ownerId = user.getId();
        return restaurantService.getRestaurantsByOwnerId(ownerId);
    }


    @PostMapping("/create")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadCoverPicture(@RequestPart(name = "file", required = false) MultipartFile file,
                                                     @PathVariable Long id) {
        String filePath = fileStorageService.saveFile(file, id);
        Restaurant restaurant = restaurantService.getRestaurantById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setPhotoPath(filePath);
        restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok("File uploaded successfully: " + filePath);
    }

    @GetMapping("/image")
    public ResponseEntity<?> getimage(Long id) {
        String img = restaurantService.getimage(id);
        return ResponseEntity.ok(img);
    }
}