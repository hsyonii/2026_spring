package example.day0923.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day0923.model.entity.ApiEntity;

@Repository 
public interface ApiRepository extends JpaRepository<ApiEntity,Integer>{

}
