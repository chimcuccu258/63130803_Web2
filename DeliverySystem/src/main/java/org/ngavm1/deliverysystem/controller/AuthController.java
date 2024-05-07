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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

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

            ResponseModel responseModel = new ResponseModel(200, MessageStringResponse.LOGIN_SUCCESSFULLY, jwtResponse);
            return ResponseEntity.ok(responseModel);
        } catch (AuthenticationException e) {
            ResponseModel responseModel = new ResponseModel(401, MessageStringResponse.INVALID_USERNAME_OR_PASSWORD, null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseModel);
        }
    }

//    @PostMapping("/supplier-signup")
//    public ResponseEntity<ResponseModel> registerStore(@Valid @RequestBody RequestSupplierSignup requestSupplierSignup) throws SupplierException {
//        try {
//            if (customerRepository.existsByEmail(requestCustomerSignup.getEmail())) {
//                return ResponseEntity.badRequest().body(new ResponseModel(400, MessageStringResponse.EMAIL_IS_ALREADY, null));
//            }
//
//            // Encode password
//            requestCustomerSignup.setPassword(encoder.encode(requestCustomerSignup.getPassword()));
//
//            int result = customerRepository.registerCustomer(requestCustomerSignup);
//
//            if (result > 0) {
//                return ResponseEntity.ok().body(new ResponseModel(200, MessageStringResponse.SUCCESS, null));
//            } else {
//                return ResponseEntity.badRequest().body(new ResponseModel(400, MessageStringResponse.ACCOUNT_CREATION_FAILED, null));
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new ResponseModel(400, e.getMessage(), null));
//        }
//    }

    @PostMapping("/employee-signup")
    public ResponseEntity<ResponseModel> registerEmployee(@Valid @RequestBody RequestEmployeeSignup request) throws EmployeeException {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseModel(400, MessageStringResponse.EMAIL_IS_ALREADY, null));
        }

        // Encode password
        request.setPassword(encoder.encode(request.getPassword()));
//        return employeeRepository.createUser(request);
        int result = employeeRepository.createAccount(request);

        if (result > 0) {
            return ResponseEntity.ok().body(new ResponseModel(200, MessageStringResponse.SUCCESS, null));
        } else {
            return ResponseEntity.badRequest().body(new ResponseModel(400, MessageStringResponse.ACCOUNT_CREATION_FAILED, null));
        }
    }

    @PostMapping("/sign-out")
    public ResponseEntity<ResponseModel> signOut(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        request.getSession().removeAttribute("token");

        // Remove authentication from SecurityContext
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.setClearAuthentication(true);
        securityContextLogoutHandler.logout(request, response, authentication);

        return ResponseEntity.ok(new ResponseModel(200, "Sign out successfully", null));
    }
}