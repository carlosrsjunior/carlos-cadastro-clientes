package carlos.clientes.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import carlos.clientes.entity.Cliente;

/**
 * Interface para persistência de Clientes do JpaRepository.
 *  
 * @author Carlos
 * 
 */
@Transactional(readOnly = true)
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNome(String nome);
	
	List<Cliente> findByNomeLike(String nome);
	
	Cliente findByCpf(String cpf);
	
	Cliente findByEmail(String email);


}
