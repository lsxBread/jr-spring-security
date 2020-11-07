package com.example.jrspringsecurity.security;

public enum ApplicationUserAuthority {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("couse:write");

    private final String authority;

    ApplicationUserAuthority(String authority) {this.authority = authority; }

    public String getAuthority() {
        return this.authority;
    }
}
