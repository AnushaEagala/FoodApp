package com.example.resto.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository mrepo;
    private final MenuFileStorageService fileStorageService;

    @Autowired
    public MenuService(MenuRepository mrepo, MenuFileStorageService fileStorageService) {
        this.mrepo = mrepo;
        this.fileStorageService = fileStorageService;
    }

    public List<Menu> getMenu() {
        return mrepo.findAll();
    }

    public Menu addMenu(Menu menu) {
        return mrepo.save(menu);
    }

    public void deleteMenuById(Long menuid) {
        System.out.println("Input for delete in service: " + menuid);
        mrepo.deleteById(menuid);
    }

    public void updateMenu(Menu menu) {
        System.out.println("Input for update in service: " + menu.getMenuId());
        mrepo.save(menu);
    }

    public Optional<List<Menu>> findByResturantId(Long restaurantId) {
        return mrepo.findByResturantId(restaurantId);
    }

    public void uploadMenuImage(MultipartFile file, Long id) {
        Menu menu = mrepo.getReferenceById(id);
        String imagePath = fileStorageService.saveFile(file, id);
        menu.setImage(imagePath);
        mrepo.save(menu);
    }

    public String getMenuImage(Long id) {
        Menu menu = mrepo.getReferenceById(id);
        return menu.getImage();
    }
}
