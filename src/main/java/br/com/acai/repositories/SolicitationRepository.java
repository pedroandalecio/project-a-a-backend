package br.com.acai.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.com.acai.entities.Solicitation;

@Repository
public interface SolicitationRepository extends PagingAndSortingRepository<Solicitation, Integer> {

}
