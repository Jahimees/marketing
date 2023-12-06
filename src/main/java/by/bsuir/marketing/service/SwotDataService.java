package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Account;
import by.bsuir.marketing.model.Swot;
import by.bsuir.marketing.repository.SwotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SwotDataService implements DataService<Swot> {

    private final SwotRepository swotRepository;
    private final AccountDataService accountDataService;

    public List<Swot> getAllSwots() {
        return swotRepository.findAll();
    }

    public Optional<Swot> getSwotById(int id) {
        return swotRepository.findById(id);
    }

    public List<Swot> getAllSwotsByIdAccount(int idAccount) {
        Optional<Account> accountOptional = accountDataService.getAccountById(idAccount);

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("Account not found");
        }

        return swotRepository.findAllByAccount(accountOptional.get());
    }

    public Swot createSwot(Swot swot) {
        Optional<Account> accountOptional = accountDataService.getAccountById(swot.getAccount().getIdAccount());

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("Account not found");
        }

        swot.setAccount(accountOptional.get());
        return swotRepository.saveAndFlush(swot);
    }

    public Swot updateSwot(int id, Swot swot) {
        Swot existingSwot = swotRepository.findById(id).orElse(null);
        if (existingSwot != null) {
            existingSwot.setName(swot.getName());
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