package com.example.resto.Menu;
import com.example.resto.category.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(generator="my_seq_menu")
    @SequenceGenerator(name="my_seq_menu", sequenceName="menus_menu_id_seq", allocationSize=1)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "restaurant_id")
    private Long resturantId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // getters and setters

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getResturantId() {
        return resturantId;
    }

    public void setResturantId(Long resturantId) {
        this.resturantId = resturantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", resturantId=" + resturantId +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }
}
