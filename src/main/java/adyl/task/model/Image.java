package adyl.task.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_name")
    @NotNull(message = "Name is mandatory")
    private byte[] imageName;

    @Column
    @NotNull(message = "Data is mandatory")
    private LocalDate data;

}
