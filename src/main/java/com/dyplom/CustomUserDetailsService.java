package com.dyplom;

import com.dyplom.model.Student;
import com.dyplom.model.Teacher;
import com.dyplom.service.StudentService;
import com.dyplom.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentService.findByLogin(username);
        if (student != null) {
            return new CustomUserDetails(student.getStudentLogin(), student.getStudentPassword(), student.getStudentRole());
        } else {
            Teacher teacher = teacherService.findByLogin(username);
            if (teacher != null) {
                return new CustomUserDetails(teacher.getTeacherLogin(), teacher.getTeacherPassword(), teacher.getTeacherrole());
            }
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    public class CustomUserDetails implements UserDetails {

        private String username;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public CustomUserDetails() {
            super();
        }

        public CustomUserDetails(String username, String password, String role) {
            this.username = username;
            this.password = password;
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            this.authorities = grantedAuthorities;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }
  
        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

}