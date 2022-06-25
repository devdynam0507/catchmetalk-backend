package dev.community.gdg.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Slf4j
@Component
public class MemberIdResolver {
    public Long resolveMemberId(Principal principal) {
        if (principal instanceof PreAuthenticatedAuthenticationToken) {
            return Long.parseLong(((PreAuthenticatedAuthenticationToken) principal).getPrincipal().toString());
        }
        throw new IllegalArgumentException("Failed to resolve memberId");
    }
}
