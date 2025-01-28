package com.example.resto.Menu;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> getMenus() {
        System.out.println("Input get menu");
        List<Menu> menu = menuService.getMenu();
        return menu;
    }

    @PostMapping("/create")
    public Menu addMenu(@RequestBody Menu menu) {
        System.out.println("Input for add" + menu.toString());
        BigDecimal price = new BigDecimal(String.valueOf(menu.getPrice()));
        Long restaurantId = Long.parseLong(String.valueOf(menu.getResturantId()));
        menu.setPrice(price);
        menu.setResturantId(restaurantId);
        System.out.println("Input for add 2" + menu.toString());

        return menuService.addMenu(menu);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long menuid) {
        System.out.println("Input for delete: " + menuid);
        menuService.deleteMenuById(menuid);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public void updateMenu(@RequestBody Menu menu) {
        try {
            System.out.println("Input for update" + menu);
            menuService.updateMenu(menu);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/{restaurantId}")
    public Optional<List<Menu>> findByResturantId(@PathVariable("restaurantId") String restaurantId) {
        System.out.println("Input get menu by restaurantId" + restaurantId);
        Long restaurantIdInput = Long.parseLong(String.valueOf(restaurantId));

        return menuService.findByResturantId(restaurantIdInput);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadMenuImage(@RequestPart(name = "file", required = false) MultipartFile file, @PathVariable Long id) {
        menuService.uploadMenuImage(file, id);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<String> getMenuImage(@PathVariable Long id) {
        String imagePath = menuService.getMenuImage(id);
        return ResponseEntity.ok(imagePath);
    }
}