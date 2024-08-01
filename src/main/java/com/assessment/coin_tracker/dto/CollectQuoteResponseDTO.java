package com.assessment.coin_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectQuoteResponseDTO {

    @JsonProperty("status")
    private Status status;

    @JsonProperty("data")
    private Map<String, Cryptocurrency> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Status {
        @JsonProperty("timestamp")
        private String timestamp;

        @JsonProperty("error_code")
        private int errorCode;

        @JsonProperty("error_message")
        private String errorMessage;

        @JsonProperty("elapsed")
        private int elapsed;

        @JsonProperty("credit_count")
        private int creditCount;

        @JsonProperty("notice")
        private String notice;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cryptocurrency {
        @JsonProperty("id")
        private int id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("symbol")
        private String symbol;

        @JsonProperty("slug")
        private String slug;

        @JsonProperty("num_market_pairs")
        private int numMarketPairs;

        @JsonProperty("date_added")
        private String dateAdded;

        @JsonProperty("tags")
        private List<String> tags;

        @JsonProperty("max_supply")
        private Double maxSupply;

        @JsonProperty("circulating_supply")
        private double circulatingSupply;

        @JsonProperty("total_supply")
        private double totalSupply;

        @JsonProperty("is_active")
        private int isActive;

        @JsonProperty("infinite_supply")
        private boolean infiniteSupply;

        @JsonProperty("platform")
        private Platform platform;

        @JsonProperty("cmc_rank")
        private int cmcRank;

        @JsonProperty("is_fiat")
        private int isFiat;

        @JsonProperty("self_reported_circulating_supply")
        private Double selfReportedCirculatingSupply;

        @JsonProperty("self_reported_market_cap")
        private Double selfReportedMarketCap;

        @JsonProperty("tvl_ratio")
        private Double tvlRatio;

        @JsonProperty("last_updated")
        private String lastUpdated;

        @JsonProperty("quote")
        private Quote quote;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Platform {
            @JsonProperty("id")
            private int id;

            @JsonProperty("name")
            private String name;

            @JsonProperty("symbol")
            private String symbol;

            @JsonProperty("slug")
            private String slug;

            @JsonProperty("token_address")
            private String tokenAddress;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Quote {
            @JsonProperty("USD")
            private USD usd;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class USD {
                @JsonProperty("price")
                private double price;

                @JsonProperty("volume_24h")
                private double volume24h;

                @JsonProperty("volume_change_24h")
                private double volumeChange24h;

                @JsonProperty("percent_change_1h")
                private double percentChange1h;

                @JsonProperty("percent_change_24h")
                private double percentChange24h;

                @JsonProperty("percent_change_7d")
                private double percentChange7d;

                @JsonProperty("percent_change_30d")
                private double percentChange30d;

                @JsonProperty("percent_change_60d")
                private double percentChange60d;

                @JsonProperty("percent_change_90d")
                private double percentChange90d;

                @JsonProperty("market_cap")
                private double marketCap;

                @JsonProperty("market_cap_dominance")
                private double marketCapDominance;

                @JsonProperty("fully_diluted_market_cap")
                private double fullyDilutedMarketCap;

                @JsonProperty("tvl")
                private Double tvl;

                @JsonProperty("last_updated")
                private String lastUpdated;
            }
        }
    }
}

