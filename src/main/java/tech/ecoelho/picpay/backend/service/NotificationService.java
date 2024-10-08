package tech.ecoelho.picpay.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.ecoelho.picpay.backend.client.NotificationClient;
import tech.ecoelho.picpay.backend.entity.Transfer;


@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;

    }

    public void sendNotification(Transfer transfer){
        try {
            logger.info("sending notification...");

            var response = notificationClient.sendNotification(transfer);
            if(response.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not OK");
            }

        } catch (Exception e){
            logger.error("Error while sending notification", e);
        }
    }
}
