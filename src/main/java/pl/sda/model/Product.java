package pl.sda.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.plantsEnum.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max=100, message = "Title size must be at least 3 characters and max 100 characters")
    private String title;

    @Column(length = 1000)
    private String description;


    private String miniature; // url? w jaki sposob mozemy zrealizowac

    private String category; //co to znaczy ze tu ma byc encja

    private Double price;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Size(min = 3, max=100, message = "Author size must be at least 3 characters and max 100 characters")
    private String author;

}
