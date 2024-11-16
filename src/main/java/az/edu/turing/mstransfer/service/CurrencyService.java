package az.edu.turing.mstransfer.service;

import az.edu.turing.mstransfer.model.response.ExchangeRateResponse;
import az.edu.turing.mstransfer.util.CurrencyRateFetcher;
import az.edu.turing.mstransfer.model.enums.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {

    private final CurrencyRateFetcher currencyRateFetcher;

    public List<ExchangeRateResponse> getExchangeRates() {
        return Arrays.stream(Currency.values())
                .map(currency -> {
                    try {
                        return ExchangeRateResponse.builder()
                                .currencyCode(currency.name())
                                .rate(currencyRateFetcher.getRate(currency))
                                .build();
                    } catch (Exception e) {
                        log.error("Failed to fetch rate for currency: {}", currency.name());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
