package gl2.example.PrAR.service;

import gl2.example.PrAR.model.User;
import gl2.example.PrAR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getByNcin(String ncin) {
        return userRepository.findById(ncin);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(String ncin) {
        userRepository.deleteById(ncin);
    }

    public Optional<User> login(String ncin, String password) {
        return userRepository.findById(ncin).filter(u -> u.getPassword().equals(password));
    }
}
