package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.dtos.TranferenciaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;


import java.io.IOException;

@Service
@Slf4j
public class SqsSender {
    @Value("${aws.trasnfer.url}")
    String queueUrl;
    private final SqsClient sqsClient;
    public SqsSender(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void publicar(TranferenciaDTO dto) {
        try {
            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(serializeObjectToJson(dto))
                    .build();

            SendMessageResponse response = sqsClient.sendMessage(request);

            System.out.println("Mensagem enviada com sucesso! MessageId: " + response.messageId());
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
        }
    }

    public static String serializeObjectToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
//            log.error("Erro ao serializar o objeto: {}", e.getMessage());
            throw new RuntimeException("Erro ao serializar o objeto: " + e.getMessage());
        }
    }
}
