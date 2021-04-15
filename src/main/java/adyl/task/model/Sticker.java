package adyl.task.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sticker")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Sticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sticker_pack_id")
    @NotFound(action = NotFoundAction.EXCEPTION)
    @NotNull(message = "StickerId is mandatory")
    private StickerPack stickerPackId;

    @OneToOne
    @JoinColumn(name = "sticker_image")
    @NotFound(action = NotFoundAction.EXCEPTION)
    @NotNull(message = "StickerImage is mandatory")
    private Image stickerImage;
}
