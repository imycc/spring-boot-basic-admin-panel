package com.imyc.SBAP.Http.role.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
@Accessors(chain=true)
public class RoleUpdateDTO {

    @NotBlank
    private String name;
    private Boolean admin = false;
    @NotEmpty
    private List<Integer> privileges;
}
