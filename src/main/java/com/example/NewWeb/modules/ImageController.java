package com.example.NewWeb.modules;

import com.example.NewWeb.Cocktail;
import com.example.NewWeb.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImageController {

    private final ImageService imageService;
    private final CocktailRepository cocktailRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ImageController(ImageService imageService, CocktailRepository cocktailRepository, IngredientRepository ingredientRepository) {
        this.imageService = imageService;
        this.cocktailRepository = cocktailRepository;
        this.ingredientRepository= ingredientRepository;
    }



    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam("drinkid") int drinkid) throws IOException {
        try {
            String imageUrl;
            Cocktail cocktail=cocktailRepository.findBydrinkid(drinkid);
            if (cocktail==null) {
                System.out.println("Cocktail with id " + drinkid + " not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            imageUrl=cocktail.getdrinkthb();
            byte[] imageBytes = imageService.downloadImage(imageUrl);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (NumberFormatException e) {
            System.err.println("Invalid idDrink format: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Bad Request 응답
        } catch (Exception e) {
            System.err.println("An exception occurred: " + e.getMessage());
            e.printStackTrace(); // 스택 트레이스 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 오류 응답
        }
    }
    @GetMapping("/imageig")
    public ResponseEntity<byte[]> getImageig(@RequestParam("igid") int igid) throws IOException {
        try {
            String imageUrl;
            Ingredient ingredient=ingredientRepository.findFirstByid(igid);
            System.out.println(igid);
            if (ingredient==null) {
                System.out.println("Cocktail with id " + igid + " not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            imageUrl="https://www.thecocktaildb.com/images/ingredients/"+ingredient.getigEn()+".png";
            byte[] imageBytes = imageService.downloadImage(imageUrl);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (NumberFormatException e) {
            System.err.println("Invalid idDrink format: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Bad Request 응답
        } catch (Exception e) {
            System.err.println("An exception occurred: " + e.getMessage());
            e.printStackTrace(); // 스택 트레이스 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 오류 응답
        }
    }
}
