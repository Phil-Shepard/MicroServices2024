package org.example.taskservice.models.entities;

import jakarta.persistence.*;
import org.example.models.enums.TaskState;

import java.time.LocalDate;

/**
 * @author Tribushko Danil
 * @since 12.01.2025
 * Класс задачи
 */
@Entity
@Table(name = "tasks")
public class Task extends EntityVersion {
    @Column(nullable = false, name = "title")
    private String title;
    @Column(nullable = false, name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "state")
    private TaskState state;
    @Column(nullable = false, name = "deadline")
    private LocalDate deadline;
    @JoinColumn(nullable = false, name = "tag")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private TaskTag tag;

    public Task() {
        super();
    }

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String description;
        private TaskState state;
        private LocalDate deadline;
        private TaskTag tag;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder state(TaskState state) {
            this.state = state;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder tag(TaskTag tag) {
            this.tag = tag;
            return this;
        }

        public Task build() {
            Task task = new Task();
            task.title = this.title;
            task.description = this.description;
            task.state = this.state;
            task.deadline = this.deadline;
            task.tag = this.tag;

            return task;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskState getState() {
        return state;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public TaskTag getTag() {
        return tag;
    }
}
