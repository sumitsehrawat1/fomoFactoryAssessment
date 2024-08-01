package com.assessment.coin_tracker.repository;

import com.assessment.coin_tracker.entity.QuoteHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteHistoryRepository extends MongoRepository<QuoteHistory, String> {
}
