package com.example.resto.Restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;  // Lombok annotation to generate getters, setters, equals, hashCode and toString methods

@Data  // This replaces @Getter, @Setter, and can include other features
@Entity(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq_gen")
    @SequenceGenerator(name = "restaurant_seq_gen", sequenceName = "restaurants_restaurant_id_seq", allocationSize = 1)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "photo_path")
    private String photoPath;

}
