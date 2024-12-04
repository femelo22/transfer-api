package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.dtos.TranferenciaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.io.IOException;

@Service
@Slf4j
public class SqsSender {
    String queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/my-queue";
    String accessKey = "YOUR_ACCESS_KEY";
    String secretKey = "YOUR_SECRET_KEY";

    public void publicar(TranferenciaDTO dto) {
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.CA_CENTRAL_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(serializeObjectToJson(dto))
                .build();

        try {
            SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendMsgRequest);
            log.info("Mensagem enviada com sucesso! ID da mensagem: {}", sendMessageResponse.messageId());
        } catch (Exception e) {
            log.error("Erro ao enviar a mensagem: {}", e.getMessage());
            throw new RuntimeException("Erro ao enviar a mensagem: " + e.getMessage());
        } finally {
            sqsClient.close();
        }
    }

    public static String serializeObjectToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("Erro ao serializar o objeto: {}", e.getMessage());
            throw new RuntimeException("Erro ao serializar o objeto: " + e.getMessage());
        }
    }
}
