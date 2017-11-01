package org.explorer.core.explorerdb.services.impl;

import static org.fest.assertions.Assertions.assertThat;

import org.explorer.core.explorerdb.AbstractSpringTest;
import org.explorer.core.explorerdb.model.ModelClass;
import org.explorer.core.explorerdb.repositories.ModelClassRepository;
import org.explorer.core.explorerdb.services.ModelClassService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelClassServiceImplTest extends AbstractSpringTest {

	@Autowired
	private ModelClassService modelClassService;

	@Autowired
	private ModelClassRepository modelClassRepository;
	
	@Test
	public void testGetById() {
		ModelClass modelClass = modelClassService.getById("1");
		assertThat(modelClass.getId()).isNotNull();
		assertThat(modelClass.getCode()).isEqualTo("code1");
		assertThat(modelClass.getTitre()).isEqualTo("titre1");
	}

	@Test
	public void testCreate() {
		ModelClass modelClass = new ModelClass();
		modelClass.setCode("b1");
		modelClass.setTitre("titre");

		ModelClass created = modelClassService.create(modelClass);

		assertThat(created.getId()).isNotNull();
	}

	@Test
	public void testUpdate() {
		ModelClass modelClass = new ModelClass();
		modelClass.setId("1");
		modelClass.setCode("A1");
		modelClass.setTitre("nouveau titre");
		
		modelClassService.update(modelClass);
		ModelClass updated = modelClassRepository.findOne(modelClass.getId());
		assertThat(updated.getTitre()).isEqualTo("nouveau titre");
	}

	@Test
	public void testDelete() {
		ModelClass modelClass = new ModelClass();
		modelClass.setId("1");
		modelClassService.delete(modelClass);

		ModelClass deleted = modelClassRepository.findOne(modelClass.getId());
		assertThat(deleted).isNull();
	}
}
