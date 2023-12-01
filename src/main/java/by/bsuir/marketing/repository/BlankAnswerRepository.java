package by.bsuir.marketing.repository;

import by.bsuir.marketing.model.Blank;
import by.bsuir.marketing.model.BlankAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlankAnswerRepository extends JpaRepository<BlankAnswer, Integer> {

    boolean existsByIpAddressAndBlank(String ipAddress, Blank blank);

    List<BlankAnswer> findAllByBlank(Blank blank);
}
