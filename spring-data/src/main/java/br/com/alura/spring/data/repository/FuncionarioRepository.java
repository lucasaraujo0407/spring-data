package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, 
		JpaSpecificationExecutor<Funcionario> {

	List<Funcionario> findByNome(String nome);
	
	@Query("select f from Funcionario f where f.nome = :nome and f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeDataContratacaoSalarioMaior(String nome, Double salario, LocalDate data);
	
	@Query(value = "select * from funcionarios f where f.data_contratacao>= :data",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "select f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
