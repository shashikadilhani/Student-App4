package com.example.demo.studentappjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "parent")
@JsonIgnoreProperties({"lastModifiedBy" })
public class Parent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column
    private String name;

    @Column
    private int phone;
    @Column
    private String address;

    @Column
    private String relation;

   /* @Column(name="studentid")
    private String studentid;*/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="studentid" , nullable = false)
    private Student student;
/*
    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }*/

    public Parent(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;

    }

   @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", relation='" + relation + '\'' +
                ", student=" + student +
                '}';
    }


/*    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", relation='" + relation + '\'' +
                ", studentid='" + studentid + '\'' +
                ", student=" + student +
                '}';
    }*/

    public Parent(String name, int phone, String address, String relation, Student student) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.relation = relation;
        this.student = student;
    }


  /*  public Parent(String name, int phone, String address, String relation, String studentid, Student student) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.relation = relation;
        this.studentid = studentid;
        this.student = student;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return  id.equals(parent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return phone == parent.phone &&
                Objects.equals(id, parent.id) &&
                Objects.equals(name, parent.name) &&
                Objects.equals(address, parent.address) &&
                Objects.equals(relation, parent.relation) &&
                Objects.equals(student, parent.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, address, relation, student);
    }*/
}
