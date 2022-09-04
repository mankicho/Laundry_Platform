package com.coders.laundry.service.notify;

import com.coders.laundry.domain.entity.DeviceTokenEntity;
import com.coders.laundry.domain.entity.MemberEntity;
import com.coders.laundry.repository.DeviceTokenRepository;
import com.coders.laundry.scheduler.NotificationScheduler;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class FcmNotificationService implements NotificationService {

    private final DeviceTokenRepository deviceTokenRepository;
    private final NotificationScheduler scheduler;
    private final String FCM_PRIVATE_KEY_PATH;

    @Autowired
    public FcmNotificationService(
        DeviceTokenRepository deviceTokenRepository,
        NotificationScheduler scheduler,
        @Value("${fcm.path}") String path
    ) {
        this.deviceTokenRepository = deviceTokenRepository;
        this.scheduler = scheduler;
        this.FCM_PRIVATE_KEY_PATH = path;
    }

    @PostConstruct
    public void init() {
        // TODO: 파일경로를 선언해주는건 보안적으로좋지않음. 환경변수로 export 하여 접근하도록 할것.
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials
                    .fromStream(new FileUrlResource(FCM_PRIVATE_KEY_PATH).getInputStream())
                    .createScoped(
                        "https://www.googleapis.com/auth/firebase",
                        "https://www.googleapis.com/auth/cloud-platform",
                        "https://www.googleapis.com/auth/firebase.readonly"
                    )
                )
                .build();
            if (FirebaseApp.getApps().isEmpty()) {
                log.info("successfully FirebaseApp init");
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void send(int memberId, String message) {
        DeviceTokenEntity deviceTokenEntity = deviceTokenRepository.selectByMemberId(memberId);

        scheduler.submit(() -> {
                Message m = Message.builder()
                    .putData("time", LocalDateTime.now().toString())
                    .setNotification(
                        Notification.builder()
                            .setTitle("testTitle")
                            .setBody(message)
                        .build())
                    .setToken(deviceTokenEntity.getToken())
                    .build();

                try {
                    String response = FirebaseMessaging.getInstance().send(m);
                    System.out.println("Successfully sent message: " + response);
                } catch (FirebaseMessagingException e) {
                    log.error(e);
                }
            },
            Duration.ofMillis(1000L)
        );
    }
}
