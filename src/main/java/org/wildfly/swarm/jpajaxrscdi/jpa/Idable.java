package org.wildfly.swarm.jpajaxrscdi.jpa;

import java.io.Serializable;

public interface Idable<N extends Number> extends Serializable {

    public N getId();
}