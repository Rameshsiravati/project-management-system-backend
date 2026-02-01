import com.example.projectmanagementTool.model.Project;
import com.example.projectmanagementTool.model.ProjectInvitation;
import com.example.projectmanagementTool.model.User;
import com.example.projectmanagementTool.service.ProjectService;
import com.example.projectmanagementTool.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invitations")
public class ProjectInvitationController {

    private final ProjectInvitationService invitationService;
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectInvitationController(ProjectInvitationService invitationService,
                                       ProjectService projectService,
                                       UserService userService) {
        this.invitationService = invitationService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping("/send")
    public ProjectInvitation sendInvitation(
            @RequestParam Long projectId,
            @RequestParam String email,
            Authentication authentication) {

        User sender = userService.getUserByEmail(authentication.getName());
        User invitedUser = userService.getUserByEmail(email);
        Project project = projectService.getProjectById(projectId, sender);

        return invitationService.sendInvitation(project, invitedUser, sender);
    }

    @GetMapping("/pending")
    public List<ProjectInvitation> getPendingInvitations(Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        return invitationService.getPendingInvitations(user);
    }

    @PostMapping("/{invitationId}/respond")
    public ProjectInvitation respondToInvitation(
            @PathVariable Long invitationId,
            @RequestParam boolean accept) {

        ProjectInvitation invitation = invitationService
                .getInvitationById(invitationId); // we’ll add this method
        return invitationService.respondToInvitation(invitation, accept);
    }
}
