package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Role;
import by.bsuir.marketing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDataService implements DataService<Role> {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleDataService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(int id, Role role) {
        Role existingRole = roleRepository.findById(id).orElse(null);
        if (existingRole != null) {
            existingRole.setName(role.getName());
            return roleRepository.save(existingRole);
        }
        return null;
    }

    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}