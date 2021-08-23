package adyl.task.model;

import adyl.task.type.MessageType;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    @NotFound(action = NotFoundAction.EXCEPTION)
    @NotNull(message = "Chat_id is mandatory")
    private Chat chat_id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @NotFound(action = NotFoundAction.EXCEPTION)
    @NotNull(message = "Sender_id is mandatory")
    private Account sender_id;

    @OneToOne
    @JoinColumn(name = "message_reply")
    @NotFound(action = NotFoundAction.IGNORE)
    private Message message_reply;

    @Enumerated(EnumType.STRING)
    @NotFound(action = NotFoundAction.EXCEPTION)
    @NotNull(message = "MessageType is mandatory")
    private MessageType messageType;

    @Column
    private String massage;


    @OneToOne
    @JoinColumn(name = "sticker_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Sticker sticker_id;

    @Column(nullable = false)
    @NotNull(message = "DateTime is mandatory")
    private LocalDateTime datetime;
}
