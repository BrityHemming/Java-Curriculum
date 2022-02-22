package learn.foraging.data;

import learn.foraging.models.Forage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForageRepositoryDouble implements ForageRepository {

    final LocalDate date = LocalDate.of(2020, 6, 26);

    private final ArrayList<Forage> forages = new ArrayList<>();

    public ForageRepositoryDouble() {
        Forage forage = new Forage();
        forage.setId("498604db-b6d6-4599-a503-3d8190fda823");
        forage.setDate(date);
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(ItemRepositoryDouble.ITEM);
        forage.setKilograms(1.25);
        forages.add(forage);
    }

    @Override
    public List<Forage> findByDate(LocalDate date) {
        return forages.stream()
                .filter(i -> i.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Forage add(Forage forage) throws DataException {
        forage.setId(java.util.UUID.randomUUID().toString());
        forages.add(forage);
        return forage;
    }

    @Override
    public boolean update(Forage forage) throws DataException {
        return false;
    }
}
