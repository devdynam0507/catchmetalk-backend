package dev.community.gdg.configuration.security;

import dev.community.gdg.configuration.jwt.JwtService;
import dev.community.gdg.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class PreAuthTokenProvider implements AuthenticationProvider {
    private final MemberService memberService;
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            final String token = authentication.getPrincipal().toString();
            final Long memberId = jwtService.decode(token);
            try {
                // TODO: memberService
//                final Member member = memberService.get(memberId);
            } catch (Exception e) {
                log.warn("member not found", e);
                throw new TokenMissingException("Member not found");
            }
            return PreAuthenticatedAuthenticationToken(
                    // TODO
                    member.memberId,
                    "",
                    Collections.singletonList(new SimpleGrantedAuthority(SecurityConfig.MEMBER_ROLE_NAME))
            );
        }
        throw new TokenMissingException("Invalid token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
