package com.wolfhack.user.query.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "user")
public class UserEntity {

	@Id
	@Column(name = "user_id")
	private Long id;

	@Column(name = "fist_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "registered")
	private LocalDate registered;

	@Override
	public final boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null) {
			return false;
		}

		Class<?> oEffectiveClass = object instanceof HibernateProxy ?
				((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
				((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();

		if (thisEffectiveClass != oEffectiveClass) {
			return false;
		}

		UserEntity that = (UserEntity) object;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ?
				((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
