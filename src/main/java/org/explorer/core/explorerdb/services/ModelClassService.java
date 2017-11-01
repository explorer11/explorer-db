package org.explorer.core.explorerdb.services;

import org.explorer.core.explorerdb.model.ModelClass;

public interface ModelClassService {

	ModelClass create(ModelClass modelClass);

	ModelClass update(ModelClass modelClass);

	ModelClass delete(ModelClass modelClass);
	
	ModelClass getById(String id);
}
