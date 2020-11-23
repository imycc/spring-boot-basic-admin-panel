package com.imyc.SBAP.Base.dataprocess;

public interface DtoModelConvertor<T, DTO> {

    T convert(DTO dto);

}
