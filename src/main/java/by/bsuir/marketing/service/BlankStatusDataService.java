package by.bsuir.marketing.service;

import by.bsuir.marketing.model.BlankStatus;
import by.bsuir.marketing.repository.BlankStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlankStatusDataService implements DataService<BlankStatus> {

    private final BlankStatusRepository blankStatusRepository;

    @Autowired
    public BlankStatusDataService(BlankStatusRepository blankStatusRepository) {
        this.blankStatusRepository = blankStatusRepository;
    }

    public List<BlankStatus> getAllBlankStatuses() {
        return blankStatusRepository.findAll();
    }

    public BlankStatus getBlankStatusById(int id) {
        return blankStatusRepository.findById(id).orElse(null);
    }

    public BlankStatus createBlankStatus(BlankStatus blankStatus) {
        return blankStatusRepository.save(blankStatus);
    }

    public BlankStatus updateBlankStatus(int id, BlankStatus blankStatus) {
        BlankStatus existingBlankStatus = blankStatusRepository.findById(id).orElse(null);
        if (existingBlankStatus != null) {
            existingBlankStatus.setName(blankStatus.getName());
            return blankStatusRepository.save(existingBlankStatus);
        } else {
            return null;
        }
    }

    public void deleteBlankStatus(int id) {
        blankStatusRepository.deleteById(id);
    }
}