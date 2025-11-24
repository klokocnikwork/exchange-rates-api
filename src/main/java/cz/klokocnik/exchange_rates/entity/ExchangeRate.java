package cz.klokocnik.exchange_rates.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_exchange_rate")
    @SequenceGenerator(name = "seq_exchange_rate", sequenceName = "exchange_rate_id_seq", allocationSize = 1)
    private Long id;
    private String shortName;
    private LocalDateTime validFrom;
    private String name;
    private String country;
    private Double move;
    private Double amount;
    private Double valBuy;
    private Double valSell;
    private Double valMid;
    private Double currBuy;
    private Double currSell;
    private Double currMid;
    private Double version;
    private Double cnbMid;
    private Double ecbMid;
}
