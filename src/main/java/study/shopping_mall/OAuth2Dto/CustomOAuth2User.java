package study.shopping_mall.OAuth2Dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2MemberDto oAuth2MemberDto;


    public CustomOAuth2User(OAuth2MemberDto oAuth2MemberDto) {

        this.oAuth2MemberDto = oAuth2MemberDto;
    }


    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return oAuth2MemberDto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {

        return oAuth2MemberDto.getName();
    }

    public String getUsername() {

        return oAuth2MemberDto.getUsername();
    }
}
