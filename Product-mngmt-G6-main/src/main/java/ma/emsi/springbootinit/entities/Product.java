package ma.emsi.springbootinit.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//@Data = @Setter + @Getter + ...
@Data @NoArgsConstructor @AllArgsConstructor
@Entity //Pour la création de cette classe en table
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String ref;
    private Integer code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date prodDate;
    private Float price;

//    @ManyToOne
//    @JoinColumn(name = "provider_id")
//    private Provider provider;



}

