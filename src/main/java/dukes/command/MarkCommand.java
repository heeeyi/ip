package dukes.command;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;

import dukes.task.Task;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MarkCommand extends Command {
    protected int action;

    public MarkCommand(boolean isExit, boolean isValid,
                      String header, String body, int action) {
        // here body = index
        super(isExit, isValid, header, body);
        this.action = action;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int index = Integer.parseInt(body);
        try {
            Task theTask = taskList.get(index - 1);
            if (this.action == 0) {
                theTask.setDone();
            } else {
                theTask.setUnDone();
            }
            ui.showMark(theTask, action);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("You have entered an invalid index.");
        }
    }
}
