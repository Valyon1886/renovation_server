package com.example.jobs.models

import jakarta.persistence.*

@Entity
@Table(name="users")
data class User(
    @Column var idToken: String?, /*идентификатор пользователя в Firebase*/
    @Column var firstName: String?, /*имя бригадира*/
    @Column var secondName: String?, /*отчество бригадира*/
    @Column var lastName: String?, /*фамилия бригадира*/
    @Column var imgSrc: String?, /*изображение профиля*/
    @OneToMany
    @Column
    var completedTasks: MutableList<Job>?, /*список выполненных заданий*/
    @OneToMany
    @Column
    var jobs: MutableList<Job>?, /*списко выполняемых заданий*/
    @OneToMany
    @Column
    var employers: MutableList<Employer>?, /*список сотрудников в бригаде*/
    @Id @GeneratedValue val id: Long? = null /*идентификатор пользователя*/
)

{
    constructor() : this("", "", "", "", "", mutableListOf(), mutableListOf(), mutableListOf()) {

    }
}