package br.com.fiap.coletas.model;

public enum UsuarioRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

//    public void setRole(String role) {
//        this.role = role;
//    }
}
