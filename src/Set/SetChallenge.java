package Set;

import java.util.*;

enum Priority {
    HIGH, MED, LOW
}

enum Status {
    IN_QUEUE, ASSIGNED, IN_PROGRESS
}

// Task sınıfı
class Task {
    private String project;
    private String description;
    private String assignee;
    private Priority priority;
    private Status status;

    public Task(String project, String description, String assignee, Priority priority, Status status) {
        this.project = project;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
    }

    public String getProject() {
        return project;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignee() {
        return assignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task [project=" + project + ", description=" + description + ", assignee=" + assignee + ", priority="
                + priority + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        return Objects.equals(project, other.project) && Objects.equals(description, other.description);
    }
}

// TaskData sınıfı
class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;

    public TaskData() {
        annsTasks = new HashSet<>();
        bobsTasks = new HashSet<>();
        carolsTasks = new HashSet<>();
    }

    public void addTask(Task task) {
        if ("ann".equalsIgnoreCase(task.getAssignee())) {
            annsTasks.add(task);
        } else if ("bob".equalsIgnoreCase(task.getAssignee())) {
            bobsTasks.add(task);
        } else if ("carol".equalsIgnoreCase(task.getAssignee())) {
            carolsTasks.add(task);
        }
    }

    public Set<Task> getTasks(String assignee) {
        switch (assignee.toLowerCase()) {
            case "ann":
                return annsTasks;
            case "bob":
                return bobsTasks;
            case "carol":
                return carolsTasks;
            case "all":
                Set<Task> allTasks = new HashSet<>(annsTasks);
                allTasks.addAll(bobsTasks);
                allTasks.addAll(carolsTasks);
                return allTasks;
            default:
                return new HashSet<>();
        }
    }

    public Set<Task> getUnion(Set<Task> set1, Set<Task> set2) {
        Set<Task> union = new HashSet<>(set1);
        union.addAll(set2);
        return union;
    }

    public Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersect = new HashSet<>(set1);
        intersect.retainAll(set2);
        return intersect;
    }

    public Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }
}

public class SetChallenge {
    public static void main(String[] args) {

        TaskData taskData = new TaskData();


        Task task1 = new Task("Project A", "Task 1", "ann", Priority.HIGH, Status.ASSIGNED);
        Task task2 = new Task("Project B", "Task 2", "bob", Priority.MED, Status.IN_PROGRESS);
        Task task3 = new Task("Project C", "Task 3", "carol", Priority.LOW, Status.IN_QUEUE);
        Task task4 = new Task("Project A", "Task 4", "ann", Priority.MED, Status.IN_PROGRESS);
        Task task5 = new Task("Project B", "Task 5", "bob", Priority.LOW, Status.IN_QUEUE);


        taskData.addTask(task1);
        taskData.addTask(task2);
        taskData.addTask(task3);
        taskData.addTask(task4);
        taskData.addTask(task5);


        Set<Task> allTasks = taskData.getTasks("all");
        System.out.println("Tüm çalışanların üzerindeki tasklar:");
        for (Task task : allTasks) {
            System.out.println(task);
        }


        Set<Task> annsTasks = taskData.getTasks("ann");
        System.out.println("\nAnn'ın üzerindeki tasklar:");
        for (Task task : annsTasks) {
            System.out.println(task);
        }

        Set<Task> bobsTasks = taskData.getTasks("bob");
        System.out.println("\nBob'ın üzerindeki tasklar:");
        for (Task task : bobsTasks) {
            System.out.println(task);
        }

        Set<Task> carolsTasks = taskData.getTasks("carol");
        System.out.println("\nCarol'ın üzerindeki tasklar:");
        for (Task task : carolsTasks) {
            System.out.println(task);
        }


        Set<Task> unassignedTasks = taskData.getDifference(allTasks, taskData.getUnion(annsTasks, taskData.getUnion(bobsTasks, carolsTasks)));
        System.out.println("\nHerhangi bir çalışana atanmamış tasklar:");
        for (Task task : unassignedTasks) {
            System.out.println(task);
        }

        Set<Task> assignedToMultiple = taskData.getIntersect(annsTasks, taskData.getIntersect(bobsTasks, carolsTasks));
        System.out.println("\nBirden fazla çalışana atanmış tasklar:");
        for (Task task : assignedToMultiple) {
            System.out.println(task);
        }
    }
}
