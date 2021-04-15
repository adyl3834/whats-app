package adyl.task.repository;


import adyl.task.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query(value = "select * from Account a where a.name = ?1", nativeQuery = true)
    Account getByName(String name);

    @Query(value = "select * from Account a where a.id = ?1", nativeQuery = true)
    Account getById(Long id);
}
