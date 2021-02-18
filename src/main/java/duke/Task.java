package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean getIsDone(){
        return isDone;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    public void printTask(){
        System.out.printf("[%s] %s\n",getStatusIcon(), getDescription());
    }
}