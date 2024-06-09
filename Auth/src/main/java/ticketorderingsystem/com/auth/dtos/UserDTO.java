package ticketorderingsystem.com.auth.dtos;

import lombok.Builder;
import lombok.Data;
import ticketorderingsystem.com.auth.models.UserModel;

import java.util.Date;

@Builder
@Data
public class UserDTO {
    private long id;
    private String nickname;
    private String email;
    private Date createdAt;

    public static UserDTO toDTO(UserModel userModel) {
        return UserDTO.builder()
                .id(userModel.getId())
                .nickname(userModel.getNickname())
                .email(userModel.getEmail())
                .createdAt(userModel.getCreatedAt())
                .build();
    }
}
