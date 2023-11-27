package by.bsuir.marketing.controller.rest;

import by.bsuir.marketing.model.BaseEntity;
import by.bsuir.marketing.model.MyResponseEntity;
import by.bsuir.marketing.model.Role;
import by.bsuir.marketing.service.RoleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleRestController {

    private final RoleDataService roleDataService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleDataService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> getRoleById(@PathVariable int id) {
        Optional<Role> roleOptional = roleDataService.getRoleById(id);

        if (roleOptional.isEmpty()) {
            return new ResponseEntity<>(new MyResponseEntity("Роль не найдена"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(roleOptional.get());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleDataService.createRole(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role role) {
        return ResponseEntity.ok(roleDataService.updateRole(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponseEntity> deleteRole(@PathVariable int id) {
        roleDataService.deleteRole(id);

        return ResponseEntity.ok(new MyResponseEntity("Роль удалена"));
    }
}