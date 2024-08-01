package com.assessment.coin_tracker.entity;

import com.assessment.coin_tracker.dto.CollectQuoteResponseDTO;
import com.assessment.coin_tracker.enums.CRYPTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quote_history")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuoteHistory {
    @Id
    private String id;
    private CRYPTO crypto;
    private CollectQuoteResponseDTO.Cryptocurrency responseData;
    private String pollId;
    private Date createdAt;
}
