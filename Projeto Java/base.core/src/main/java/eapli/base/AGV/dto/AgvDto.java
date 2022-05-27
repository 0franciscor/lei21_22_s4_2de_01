package eapli.base.AGV.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class AgvDto {

    public String id;

    public AgvDto(String id) {
        this.id = id;
    }

    public AgvDto(){

    }
}
