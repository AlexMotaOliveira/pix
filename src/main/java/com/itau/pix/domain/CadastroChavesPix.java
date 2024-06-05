package com.itau.pix.domain;

import jakarta.validation.Valid;


public record CadastroChavesPix(@Valid Correntista correntista, @Valid ChavePix chavePix) {

}
