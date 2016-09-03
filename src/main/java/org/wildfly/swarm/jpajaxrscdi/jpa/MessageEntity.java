package org.wildfly.swarm.jpajaxrscdi.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.wildfly.swarm.jpajaxrscdi.dto.MessageDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE_TABLE")
@NamedQueries({
        @NamedQuery(name = "Message.findAll", query = "SELECT m FROM MessageEntity m"),
        @NamedQuery(name = "Message.findById", query = "SELECT m FROM MessageEntity m WHERE m.id = :messageId")
})
@XmlRootElement
public class MessageEntity extends DBEntity implements DTOable<MessageDTO> {
    private static final long serialVersionUID = 1L;

    @Column(length = 1024)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public MessageDTO toDTO() {
        return new MessageDTO(getId(), text);
    }

    @Override
    public void fromDTO(MessageDTO message) throws JsonProcessingException {
        setId((Long) message.getId());
        setText(message.getText());
    }

    @Override
    public String toString() {
        return text + " " + getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof MessageEntity)) {
            return false;
        }
        MessageEntity that = (MessageEntity) obj;
        if (that.text.equals(this.text) && that.getId() == this.getId()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.text);
    }

}
