package org.ngavm1.deliverysystem.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, User user) {
        this.token = accessToken;
        this.username = user.getUsername();
        this.roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }
}