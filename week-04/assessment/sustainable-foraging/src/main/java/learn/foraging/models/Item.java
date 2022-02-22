package learn.foraging.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private int id;
    private String name;
    private Category category;
    private BigDecimal dollarPerKilogram;

    public Item() {
    }

    public Item(int id, String name, Category category, BigDecimal dollarPerKilogram) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.dollarPerKilogram = dollarPerKilogram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getDollarPerKilogram() {
        return dollarPerKilogram;
    }

    public void setDollarPerKilogram(BigDecimal dollarPerKilogram) {
        this.dollarPerKilogram = dollarPerKilogram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(name, item.name) &&
                category == item.category &&
                Objects.equals(dollarPerKilogram, item.dollarPerKilogram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, dollarPerKilogram);
    }
}
