package prac.sbt.dollarreport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface QuoteRepo extends CrudRepository<Quote, Long> {
    Quote findByDate(LocalDate date);
}
