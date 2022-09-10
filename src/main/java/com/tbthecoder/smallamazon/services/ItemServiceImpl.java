package com.tbthecoder.smallamazon.services;

import com.tbthecoder.smallamazon.dtos.ItemDTO;
import com.tbthecoder.smallamazon.dtos.OrderRequest;
import com.tbthecoder.smallamazon.models.Item;
import com.tbthecoder.smallamazon.repositories.ItemRepository;
import com.tbthecoder.smallamazon.services.interfaces.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Item saveItem(ItemDTO itemDTO) {
        return itemRepository.save(Item.builder()
                .product(itemDTO.product())
                .amountTotal(itemDTO.amountTotal())
                .quantity(itemDTO.quantity())
                .build());
    }
}
