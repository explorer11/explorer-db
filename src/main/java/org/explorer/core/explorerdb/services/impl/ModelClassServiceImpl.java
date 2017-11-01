package org.explorer.core.explorerdb.services.impl;

import java.util.Date;

import org.explorer.core.explorerdb.model.ModelClass;
import org.explorer.core.explorerdb.repositories.ModelClassRepository;
import org.explorer.core.explorerdb.services.ModelClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelClassServiceImpl implements ModelClassService {

	private final Logger log = LoggerFactory.getLogger(ModelClassServiceImpl.class);

	@Autowired
	private ModelClassRepository modelClassRepository;

	public ModelClass create(ModelClass modelClass) {
		return save(modelClass);
	}

	public ModelClass update(ModelClass modelClass) {
		return save(modelClass);
	}

	protected ModelClass save(ModelClass modelClass) {

		log.debug("save {}", modelClass.getCode());

		modelClass.setUpdateDate(new Date());
		ModelClass updatedModelClass = modelClassRepository.save(modelClass);

		return updatedModelClass;
	}

	public ModelClass delete(ModelClass modelClass) {
		log.debug("delete {}", modelClass.getId());

		ModelClass deleted = modelClassRepository.findOne(modelClass.getId());
		modelClassRepository.delete(deleted);

		return deleted;
	}

	public ModelClass getById(String id) {
		log.debug("getById {}", id);

		ModelClass modelClass = modelClassRepository.findOne(id);

		return modelClass;
	}
}
