package com.bsl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsl.entity.Admin;
import com.bsl.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;

	public String loginUser(String username, String password) {

		Admin admin = adminRepository.findByName(username); // it will set user to null if username is not present
		if (admin != null && admin.getPassword().equals(password)) {
			return "yes";
		} else {
			return "no";
		}

	}

	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public Optional<Admin> getAdminById(Long id) {
		return adminRepository.findById(id);
	}

	public Optional<Admin> getAdminByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	public List<Admin> searchAdminsByName(String name) {
		return adminRepository.findByNameContainingIgnoreCase(name);
	}

	public Admin updateAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public void deleteAdminById(Long id) {
		adminRepository.deleteById(id);
	}
}