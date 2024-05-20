package ma.emsi.springbootinit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //Pour la création de cette classe en table
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "provider")
//    private List<Product> products;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;

}
