package agenda.modelo;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "contactos")
@SuppressWarnings("serial")
public class Contacto implements Comparable<Contacto>, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contacto")
	private int idContacto;
	private String nombre;
	@Column(name = "apellido")
	private String apellidos;
	private String apodo;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "tipoVia", column = @Column(name = "tipo_via")), 
		@AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal"))})
	private Domicilio dom;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name = "telefono")
	@CollectionTable(name = "telefonos", joinColumns = {@JoinColumn(name = "fk_contacto")})
	private Set<String> telefonos;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@Column(name = "correo")
	@CollectionTable(name = "correos", joinColumns = {@JoinColumn(name = "fk_contacto")})
	private Set<String> correos;
	
	public Contacto() {
		dom = new Domicilio();
		telefonos = new LinkedHashSet<>(); // LHS: bueno la búsqueda, y además respeta el orden de carga
		correos = new LinkedHashSet<>();
	}
	
	// Con este constructor creo contactos rápidamente
	public Contacto(String nombre, String apellidos, String apodo) {
		this();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.apodo = apodo;
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public Domicilio getDom() {
		return dom;
	}

	public void setDom(Domicilio dom) {
		this.dom = dom;
	}

	public Set<String> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(Set<String> telefonos) {
		this.telefonos = telefonos;
	}

	public Set<String> getCorreos() {
		return correos;
	}

	public void setCorreos(Set<String> correos) {
		this.correos = correos;
	}
	
	public void addTelefonos(String... telefonos) {
		for(int i = 0; i < telefonos.length; i++) {
			this.telefonos.add(telefonos[i]);
		}
	}
	
	public void addCorreos(String... correos) {
		for(int i = 0; i < correos.length; i++) {
			addCorreo(correos[i]);
		}
	}
	
	public void addTelefono(String telefono) {
		this.telefonos.add(telefono);
	}
	
	public void addCorreo(String correo) {
		this.correos.add(correo);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;
		if (this.getClass() != o.getClass()) return false;
		Contacto otro = (Contacto)o;
		return this.idContacto == otro.idContacto;
	}
	
	@Override
	public int hashCode() {
		return idContacto;
	}

	@Override
	public String toString() {
		return "[" + idContacto + ", " + nombre + ", " + apellidos + ", " + apodo + "]";
	}

//	Compara OK independientemente de las mayus o minus
//		no ordena correctamente los caracteres especiales á ñ etc
//	@Override
//	public int compareTo(Contacto o) {
////		ordenar por contacto: 
////		return this.idContacto - o.idContacto;
//		
//		if(this.equals(o)) return 0;
//		
////		ordenar por nombre, a lo beshtia:
////		return this.nombre.compareTo(o.nombre);
//		
////		ordenar por nombre pero bien:
//		return (this.nombre + this.idContacto).toLowerCase().compareTo((o.nombre + o.idContacto).toLowerCase());
//	}
	
	@Override
	public int compareTo(Contacto o) {
		if(this.equals(o)) return 0;
		Collator col = Collator.getInstance(new Locale("es"));	// new Locale es un objeto anónimo
		
		return col.compare(this.nombre + this.idContacto, o.nombre +  o.idContacto);
	}
	
	public static Comparator<Contacto> getIdComparator(){
		return new IdComparator();
	}
	
	private static class IdComparator implements Comparator<Contacto>{
		@Override
		public int compare(Contacto o1, Contacto o2) {
			return o1.getIdContacto()-o2.getIdContacto();
		}
	}
	
}
