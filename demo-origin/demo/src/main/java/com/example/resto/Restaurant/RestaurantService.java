package com.example.resto.Restaurant;

import com.example.resto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepo repo;
    private final RestaurantFileStorageService fileStorageService;

    @Autowired
    public RestaurantService(RestaurantRepo repo, RestaurantFileStorageService fileStorageService) {
        this.repo = repo;
        this.fileStorageService = fileStorageService;
    }

    public List<Restaurant> getRestaurants() {
        return repo.findAll();
    }

    public List<Restaurant> getRestaurantsByOwnerId(int ownerId) {
        return repo.findByOwnerId(ownerId);
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return repo.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        repo.deleteById(id);
    }

    public void updateRestaurant(Restaurant restaurant) {
        repo.save(restaurant);
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return repo.findById(id);
    }

    public String getimage(Long id) {
        Restaurant restaurant = repo.getReferenceById(id);
        return restaurant.getPhotoPath();
    }
}