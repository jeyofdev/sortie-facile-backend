package com.poec.sortie_facile_backend.util;

import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryMapper;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.core.enums.Role;
import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.auth_user.AuthUserRepository;
import com.poec.sortie_facile_backend.domain.category.dto.SaveCategoryDTO;
import com.poec.sortie_facile_backend.domain.contact.Contact;
import com.poec.sortie_facile_backend.domain.contact.ContactMapper;
import com.poec.sortie_facile_backend.domain.contact.ContactRepository;
import com.poec.sortie_facile_backend.domain.contact.dto.SaveContactDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final CategoryRepository categoryRepository;
    private final ContactRepository contactRepository;

    private final CategoryMapper categoryMapper;
    private final ContactMapper contactMapper;

    @Override
    public void run(String... args) throws Exception {
        if (this.authUserRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createAdmin();
            this.createUsers();
        }

        this.createDatas();
    }

    private void createAdmin() {
        AuthUser admin = AuthUser.builder()
                .nickname("admin")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("admin"))
                .role("ROLE_" + Role.ADMIN)
                .build();

        this.authUserRepository.save(admin);
    }

    private void createUsers() {
        AuthUser user1 = AuthUser.builder()
                .nickname("user")
                .email("user@user.com")
                .password(passwordEncoder.encode("user"))
                .role("ROLE_" + Role.USER)
                .build();

        this.authUserRepository.save(user1);
    }

    private void createDatas() {
        this.createMessageEmails();
        this.createCategories();
    }

    private void createCategories() {
        SaveCategoryDTO saveCategorySport = new SaveCategoryDTO("Sport", "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnR8ZW58MHx8MHx8fDA%3D");
        SaveCategoryDTO saveCategoryMovie = new SaveCategoryDTO("Cinéma", "https://images.unsplash.com/photo-1604061986761-d9d0cc41b0d1?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8VGFibGUlMjBCYXNzZXxlbnwwfHwwfHx8MA%3D%3D");
        SaveCategoryDTO saveCategoryCulture = new SaveCategoryDTO("Culture", "https://plus.unsplash.com/premium_photo-1661407582641-9ce38a3c8402?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Q2FuYXAlQzMlQTl8ZW58MHx8MHx8fDA%3D");
        SaveCategoryDTO saveCategoryMusic = new SaveCategoryDTO("Musique", "https://images.unsplash.com/photo-1595428774223-ef52624120d2?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8QXJtb2lyZXxlbnwwfHwwfHx8MA%3D%3D");
        SaveCategoryDTO saveCategoryVideoGame = new SaveCategoryDTO("Jeux vidéos", "https://images.unsplash.com/photo-1586753513812-462ed2a82584?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mjh8fCVDMyVBOWNsYWlyYWdlJTIwbGVkfGVufDB8fDB8fHww");

        List<SaveCategoryDTO> saveCategoryList = Arrays.asList(saveCategorySport, saveCategoryMovie, saveCategoryCulture, saveCategoryMusic, saveCategoryVideoGame);

        for (SaveCategoryDTO saveCategory : saveCategoryList) {
            Category category = categoryMapper.mapToEntity(saveCategory);
            categoryRepository.save(category);
        }
    }

    private void createMessageEmails() {
        SaveContactDTO saveEmailMessageA = new SaveContactDTO("Renseignement sur une activité", "johndoe@test.com", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In in felis quis odio elementum bibendum a id tellus. Integer ultricies vel mauris eu pretium. Donec efficitur felis quis tincidunt vulputate. Aliquam et odio efficitur, bibendum elit sed, ullamcorper tellus. Pellentesque at sapien vitae diam euismod viverra eu vel nisi.", false);
        SaveContactDTO saveEmailMessageB = new SaveContactDTO("Annulation de réservation", "janedoe@test.com", "Sed at rhoncus sem. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Integer maximus odio id sem tristique efficitur. Sed efficitur, tortor sed tempus aliquet, quam orci varius tellus, eget vestibulum urna justo quis sapien. Phasellus gravida consequat pharetra.", false);

        List<SaveContactDTO> saveEmailMessageList = Arrays.asList(saveEmailMessageA, saveEmailMessageB);

        for (SaveContactDTO saveEmailMessage : saveEmailMessageList) {
            Contact emailMessage = contactMapper.mapToEntity(saveEmailMessage);
            contactRepository.save(emailMessage);
        }
    }
}
