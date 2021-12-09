package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Notifications;
import CS434.ExamPortal.Repositories.NotificationRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.List;

@RestController
public class NotificationsController {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @GetMapping(path="/get-notification-student-id")
    public @ResponseBody
    List<Notifications> getNotification(@RequestBody Notifications notifications) {
        NullUser users = userRepositoryImpl.findByUserId(notifications.getStudentId());
        if (!users.isAvailable()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user doesn't exist");

        }

        return notificationRepository
                .findNotificationsByStudentIdAndSeen(notifications.getStudentId(),0);

    }
    @GetMapping(path="/mark-as-seen-all")
    public @ResponseBody
    ResponseStatusException markAsSeenNotification(@RequestBody Notifications notifications) {
        NullUser users = userRepositoryImpl.findByUserId(notifications.getStudentId());
        if (!users.isAvailable()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user doesn't exist");

        }

        List<Notifications> notifications1 = notificationRepository
                .findNotificationsByStudentId(notifications.getStudentId());
        Iterator<?> it = notifications1.iterator();
        while (it.hasNext()){
            Notifications singleNotification = (Notifications) it.next();
            singleNotification.setSeen(1);
            notificationRepository.save(singleNotification);
        }

        return new ResponseStatusException(HttpStatus.ACCEPTED);

    }
}
