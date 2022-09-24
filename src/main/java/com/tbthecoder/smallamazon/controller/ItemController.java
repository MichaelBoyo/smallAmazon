package com.tbthecoder.smallamazon.controller;

import com.tbthecoder.smallamazon.dtos.ItemDTO;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.exceptions.ItemNotFoundException;
import com.tbthecoder.smallamazon.models.Item;
import com.tbthecoder.smallamazon.services.interfaces.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public Item addItem(ItemDTO item) {
        return itemService.saveItem(item);
    }
    @GetMapping
    public Item getItem(String id) throws ItemNotFoundException {
        return itemService.getItem(id);
    }
    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
    @DeleteMapping
    public Response deleteItem(String id) {
        return itemService.deleteItem(id);
    }
    @DeleteMapping("/all")
    public Response deleteAllItems() {
        return itemService.deleteAllItems();
    }

}
