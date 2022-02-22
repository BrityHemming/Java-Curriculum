package learn.foraging.data;

import learn.foraging.models.Category;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemFileRepository implements ItemRepository {

    private static final String HEADER = "id,name,category,dollars/kilogram";
    private final String filePath;

    public ItemFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Item> findAll() {
        ArrayList<Item> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 4) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        return findAll().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Item add(Item item) throws DataException {

        if (item == null) {
            return null;
        }

        List<Item> all = findAll();

        int nextId = all.stream()
                .mapToInt(Item::getId)
                .max()
                .orElse(0) + 1;

        item.setId(nextId);

        all.add(item);
        writeAll(all);

        return item;
    }

    public boolean update(Item item) throws DataException {

        if (item == null) {
            return false;
        }

        List<Item> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (item.getId() == all.get(i).getId()) {
                all.set(i, item);
                writeAll(all);
                return true;
            }
        }

        return false;
    }

    private String serialize(Item item) {
        return String.format("%s,%s,%s,%s",
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getDollarPerKilogram());
    }

    private Item deserialize(String[] fields) {
        Item result = new Item();
        result.setId(Integer.parseInt(fields[0]));
        result.setName(fields[1]);
        result.setCategory(Category.valueOf(fields[2]));
        result.setDollarPerKilogram(new BigDecimal(fields[3]));
        return result;
    }

    protected void writeAll(List<Item> items) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {

            writer.println(HEADER);

            if (items == null) {
                return;
            }

            for (Item item : items) {
                writer.println(serialize(item));
            }

        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }
}
