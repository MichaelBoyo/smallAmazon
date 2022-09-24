package com.tbthecoder.smallamazon.services.interfaces;

import com.tbthecoder.smallamazon.dtos.ItemDTO;
import com.tbthecoder.smallamazon.dtos.OrderRequest;
import com.tbthecoder.smallamazon.dtos.Response;
import com.tbthecoder.smallamazon.exceptions.ItemNotFoundException;
import com.tbthecoder.smallamazon.models.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(ItemDTO itemDTO);
    Item getItem(String id) throws ItemNotFoundException;
    Response deleteItem(String id);
    Response deleteAllItems();

    List<Item> getAllItems();
}
