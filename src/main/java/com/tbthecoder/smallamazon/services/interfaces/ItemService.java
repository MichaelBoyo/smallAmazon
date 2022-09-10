package com.tbthecoder.smallamazon.services.interfaces;

import com.tbthecoder.smallamazon.dtos.ItemDTO;
import com.tbthecoder.smallamazon.dtos.OrderRequest;
import com.tbthecoder.smallamazon.models.Item;

public interface ItemService {
    Item saveItem(ItemDTO itemDTO);

}
