package com.assessment.coin_tracker.enums;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CRYPTO {
    BTC("BTC"),
    ETH("ETH"),
    USDT("USDT"),
    BNB("BNB"),
    SOL("SOL");

    private final String symbol;

    CRYPTO(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static List<String> getAllSymbols() {
        return Arrays.stream(CRYPTO.values())
                .map(CRYPTO::getSymbol)
                .collect(Collectors.toList());
    }

    public static CRYPTO convertToCrypto(String symbol) {
        for (CRYPTO crypto : CRYPTO.values()) {
            if (crypto.getSymbol().equals(symbol)) {
                return crypto;
            }
        }
        throw new IllegalArgumentException("Unknown cryptocurrency symbol: " + symbol);
    }
}

