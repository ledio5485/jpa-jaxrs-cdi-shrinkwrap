package org.wildfly.swarm.jpajaxrscdi.service;

import org.wildfly.swarm.jpajaxrscdi.dto.MessageDTO;
import org.wildfly.swarm.jpajaxrscdi.jpa.MessageEntity;
import org.wildfly.swarm.jpajaxrscdi.util.EntityConverter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class MessageService {

    @PersistenceContext
    private EntityManager entityManager;

    private final EntityConverter entityConverter = new EntityConverter();

    public List<MessageDTO> getMessages() {
        return entityConverter.convertList(entityManager.createNamedQuery("Message.findAll", MessageEntity.class).getResultList());
    }

    public MessageDTO getMessage(long messageId) {
        return entityManager.createNamedQuery("Message.findById", MessageEntity.class)
                .setParameter("messageId", messageId)
                .getSingleResult().toDTO();
    }

    @Transactional
    public MessageDTO saveMessage(MessageDTO message) throws Exception {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.fromDTO(message);
        MessageEntity retVal = entityManager.merge(messageEntity);
        return retVal.toDTO();
    }
}
