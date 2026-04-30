package gl2.example.PrAR.controller;

import gl2.example.PrAR.model.User;
import gl2.example.PrAR.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{ncin}")
    public ResponseEntity<User> getOne(@PathVariable String ncin) {
        return userService.getByNcin(ncin).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{ncin}")
    public ResponseEntity<User> update(@PathVariable String ncin, @RequestBody User user) {
        if (userService.getByNcin(ncin).isEmpty()) return ResponseEntity.notFound().build();
        user.setNcin(ncin);
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{ncin}")
    public ResponseEntity<Void> delete(@PathVariable String ncin) {
        userService.delete(ncin);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User credentials) {
        return userService.login(credentials.getNcin(), credentials.getPassword())
                .map(u -> ResponseEntity.ok("Login successful, role: " + u.getRole()))
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
