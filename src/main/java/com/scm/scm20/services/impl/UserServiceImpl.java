package com.scm.scm20.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm20.entities.User;
import com.scm.scm20.helpers.AppConstants;
import com.scm.scm20.helpers.Helper;
import com.scm.scm20.helpers.ResourceNotFoundException;
import com.scm.scm20.repositories.UserRepo;
import com.scm.scm20.services.EmailService;
import com.scm.scm20.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired(required = true)
    private EmailService emailService;

    @Autowired
    private Helper helper;

    @Override
    public User saveUser(User user) {

        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        // ✅ SAFE for Google + normal users
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);

        User savedUser = userRepo.save(user);

        if (emailService != null) {
            String emailLink =
                    helper.getLinkForEmailVerificatiton(emailToken);
            emailService.sendEmail(
                    savedUser.getEmail(),
                    "Verify Account : Smart Contact Manager",
                    emailLink
            );
        }

        return savedUser;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {

        User user2 = userRepo.findById(user.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        if (user.getPassword() != null) {
            user2.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return Optional.of(userRepo.save(user2));
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(String userId) {
        return userRepo.findById(userId).isPresent();
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public User updateProfile(
            String userId,
            String about,
            String phoneNumber,
            String profilePic
    ) {

        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // ✅ ONLY allowed fields
        user.setAbout(about);
        user.setPhoneNumber(phoneNumber);
        user.setProfilePic(profilePic);

        // ❌ email NOT touched
        // ❌ username NOT touched
        // ❌ password NOT touched
        // ❌ roles / provider NOT touched

        return userRepo.save(user);
    }

}
