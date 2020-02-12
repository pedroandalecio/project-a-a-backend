package br.com.acai.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import br.com.acai.entities.Size;

@Repository
public interface SizeRepository extends PagingAndSortingRepository<Size, Integer> {

}
