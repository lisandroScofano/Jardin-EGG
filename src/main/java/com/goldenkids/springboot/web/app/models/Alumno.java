package com.goldenkids.springboot.web.app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Alumno {

    @Id
    private int dni;

    private String nombre;
    private String apellido;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @ManyToMany
    private List<Usuario> contacto;
    @ManyToOne
    private Salita salita;

    public Alumno() {
        this.contacto = new ArrayList<>();
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Usuario> getContacto() {
        return contacto;
    }

    public void setContacto(List<Usuario> contacto) {
        this.contacto = contacto;
    }

    public Salita getSalita() {
        return salita;
    }

    public void setSalita(Salita salita) {
        this.salita = salita;
    }

    public void agregarPadre(Usuario padre) {
        this.contacto.add(padre);
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public String toString() {
        return "Alumno{" + "dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", fechaBaja=" + fechaBaja + ", contacto=" + contacto + ", salita=" + salita + '}';
    }

    
}
