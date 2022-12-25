package ru.clevertec.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doAdminStuff() {
        log.info("Only admin here");
    }
}
