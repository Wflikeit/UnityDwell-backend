package UnityDwell.com.UnityDwell.config.security;

import UnityDwell.com.UnityDwell.error.ErrorResponse;
import UnityDwell.com.UnityDwell.model.users.Role;
import UnityDwell.com.UnityDwell.model.users.User;
import UnityDwell.com.UnityDwell.service.EmployeeService;
import UnityDwell.com.UnityDwell.service.OwnerOfFlatService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final OwnerOfFlatService ownerOfFlatService;
    private final EmployeeService employeeService;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final int TOKEN_BEGIN_INDEX = 7;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authenticationHeaderValue = request.getHeader(HEADER_STRING);
        final String jwtToken;
        final String userEmail;
        if (authenticationHeaderValue == null || !authenticationHeaderValue.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authenticationHeaderValue.substring(TOKEN_BEGIN_INDEX);
        try {
            jwtService.verifyToken(jwtToken);
        } catch (JWTVerificationException e) {
            processErrorResponse(response);
            return;
        }
        userEmail = jwtService.extractUserEmail(jwtToken);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Role role = Role.valueOf(jwtService.extractUserRole(jwtToken));
            User userDetails = getUser(role, userEmail);
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    @SneakyThrows
    private void processErrorResponse(HttpServletResponse response) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Jwt token is expired or invalid")
                .additionalData(new HashMap<>())
                .build();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(errorResponse));
    }

    private User getUser(Role role, String userEmail) {
        User userDetails = new User();
        switch (role) {
            case ADMIN -> {
                userDetails = employeeService.loadUserByUsername(userEmail);
                userDetails.setRole(Role.ADMIN);
            }
            case EMPLOYEE -> {
                userDetails = employeeService.loadUserByUsername(userEmail);
                userDetails.setRole(Role.EMPLOYEE);
            }
            case FLAT_OWNER -> {
                userDetails = ownerOfFlatService.loadUserByUsername(userEmail);
                userDetails.setRole(Role.FLAT_OWNER);
            }
        }
        return userDetails;
    }
}
