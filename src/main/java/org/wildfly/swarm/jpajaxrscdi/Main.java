package org.wildfly.swarm.jpajaxrscdi;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;
import org.wildfly.swarm.jpajaxrscdi.dto.MessageDTO;
import org.wildfly.swarm.jpajaxrscdi.jpa.DBEntity;
import org.wildfly.swarm.jpajaxrscdi.jpa.DTOable;
import org.wildfly.swarm.jpajaxrscdi.jpa.Idable;
import org.wildfly.swarm.jpajaxrscdi.jpa.MessageEntity;
import org.wildfly.swarm.jpajaxrscdi.resource.MessageResource;
import org.wildfly.swarm.jpajaxrscdi.service.MessageService;
import org.wildfly.swarm.jpajaxrscdi.util.EntityConverter;

public class Main {
    public static void main(String[] args) throws Exception {
        Swarm swarm = new Swarm();

        swarm.fraction(new DatasourcesFraction()
                .dataSource("MyDS", (ds) -> {
                    ds.driverName("h2");
                    ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
                    ds.userName("sa");
                    ds.password("sa");
                })
        );

        swarm.fraction(new JPAFraction().defaultDatasource("jboss/datasources/MyDS"));
        swarm.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addClasses(MessageEntity.class);
        deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
        deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/beans.xml", Main.class.getClassLoader()), "classes/META-INF/beans.xml");
        deployment.addResource(MessageResource.class);
        deployment.addResource(MessageService.class);
        deployment.addResource(MessageEntity.class);
        deployment.addResource(DBEntity.class);
        deployment.addResource(DTOable.class);
        deployment.addResource(Idable.class);
        deployment.addResource(MessageDTO.class);
        deployment.addResource(EntityConverter.class);
        deployment.addAllDependencies();

        swarm.deploy(deployment);
    }
}
