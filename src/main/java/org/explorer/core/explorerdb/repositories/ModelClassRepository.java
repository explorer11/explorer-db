package org.explorer.core.explorerdb.repositories;

import java.util.Collection;

import org.explorer.core.explorerdb.model.ModelClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModelClassRepository extends CrudRepository<ModelClass, String> {
	
	long countByDuree(Integer duree);
	
	Collection<ModelClass> findByCode(String code);
	
	Collection<ModelClass> findByParentModelClass_Id(String id);
}
