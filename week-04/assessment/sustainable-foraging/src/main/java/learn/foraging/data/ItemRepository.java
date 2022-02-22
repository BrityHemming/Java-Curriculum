package learn.foraging.data;

import learn.foraging.models.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAll();

    Item findById(int id);

    Item add(Item item) throws DataException;
}
