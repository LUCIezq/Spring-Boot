package com.sastreria.gestiondeprecios.users;

import lombok.Builder;

@Builder
public record UserDetailResponse(Long id, String nombre) {
}
