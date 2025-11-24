package cz.klokocnik.exchange_rates.repository;

import cz.klokocnik.exchange_rates.entity.ExchangeRate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("""
        SELECT e
        FROM ExchangeRate e
        WHERE e.id = (
            SELECT MAX(e2.id)
            FROM ExchangeRate e2
            WHERE e2.shortName = e.shortName AND e2.name = e.name
        )
    """)
    List<ExchangeRate> findMostRecentExchangeRates();
}
