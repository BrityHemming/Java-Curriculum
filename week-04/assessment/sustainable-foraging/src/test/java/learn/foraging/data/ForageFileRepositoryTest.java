package learn.foraging.data;

import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForageFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/forage-seed-2020-06-26.csv";
    static final String TEST_FILE_PATH = "./data/forage_data_test/2020-06-26.csv";
    static final String TEST_DIR_PATH = "./data/forage_data_test";
    static final int FORAGE_COUNT = 54;

    final LocalDate date = LocalDate.of(2020, 6, 26);

    ForageFileRepository repository = new ForageFileRepository(TEST_DIR_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindByDate() {
        List<Forage> forages = repository.findByDate(date);
        assertEquals(FORAGE_COUNT, forages.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Forage forage = new Forage();
        forage.setDate(date);
        forage.setKilograms(0.75);

        Item item = new Item();
        item.setId(12);
        forage.setItem(item);

        Forager forager = new Forager();
        forager.setId("AAAA-1111-2222-FFFF");
        forage.setForager(forager);

        forage = repository.add(forage);

        assertEquals(36, forage.getId().length());
    }

}