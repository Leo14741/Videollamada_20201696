package com.example.videollamada20201696;

import com.twilio.Twilio;
import com.twilio.rest.video.v1.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class VideoCallController {

    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC4917c7396b3c1497ac61235eec3c7cdf";
    public static final String AUTH_TOKEN = "c04b4e2e8beb10ae21b0278a79338fa9";

    @GetMapping("")
    public String getVideoCallView() {
        return "Telito";
    }

    @PostMapping("/start-video-call")
    public ResponseEntity<String> startVideoCall() {
        try {
            String uniqueRoomName = "My Room " + System.currentTimeMillis();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Room room = Room.creator()
                    .setType(Room.RoomType.GO)
                    .setUniqueName(uniqueRoomName)
                    .create();

            return ResponseEntity.ok(room.getSid());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al iniciar la videollamada");
        }
    }
}