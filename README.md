# Exchange rates api

## Lokální vývoj
Pro spuštění aplikace je potřeba nastavit systémové proměnné do .env souboru:
.env soubor musí být umístěn v root složce projektu

Pomocí docker compose up nastartujeme dev databázi

**Systémové proměnné pro lokální vývoj**
* EXCHANGE_RATE_API_URL
* EXCHANGE_RATE_API_API_KEY
* SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop
* SPRING_SQL_INIT_MODE=always
* SPRING.JPA.DEFER_DATASOURCE_INITIALIZATION=true

Po spuštění běží api na http://localhost:8080