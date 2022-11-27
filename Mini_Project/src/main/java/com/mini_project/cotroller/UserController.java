package com.mini_project.cotroller;

import com.mini_project.model.Items;
import com.mini_project.service.ItemsService;
import com.mini_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("v1/mini")
public class UserController {

//    view profile
//    update mobile no
//    delete address
//    view order
//    cancel orders
//    update orders
//    make order
//    make payment

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public ResponseEntity<List<Items>> getAllItems(  ){

        List<Items> ls = itemsService.getItemAllItems();
        return new ResponseEntity<>( ls , HttpStatus.OK );

    }

    public ResponseEntity< List<Items> > getItemsByCategory( @NotNull String category ){

        return new ResponseEntity<>( itemsService.searchItemsByCategory( category ) ,HttpStatus.OK );

    }

    @GetMapping("/items/{price}")
    public ResponseEntity<List<Items>> getItemByPrice(@PathVariable("price") Integer itemprice){

       List<Items> itemsList =  itemsService.searchItemsByPrice(itemprice);

       return new ResponseEntity<List<Items>>(itemsList, HttpStatus.ACCEPTED);

    }

    @GetMapping("/items/range/{low}/{high}")
    public ResponseEntity<List<Items>> itemsInRange(@PathVariable("low") Integer low, @PathVariable("hight") Integer high){

        return new ResponseEntity<List<Items>>( itemsService.searchItemsInPriceRange(low, high), HttpStatus.OK);

    }



    @GetMapping("/items/{price}")
    public ResponseEntity<List<Items>> itemsLowToHigh(@PathVariable("price") Double price){

        return new ResponseEntity<List<Items>>( itemsService.sortItemsByPriceLowToHigh(price), HttpStatus.OK);

    }

    @GetMapping("/items")
    public ResponseEntity<List<Items>> itemsHighToLow(@RequestParam("price") Double price){

        return new ResponseEntity<List<Items>>( itemsService.sortItemsByPriceHighToLow(price), HttpStatus.OK);

    }







}
