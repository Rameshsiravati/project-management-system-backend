import com.example.projectmanagementTool.model.InvitationStatus;
import com.example.projectmanagementTool.model.Project;
import com.example.projectmanagementTool.model.ProjectInvitation;
import com.example.projectmanagementTool.model.User;
import com.example.projectmanagementTool.repository.ProjectInvitationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInvitationService {

    private final ProjectInvitationRepository invitationRepository;

    public ProjectInvitationService(ProjectInvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public ProjectInvitation sendInvitation(Project project, User invitedUser, User invitedBy) {

        invitationRepository.findByProjectAndInvitedUser(project, invitedUser)
                .ifPresent(invite -> {
                    throw new RuntimeException("User already invited");
                });

        ProjectInvitation invitation = new ProjectInvitation();
        invitation.setProject(project);
        invitation.setInvitedUser(invitedUser);
        invitation.setInvitedBy(invitedBy);
        invitation.setStatus(InvitationStatus.PENDING);

        return invitationRepository.save(invitation);
    }

    public List<ProjectInvitation> getPendingInvitations(User user) {
        return invitationRepository.findByInvitedUserAndStatus(user, InvitationStatus.PENDING);
    }

    public ProjectInvitation respondToInvitation(ProjectInvitation invitation, boolean accept) {
        invitation.setStatus(accept ? InvitationStatus.ACCEPTED : InvitationStatus.REJECTED);
        return invitationRepository.save(invitation);
    }
    public ProjectInvitation getInvitationById(Long id) {
        return invitationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invitation not found"));
    }

}
