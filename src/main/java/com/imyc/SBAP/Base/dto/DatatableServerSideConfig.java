package com.imyc.SBAP.Base.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain=true)
public class DatatableServerSideConfig {

    private int draw;
    private int start;
    private int length;
    private String keyword;

}
