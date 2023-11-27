package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Swot;
import by.bsuir.marketing.repository.SwotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SwotDataService implements DataService<Swot> {

    private final SwotRepository swotRepository;

    public List<Swot> getAllSwots() {
        return swotRepository.findAll();
    }

    public Optional<Swot> getSwotById(int id) {
        return swotRepository.findById(id);
    }

    public Swot createSwot(Swot swot) {
        return swotRepository.save(swot);
    }

    public Swot updateSwot(int id, Swot swot) {
        Swot existingSwot = swotRepository.findById(id).orElse(null);
        if (existingSwot != null) {
            existingSwot.setStrength(swot.getStrength());
            existingSwot.setWeakness(swot.getWeakness());
            existingSwot.setOpportunity(swot.getOpportunity());
            existingSwot.setThreat(swot.getThreat());
            return swotRepository.save(existingSwot);
        }
        return null;
    }

    public void deleteSwot(int id) {
        swotRepository.deleteById(id);
    }
}