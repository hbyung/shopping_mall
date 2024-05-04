package study.shopping_mall.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String username;
//    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{8,16}$", message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    private String password;
//    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{8,16}$", message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    private String repassword;
    private String role;
    @NotEmpty(message = "휴대폰 번호는 필수 입니다.")
    private String phone;

    @NotEmpty(message = "회원 이메일은 필수 입니다.")
    private String email;

}
