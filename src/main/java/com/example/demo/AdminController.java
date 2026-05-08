package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminController
{
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return adminService.getUsers();
    }

    @PostMapping("/freeze")
    public String freeze(@RequestParam("email") String email) {
        return adminService.freeze(email);
    }
}
