package br.com.acai.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.com.acai.entities.Personalize;

@Repository
public interface PersonalizeRepository extends PagingAndSortingRepository<Personalize, Integer> {

}
