package org.wildfly.swarm.jpajaxrscdi.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;

public interface DTOable<D extends Serializable> extends Serializable {

    public D toDTO();

    public void fromDTO(D dto) throws JsonProcessingException;

}