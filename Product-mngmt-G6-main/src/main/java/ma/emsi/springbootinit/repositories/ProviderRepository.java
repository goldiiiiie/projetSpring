package ma.emsi.springbootinit.repositories;

import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider,Long> {
}
