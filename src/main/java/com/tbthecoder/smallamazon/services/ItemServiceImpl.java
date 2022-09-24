package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.ItemDTO;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.dtos.Status;
import com.tbthecoder.smallamazon.exceptions.ItemNotFoundException;
import com.tbthecoder.smallamazon.models.Item;
import com.tbthecoder.smallamazon.repositories.ItemRepository;
import com.tbthecoder.smallamazon.services.interfaces.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Item saveItem(ItemDTO itemDTO) {

        return itemRepository.save(Item.builder()
                .product(itemDTO.product())
                .amountTotal(itemDTO.amountTotal())
                .qtyOrdered(itemDTO.quantity())
                .build());
    }

    @Override
    public Item getItem(String id) throws ItemNotFoundException {
        return itemRepository.findById(id).orElseThrow( ()-> new ItemNotFoundException("item not found"));
    }



    @Override
    public Response deleteItem(String id) {
        itemRepository.deleteById(id);
        return new Response(Status.SUCCESS, "Item deleted successfully");
    }

    @Override
    public Response deleteAllItems() {
        itemRepository.deleteAll();
        return new Response(Status.SUCCESS, "All items deleted successfully");
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
