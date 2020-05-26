package dajoni.spring.playground;

import lombok.Data;

@Data
class ApplicationUser {
    private String username;
    private String password;
    private String[] roles;
}
