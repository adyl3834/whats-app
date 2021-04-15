package adyl.task.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sticker_pack")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StickerPack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "PackName is mandatory")
    private String packName;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NotFound(action = NotFoundAction.EXCEPTION)
    @NotNull(message = "OwnerId is mandatory")
    private Account ownerId;

    @ManyToMany
    @JoinTable(name = "account_sticker_pack",
            joinColumns = {@JoinColumn(name = "sticker_pack_id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id")})
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Account> accounts = new HashSet<>();

}
