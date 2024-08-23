package com.example.demo.Service;

import com.example.demo.DTO.EmploymentStatisticsDTO;
import com.example.demo.DTO.RouteGraphDTO;
import com.example.demo.Model.User;
import com.example.demo.Model.UserInfoDetails;
import com.example.demo.Repository.TerminationRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private TerminationRepository terminationRepository;
    @Autowired
    private UserRepository userRepository;
    @Value("${image.upload.path}")
    private String imagePath;

    public User save(User user){
        //return this.userRepository.save(user);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User FindByUsername(String username){
        return this.userRepository.getByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User addUser(User userInfo) {
        User savedUser = userRepository.save(userInfo);
        return savedUser;
    }

    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User getById(Integer userId){
        return userRepository.getById(userId);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRoles(role);
    }

    public List<User> searchByName(String name) {
        return userRepository.searchByName(name);
    }

    public List<User> searchByRole(String role) {
        return userRepository.findByRoleContaining(role);
    }

    public String getImage(String imageName) throws IOException {
        Path imagePath = Paths.get(this.imagePath, imageName);
        byte[] imageBytes = Files.readAllBytes(imagePath);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public void uploadFile(MultipartFile file, String fileName) throws IOException {
        if (!file.isEmpty()) {
            Path uploadDir = Paths.get(this.imagePath);
            Path targetPath = uploadDir.resolve(fileName);

            Files.createDirectories(uploadDir);
            Files.write(targetPath, file.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
    }

    public String generateStoredFileName(String prefix, MultipartFile file) {
        String fileExtension = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return prefix + "_" + dateTime + "." + fileExtension;
    }

    public EmploymentStatisticsDTO getEmploymentStatistics() {
        int employedCount = userRepository.countByEmployedTrue();
        int terminatedCount = (int) terminationRepository.count();
        return new EmploymentStatisticsDTO(employedCount, terminatedCount);
    }

}
