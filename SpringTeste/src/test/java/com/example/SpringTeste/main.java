package com.example.SpringTeste;

import com.example.SpringTeste.Entity.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario u = Usuario.builder()
                .id(1)
                .nome("Teste")
                .email("teste@email.com")
                .build();

        System.out.println(u);
    }
}
