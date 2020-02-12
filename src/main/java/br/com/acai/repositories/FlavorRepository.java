package br.com.acai.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.com.acai.entities.Flavor;

@Repository
public interface FlavorRepository extends PagingAndSortingRepository<Flavor, Integer> {

}
