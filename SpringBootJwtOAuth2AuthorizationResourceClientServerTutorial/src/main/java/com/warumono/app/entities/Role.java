package com.warumono.app.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.warumono.app.enums.Authority;
import com.warumono.app.enums.converters.AuthorityConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor(staticName = "on")
@AllArgsConstructor(staticName = "of")
@Builder
@Setter
@Getter
@Table
(
//	 name = "role", 
	uniqueConstraints = 
	{
		@UniqueConstraint(name = "UNIQ_NAME__USER_SEQ_IN_ROLE", columnNames = { "name", "user_seq" })
	}
)
@Entity
public class Role extends AuditingEntity
{
	@NonNull
	@Convert(converter = AuthorityConverter.class)
	@Column(nullable = false, updatable = false, length = 20, columnDefinition = "VARCHAR(20) COMMENT '권한'")
	private Authority name;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(nullable = false, updatable = false, referencedColumnName = "seq", name = "user_seq", foreignKey = @ForeignKey(name = "FKEY_USER_SEQ_IN_ROLE"))
	private User user;
}
