package com.bsl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsl.entity.Admin;
import com.bsl.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/register")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
		Admin savedAdmin = adminService.createAdmin(admin);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
	}

	@PostMapping("/login") // user login
	public String login(@RequestBody Admin user) {
		String message = adminService.loginUser(user.getName(), user.getPassword());
		if (message.equals("yes")) {
			return "Login Successfull";
		} else {
			return "Invalid User";
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") Long id) {
		Optional<Admin> admin = adminService.getAdminById(id);
		if (admin.isPresent()) {
			return ResponseEntity.ok(admin.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<Admin>> searchAdminsByName(@RequestParam("name") String name) {
		List<Admin> admins = adminService.searchAdminsByName(name);
		return ResponseEntity.ok(admins);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long id, @RequestBody Admin admin) {
		Optional<Admin> existingAdmin = adminService.getAdminById(id);
		if (existingAdmin.isPresent()) {
			admin.setId(id);
			Admin updatedAdmin = adminService.updateAdmin(admin);
			return ResponseEntity.ok(updatedAdmin);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public String deleteAdminById(@PathVariable("id") Long id) {
		adminService.deleteAdminById(id);
		return "Deleted successfully";
	}
}