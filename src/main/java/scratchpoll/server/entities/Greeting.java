package scratchpoll.server.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Greeting implements Serializable {
    @Id @GeneratedValue private int id;
    @Basic private String message;
    @Basic private String language;

    public Greeting() {}
    public Greeting(String message, String language) {
        this.message = message;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String toString() {
        return "Greeting id=" + id + ", message=" + message + ", language=" + language;
    }
}