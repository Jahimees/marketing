package by.bsuir.marketing.controller;

import by.bsuir.marketing.model.Role;
import by.bsuir.marketing.service.RoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    private final RoleDataService roleDataService;

    @Autowired
    public RoleRestController(RoleDataService roleDataService) {
        this.roleDataService = roleDataService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleDataService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable int id) {
        return roleDataService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleDataService.createRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable int id, @RequestBody Role role) {
        return roleDataService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable int id) {
        roleDataService.deleteRole(id);
    }
}