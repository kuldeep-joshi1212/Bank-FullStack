package com.bank.home.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

@Data
@MappedSuperclass
public class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "created", updatable = false, nullable = false)
	private ZonedDateTime created;
	
	@Column(name = "updated", nullable = false)
	private ZonedDateTime updated;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	
	@PrePersist
	private void prePersist() {
		ZonedDateTime now = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
		if(created == null) {
			created = now;
		}
		updated = now;
	}
	
	@PreUpdate
	private void preUpdate() {
		updated = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof BaseEntity))
			return false;
		BaseEntity that = (BaseEntity) o;
		return Objects.equals(id, that.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
	
}
