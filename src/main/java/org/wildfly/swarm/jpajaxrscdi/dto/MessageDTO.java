package org.wildfly.swarm.jpajaxrscdi.dto;

import javax.enterprise.inject.Model;
import java.io.Serializable;

@Model
public class MessageDTO implements Serializable {

    private Number id;
    private String text;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageDTO that = (MessageDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        return text != null ? text.equals(that.text) : that.text == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
