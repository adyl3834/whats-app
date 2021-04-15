package adyl.task.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull(message = "Name is mandatory")
    private String chatName;
    @Column
    private boolean notificationEnable;

    @OneToOne
    @JoinColumn(name = "chat_picture")
    @NotFound(action = NotFoundAction.IGNORE)
    private Image chatPicture;

    @ManyToMany
    @JoinTable(name = "account_chat",
            joinColumns = {@JoinColumn(name = "chat_id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id")})
    private Set<Account> accounts = new HashSet<>();

}
