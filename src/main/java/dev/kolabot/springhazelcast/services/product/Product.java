package dev.kolabot.springhazelcast.services.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable {

    @Id
    private Long id;
    private String name;
    private String description;

}
