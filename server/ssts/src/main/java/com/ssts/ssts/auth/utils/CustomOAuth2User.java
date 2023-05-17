package com.ssts.ssts.auth.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {

    private long memberId;
    private String email;

    /**
     * Constructs a {@code DefaultOAuth2User} using the provided parameters.
     *
     * @param authorities      the authorities granted to the user
     * @param attributes       the attributes about the user
     * @param nameAttributeKey the key used to access the user's &quot;name&quot; from
     *                         {@link #getAttributes()}
     */
    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey,
                            long memberId, String email) {
        super(authorities, attributes, nameAttributeKey);
        this.memberId=memberId;
        this.email=email;
    }

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey,
                            String email) {
        super(authorities, attributes, nameAttributeKey);
        this.memberId=-1;
        this.email=email;
    }

    public long getMemberId(){
        return this.memberId;
    }

    public String getEmail(){
        return this.email;
    }

}
