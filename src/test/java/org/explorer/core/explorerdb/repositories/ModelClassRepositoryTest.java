package org.explorer.core.explorerdb.repositories;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.explorer.core.explorerdb.AbstractSpringTest;
import org.explorer.core.explorerdb.model.ModelClass;
import org.explorer.core.explorerdb.model.ModelClassTheme;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class ModelClassRepositoryTest extends AbstractSpringTest {

	@Autowired
	private ModelClassRepository modelClassRepository;

	@Test
	public void testCreate() {
		ModelClass modelClass = new ModelClass();
		modelClass.setId("2");
		modelClass.setCode("b1");
		modelClass.setTitre("titre1");
		
		ModelClassTheme modelClassTheme = new ModelClassTheme();
		modelClassTheme.setModelClass(modelClass);
		modelClassTheme.setTheme("theme");
		modelClass.setThemes(Stream.of(modelClassTheme).collect(Collectors.toList()));
		
		modelClass = modelClassRepository.save(modelClass);
		assertThat(modelClass.getId()).isNotNull();
		modelClass = modelClassRepository.findOne(modelClass.getId());
		assertThat(modelClass.getThemes()).hasSize(1);
	}
	
	@Test
	public void testCreateParentFormation() {
		ModelClass formation = new ModelClass();
		formation.setId("2");
		formation.setCode("code2");
		formation.setTitre("titre");
		
		ModelClass child = new ModelClass();
		child.setId("1");
		formation.setChildModelClass(
				Stream.of(child).collect(Collectors.toSet()));
		formation = modelClassRepository.save(formation);
		assertThat(formation.getId()).isNotNull();
		formation = modelClassRepository.findOne(formation.getId());
		assertThat(formation.getChildModelClass()).hasSize(1);
		assertThat(formation.getParentModelClass()).hasSize(0);
	}

	@Test
	public void testUpdate() {
		ModelClass modelClass = new ModelClass();
		modelClass.setId("1");
		modelClass.setCode("code12");
		modelClass.setTitre("titre");
		modelClass = modelClassRepository.save(modelClass);
		assertThat(modelClass.getId()).isEqualTo("1");
		assertThat(modelClass.getCode()).isEqualTo("code12");
	}
	
	@Test
	public void testCountByDuree() {
		long countByDuree2 = modelClassRepository.countByDuree(2);
		assertThat(countByDuree2).isEqualTo(2);
		long countByDuree1 = modelClassRepository.countByDuree(1);
		assertThat(countByDuree1).isEqualTo(0);
	}
	
	@Test
	public void testFindByCode() {
		Collection<ModelClass> modelClassCollection1 = modelClassRepository.findByCode("code1");
		assertThat(modelClassCollection1).hasSize(1);
		assertThat(modelClassCollection1.iterator().next().getId()).isEqualTo("1");
		
		Collection<ModelClass> modelClassCollection = modelClassRepository.findByCode("code");
		assertThat(modelClassCollection).hasSize(0);
	}
	
	@Test
	public void testFindByParentModelClass_Id() {
		Collection<ModelClass> modelClassCollection11 = modelClassRepository.findByParentModelClass_Id("11");
		assertThat(modelClassCollection11).hasSize(2);
		assertThat(modelClassCollection11.stream().map(modelClass->modelClass.getId())
				.collect(Collectors.toList())).containsOnly("21", "31");
		
		Collection<ModelClass> modelClassCollection1 = modelClassRepository.findByParentModelClass_Id("1");
		assertThat(modelClassCollection1).hasSize(0);
	}
	

}