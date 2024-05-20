package ma.emsi.springbootinit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "category")
//    private List<Product> products;

}
