package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.repository.BlankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlankDataService implements DataService<Blank> {

    private final BlankRepository blankRepository;

    @Autowired
    public BlankDataService(BlankRepository blankRepository) {
        this.blankRepository = blankRepository;
    }

    public List<Blank> getAllBlanks() {
        return blankRepository.findAll();
    }

    public Blank getBlankById(int id) {
        return blankRepository.findById(id).orElse(null);
    }

    public Blank createBlank(Blank blank) {
        return blankRepository.save(blank);
    }

    public Blank updateBlank(int id, Blank blank) {
        Blank existingBlank = blankRepository.findById(id).orElse(null);
        if (existingBlank != null) {
            existingBlank.setBlankStatus(blank.getBlankStatus());
            existingBlank.setName(blank.getName());
            existingBlank.setCreationDate(blank.getCreationDate());
            existingBlank.setProduct(blank.getProduct());
            existingBlank.setAccount(blank.getAccount());
            return blankRepository.save(existingBlank);
        }
        return null;
    }

    public void deleteBlank(int id) {
        blankRepository.deleteById(id);
    }
}