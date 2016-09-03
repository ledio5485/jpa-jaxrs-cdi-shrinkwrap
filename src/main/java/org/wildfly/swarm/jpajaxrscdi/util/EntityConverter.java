package org.wildfly.swarm.jpajaxrscdi.util;

import org.wildfly.swarm.jpajaxrscdi.jpa.DTOable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntityConverter {

    public <D extends Serializable, E extends DTOable<D>> List<D> convertList(final List<E> entities) {
        return new ArrayList<D>(entities.size()) {
            {
                for (E e : entities) {
                    add(e.toDTO());
                }
            }
        };
    }

}