package com.demo.shop.user;

import com.demo.shop.user.domain.UserAccount;
import com.demo.shop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserAccount> getProducts() {
        return userRepository.findAll();
    }

    public void addProduct(UserAccount user) {
        Optional<UserAccount> productByCostCode = userRepository.findUserByEmail(user.getEmail());

        if (productByCostCode.isPresent()) {
            throw new IllegalStateException("email existed");
        }

        userRepository.save(user);
    }

    public void deleteProduct(Long userID) {
        boolean exists = userRepository.existsById(userID);

        if (!exists) {
            throw new IllegalStateException("user" + userID + "does not exists");
        }

        userRepository.deleteById(userID);

    }

    public void updateProduct(long userID, UserAccount user) {
        UserAccount target = userRepository.findById(userID)
                .orElseThrow(() -> new IllegalStateException("user" + userID + "does not exists"));

        target.setName(user.getName());
        target.setEmail(user.getEmail());
        target.setPassword(user.getPassword());
    }
}
