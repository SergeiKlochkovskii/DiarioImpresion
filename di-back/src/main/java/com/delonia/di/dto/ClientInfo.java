package com.delonia.di.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Service
@Slf4j

public class ClientInfo {
    private String nombre;
    private String apellido1;
    private String apellido2;
}

