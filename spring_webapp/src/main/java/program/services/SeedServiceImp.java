package program.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import program.constants.Roles;
import program.entities.RoleEntity;
import program.iterfaces.SeedService;
import program.repositories.RoleRepository;

@Service
@AllArgsConstructor
public class SeedServiceImp implements SeedService {
    RoleRepository roleRepository;
    @Override
    public void seedRoleData() {
        if (roleRepository.count() == 0) {
            RoleEntity admin = new RoleEntity().builder()
                    .name(Roles.Admin)
                    .build();
            roleRepository.save(admin);

            RoleEntity user = new RoleEntity().builder()
                    .name(Roles.User)
                    .build();
            roleRepository.save(user);
        }
    }

    @Override
    public void seedUserData() {

    }
}
