package br.com.acai.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.acai.entities.SolicitationPersonalize;

@Repository
public interface SolicitationPersonalizeRepository extends PagingAndSortingRepository<SolicitationPersonalize, Integer> {

}
