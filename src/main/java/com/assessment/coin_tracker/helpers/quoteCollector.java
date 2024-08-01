package com.assessment.coin_tracker.helpers;

import com.assessment.coin_tracker.dto.CollectQuoteResponseDTO;
import com.assessment.coin_tracker.entity.QuoteHistory;
import com.assessment.coin_tracker.enums.CRYPTO;
import com.assessment.coin_tracker.repository.QuoteHistoryRepository;
import com.assessment.coin_tracker.utils.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.UUID;

import static com.assessment.coin_tracker.common.Constants.*;

@Component
@Slf4j
public class quoteCollector {

    @Autowired
    QuoteHistoryRepository quoteHistoryRepository;

    @Value("${coin-market-cap.base-url:dummy}")
    private String collectorBaseUrl;

    @Value("${coin-market-cap.get-quote-by-symbol-endpoint:dummy}")
    private String collectorQuoteEndpoint;

    @Value("${coin-market-cap.api-key:dummy}")
    private String collectorApiKey;


    @Scheduled(fixedRate = 1000)
    public void collectQuote() {
        String pollId = UUID.randomUUID().toString();
        try {
            log.info("Quotes Collection began for pollId : {}", pollId);

            String url = buildUrl();
            HttpGet request = createHttpGetRequest(url);

            RequestHandler requestHandler = new RequestHandler();

            HttpResponse response = requestHandler.retryApi(request);
            String responseAsString = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();

            CollectQuoteResponseDTO responseDTO = objectMapper.readValue(responseAsString, CollectQuoteResponseDTO.class);

            if (responseDTO.getStatus().getErrorCode() == 0) {
                saveQuoteHistory(responseDTO, pollId);
            } else {
                log.error("Error from API, pollId: {}, error : {}", pollId,responseDTO.getStatus().getErrorMessage());
                throw new RuntimeException(responseDTO.getStatus().getErrorMessage());
            }

            log.info("Quotes successfully collected for pollId : {}", pollId);

        } catch (Exception e) {
            log.error("Exception occurred while collecting quotes for pollId : {}", pollId, e);
        }
    }

    private String buildUrl() {
        return UriComponentsBuilder.fromHttpUrl(collectorBaseUrl + collectorQuoteEndpoint)
                .queryParam(SYMBOL, String.join(SYMBOL_SEPERATOR, CRYPTO.getAllSymbols()))
                .toUriString();
    }

    private HttpGet createHttpGetRequest(String url) {
        HttpGet request = new HttpGet(url);
        request.setHeader(new BasicHeader(AUTH_HEADER_KEY, collectorApiKey));
        return request;
    }

    private void saveQuoteHistory(CollectQuoteResponseDTO responseDTO, String pollId) {
        responseDTO.getData().forEach((symbol, cryptoData) -> {
            try {
                CRYPTO crypto = CRYPTO.convertToCrypto(symbol);

                QuoteHistory quoteHistory = new QuoteHistory();
                quoteHistory.setCrypto(crypto);
                quoteHistory.setResponseData(cryptoData);
                quoteHistory.setCreatedAt(new Date());
                quoteHistory.setPollId(pollId);

                quoteHistoryRepository.save(quoteHistory);
            } catch (Exception e) {
                log.error("Exception occurred while saving quotes for pollId : {}, symbol :{}", pollId, symbol,e);
            }
        });
    }
}
