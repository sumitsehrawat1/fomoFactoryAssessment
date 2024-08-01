package com.assessment.coin_tracker.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.assessment.coin_tracker.common.Constants.API_RETRY_COUNTER;

@Component
public class RequestHandler {

    private HttpClient httpClient;

    @Bean
    public HttpClient httpClient(){
        if(Objects.isNull(httpClient)) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(1000)
                    .setConnectionRequestTimeout(1000)
                    .setSocketTimeout(1000)
                    .build();
            this.httpClient = HttpClientBuilder.create()
                    .setDefaultRequestConfig(requestConfig)
                    .build();
        }
        return this.httpClient;
    }

    public HttpResponse retryApi(HttpUriRequest request) {
        if(Objects.isNull(httpClient)) {
            httpClient();
        }
        int i = API_RETRY_COUNTER;
        HttpResponse response = null;
        while (i>0) {
            i--;
            try {
                response = httpClient.execute(request);
                break;
            } catch (Exception err) {
                if(i == 0)
                    throw new RuntimeException("Exception in retryApi");
            }
        }
        return response;
    }

}
