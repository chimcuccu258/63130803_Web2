package org.ngavm1.deliverysystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ngavm1.deliverysystem.exception.EmployeeException;
import org.ngavm1.deliverysystem.exception.SupplierException;
import org.ngavm1.deliverysystem.payload.request.RequestEmployeeSignup;
import org.ngavm1.deliverysystem.payload.request.RequestLogin;
import org.ngavm1.deliverysystem.payload.request.RequestSupplierSignup;
import org.ngavm1.deliverysystem.payload.response.JwtResponse;
import org.ngavm1.deliverysystem.payload.response.ResponseModel;
import org.ngavm1.deliverysystem.repository.CustomerRepository;
import org.ngavm1.deliverysystem.repository.EmployeeRepository;
import org.ngavm1.deliverysystem.repository.SupplierRepository;
import org.ngavm1.deliverysystem.security.JwtUtils;
import org.ngavm1.deliverysystem.utils.MessageStringResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final SupplierRepository supplierRepository;

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> authenticateUser(@Valid @RequestBody RequestLogin loginRequest, HttpServletRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            // Save token to session
            request.getSession().setAttribute("token", jwt);

            org.springframework.security.core.userdetails.User userDetails = (User) authentication.getPrincipal();

            // Return token to client
            JwtResponse jwtResponse = new JwtResponse(jwt, userDetails);

            ResponseModel responseModel = new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.LOGIN_SUCCESSFULLY, jwtResponse);
            return ResponseEntity.ok(responseModel);
        } catch (AuthenticationException e) {
            ResponseModel responseModel = new ResponseModel(MessageStringResponse.ERROR, MessageStringResponse.INVALID_USERNAME_OR_PASSWORD, null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseModel);
        }
    }

    @PostMapping("/supplier-signup")
    public ResponseEntity<ResponseModel> registerSupplier(@Valid @RequestBody RequestSupplierSignup requestSupplierSignup) throws SupplierException {
        try {
            if (supplierRepository.existsByEmail(requestSupplierSignup.getEmail())) {
                return ResponseEntity.badRequest().body(new ResponseModel(MessageStringResponse.ERROR, MessageStringResponse.EMAIL_IS_ALREADY, null));
            }

            if (supplierRepository.existByPhone(requestSupplierSignup.getPhoneNumber())) {
                return ResponseEntity.badRequest().body(new ResponseModel(MessageStringResponse.ERROR, MessageStringResponse.PHONE_NUMBER_IS_ALREADY, null));
            }

            // Encode password
            requestSupplierSignup.setPassword(encoder.encode(requestSupplierSignup.getPassword()));

            int result = supplierRepository.insertSupplier(requestSupplierSignup);

            if (result > 0) {
                return ResponseEntity.ok().body(new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, null));
            } else {
                return ResponseEntity.badRequest().body(new ResponseModel(MessageStringResponse.ERROR, MessageStringResponse.ACCOUNT_CREATION_FAILED, null));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseModel(MessageStringResponse.ERROR, e.getMessage(), null));
        }
    }

    @PostMapping("/employee-signup")
    public ResponseEntity<ResponseModel> registerEmployee(@Valid @RequestBody RequestEmployeeSignup requestEmployeeSignup) throws EmployeeException {
        if (employeeRepository.existsByEmail(requestEmployeeSignup.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseModel(MessageStringResponse.ERROR, MessageStringResponse.EMAIL_IS_ALREADY, null));
        }

        if (employeeRepository.existByPhone(requestEmployeeSignup.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(new ResponseModel(MessageStringResponse.ERROR, MessageStringResponse.PHONE_NUMBER_IS_ALREADY, null));
        }

        // Encode password
        requestEmployeeSignup.setPassword(encoder.encode(requestEmployeeSignup.getPassword()));

        int result = employeeRepository.insertEmployee(requestEmployeeSignup);

        if (result > 0) {
            return ResponseEntity.ok().body(new ResponseModel(MessageStringResponse.SUCCESS, MessageStringResponse.SUCCESS, null));
        } else {
            return ResponseEntity.badRequest().body(new ResponseModel(MessageStringResponse.ERROR, MessageStringResponse.ACCOUNT_CREATION_FAILED, null));
        }
    }

    @PostMapping("/sign-out")
    public ResponseEntity<ResponseModel> signOut(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        request.getSession().removeAttribute("token");

        // Remove authentication from SecurityContext
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.setClearAuthentication(true);
        securityContextLogoutHandler.logout(request, response, authentication);

        return ResponseEntity.ok(new ResponseModel(MessageStringResponse.SUCCESS, "Sign out successfully", null));
    }
}