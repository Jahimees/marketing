package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.repository.BlankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlankDataService implements DataService<Blank> {

    private final BlankRepository blankRepository;

    public List<Blank> getAllBlanks() {
        return blankRepository.findAll();
    }

    public Optional<Blank> getBlankById(int id) {
        return blankRepository.findById(id);
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