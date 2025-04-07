package com.hotelmanagementsystem.indentity_service.service;

import com.hotelmanagementsystem.indentity_service.entity.TaiKhoan;
import com.hotelmanagementsystem.indentity_service.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan tk = taiKhoanRepository.findByTenDangNhap(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng"));
        return new org.springframework.security.core.userdetails.User(
                tk.getTenDangNhap(),
                tk.getMatKhau(),
                List.of(new SimpleGrantedAuthority(tk.getRole().name()))
        );
    }
}
