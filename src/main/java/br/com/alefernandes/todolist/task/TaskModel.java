package br.com.alefernandes.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

                       
    /**
     * Uma tarefa tem que ter:
     * 
     * ID (ID_USUARIO)
     * Usuário
     * Descrição
     * Título
     * Data de início
     * Data de término
     * Prioridade
     */


@Data
@Entity(name = "tb_tasks")     
public class TaskModel {
   
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    
    private UUID idUser;
}
