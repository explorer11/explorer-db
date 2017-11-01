package org.explorer.core.explorerdb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "MODELCLASS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "catalogue")
public class ModelClass implements Serializable {

	private static final long serialVersionUID = -7673269991872723369L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private String id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "TITRE")
	private String titre;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modelClass", cascade = CascadeType.ALL)
	private List<ModelClassTheme> themes;
	
	@Column(name = "DUREE")
	private Integer duree;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "LINKED_MODELCLASS", joinColumns = {
			@JoinColumn(name = "childID", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "parentID",
					nullable = false, updatable = false) })
	private Set<ModelClass> parentModelClass = new HashSet<ModelClass>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "parentModelClass")
	private Set<ModelClass> childModelClass = new HashSet<ModelClass>();
	
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<ModelClassTheme> getThemes() {
		return themes;
	}

	public void setThemes(List<ModelClassTheme> themes) {
		this.themes = themes;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Set<ModelClass> getParentModelClass() {
		return parentModelClass;
	}

	public void setParentModelClass(Set<ModelClass> parentModelClass) {
		this.parentModelClass = parentModelClass;
	}

	public Set<ModelClass> getChildModelClass() {
		return childModelClass;
	}

	public void setChildModelClass(Set<ModelClass> childModelClass) {
		this.childModelClass = childModelClass;
	}
}
