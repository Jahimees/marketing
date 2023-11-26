package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Swot;
import by.bsuir.marketing.repository.SwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwotDataService implements DataService<Swot> {

    private final SwotRepository swotRepository;

    @Autowired
    public SwotDataService(SwotRepository swotRepository) {
        this.swotRepository = swotRepository;
    }

    public List<Swot> getAllSwots() {
        return swotRepository.findAll();
    }

    public Swot getSwotById(int id) {
        return swotRepository.findById(id).orElse(null);
    }

    public Swot createSwot(Swot swot) {
        return swotRepository.save(swot);
    }

    public Swot updateSwot(int id, Swot swot) {
        Swot existingSwot = swotRepository.findById(id).orElse(null);
        if (existingSwot != null) {
            // Update the properties of the existing Swot object
            // based on the provided Swot object
            // For example:
            // existingSwot.setProperty1(swot.getProperty1());
            // existingSwot.setProperty2(swot.getProperty2());
            // ...
            return swotRepository.save(existingSwot);
        }
        return null;
    }

    public void deleteSwot(int id) {
        swotRepository.deleteById(id);
    }
}