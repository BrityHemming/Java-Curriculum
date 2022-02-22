package learn.foraging.data;

import learn.foraging.models.Category;
import learn.foraging.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

class ItemFileRepositoryTest {

    static final String SEED_PATH = "./data/items-seed.txt";
    static final String TEST_PATH = "./data/items-test.txt";
    static final int NEXT_ID = 27;

    ItemFileRepository repository = new ItemFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() {
        assertTrue(repository.findAll().size() == NEXT_ID - 1);
    }

    @Test
    void shouldFindPapaw() {
        Item papaw = repository.findById(6);
        assertNotNull(papaw);
        assertEquals("Papaw", papaw.getName());
        assertEquals(Category.EDIBLE, papaw.getCategory());
        assertEquals(new BigDecimal("9.99"), papaw.getDollarPerKilogram());
    }

    @Test
    void shouldAdd() throws DataException {

        Item expected = new Item();
        expected.setName("Catalpa");
        expected.setCategory(Category.INEDIBLE);
        expected.setDollarPerKilogram(BigDecimal.ZERO);
        expected.setId(NEXT_ID);

        Item item = new Item();
        item.setName("Catalpa");
        item.setCategory(Category.INEDIBLE);
        item.setDollarPerKilogram(BigDecimal.ZERO);

        Item actual = repository.add(item);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateNewFile() throws DataException {
        String path = "./data/items-new.txt";
        File file = new File(path);
        file.delete();

        ItemFileRepository repository = new ItemFileRepository(path);
        Item item = new Item();
        item.setName("Catalpa");
        item.setCategory(Category.INEDIBLE);
        item.setDollarPerKilogram(BigDecimal.ZERO);
        item = repository.add(item);

        assertEquals(1, item.getId());
        assertEquals(1, repository.findAll().size());
    }


}