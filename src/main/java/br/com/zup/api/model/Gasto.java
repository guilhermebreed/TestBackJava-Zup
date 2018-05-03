package br.com.zup.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "Gasto")
public class Gasto implements Serializable {

	private static final long serialVersionUID = -8507622473380945770L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_Gasto", precision = 12, scale = 0)
	private Long id;

	@Column(name = "VALUE", nullable = false)
	private double value;

	@Column(name = "USER_CODE", nullable = false)
	private Long userCode;

	@Column(name = "Gasto_DATE", nullable = false)
	private LocalDateTime gastoDate;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_Categoria", referencedColumnName = "ID_Categoria", nullable = true)
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

	public LocalDateTime getGastoDate() {
		return gastoDate;
	}

	public void setGastoDate(final LocalDateTime GastoDate) {
		this.gastoDate = GastoDate;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(final Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(final Long userCode) {
		this.userCode = userCode;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getValue()).append(getUserCode()).append(getGastoDate())
				.append(getVersion()).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		final Gasto rhs = (Gasto) obj;
		return new EqualsBuilder().append(getId(), rhs.getId()).append(getValue(), rhs.getValue())
				.append(getUserCode(), rhs.getUserCode()).append(getGastoDate(), rhs.getGastoDate())
				.append(getVersion(), rhs.getVersion()).isEquals();
	}
}
