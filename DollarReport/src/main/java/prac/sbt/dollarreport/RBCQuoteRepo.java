package prac.sbt.dollarreport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RBCQuoteRepo extends CrudRepository<RBCQuote, Long> {
    RBCQuote findByDate(LocalDate date);
}
