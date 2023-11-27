package by.bsuir.marketing.service;

import by.bsuir.marketing.model.Role;
import by.bsuir.marketing.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleDataService implements DataService<Role> {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
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