package shaduke.ui;

import shaduke.clients.Client;
import shaduke.clients.ClientList;
import shaduke.tasks.Task;
import shaduke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that deals with interactions with the user throught the GUI.
 */
public class GuiUi extends Ui {
    private final List<String> messages;

    public GuiUi() {
        messages = new ArrayList<>();
    }

    private void addMessage(String message) {
        messages.add(message);
    }

    public String getOutput() {
        StringBuilder sb = new StringBuilder();
        for (String msg : messages) {
            sb.append(msg).append("\n");
        }
        messages.clear();
        return sb.toString();
    }

    @Override
    public void showGreeting() {
        addMessage("Hail, noble friend! I am thy humble servant, acow123_bot.");
        addMessage("Pray, reveal thy desire, that I may serve thee forthwith.");
    }

    @Override
    public void showAdios() {
        addMessage("Fare thee well, gentle soul. May Morpheus grant thee visions most sweet.");
        addMessage("Till we meet upon the morrow's stage of deeds and tasks.");
    }

    @Override
    public void showError(String errorMessage) {
        addMessage("Alas! A misfortune hath occurred: " + errorMessage);
    }

    @Override
    public void showAdded(Task task, int size) {
        addMessage("Lo! A task is wrought and added to thy ledger:");
        addMessage("  " + task);
        addMessage("Now thou possesseth " + size + " noble charges to fulfill.");
    }

    @Override
    public void showList(TaskList tasks) {
        addMessage("Behold, the labours thou hast undertaken:");
        for (int i = 0; i < tasks.size(); i++) {
            addMessage((i + 1) + ". " + tasks.get(i));
        }
    }

    @Override
    public void showMarked(Task task) {
        addMessage("Huzzah! This task hath been vanquished:");
        addMessage("  " + task);
    }

    @Override
    public void showUnmarked(Task task) {
        addMessage("Soft! This task remains unfulfilled, yet awaits thy hand:");
        addMessage("  " + task);
    }

    @Override
    public void showDeleted(Task task, int size) {
        addMessage("Alas! This task hath met its end, consigned to oblivion:");
        addMessage("  " + task);
        addMessage("Yet thou hast " + size + " tasks still in thy ledger.");
    }

    @Override
    public void showFind(TaskList tasks, String toSearch) {
        addMessage("Tasks that doth match thy query:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(toSearch)) {
                addMessage((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    @Override
    public void showAddClient(Client client, ClientList clients) {
        addMessage("Hail! " + client + " hath entered thy service.");
        addMessage("Thou now commandest " + clients.size() + " loyal clients.");
    }

    @Override
    public void showAssignClient(Task task, Client client) {
        addMessage(client + " hath been assigned unto this noble task:");
        addMessage("  " + task);
    }

    @Override
    public void showClients(ClientList clients) {
        addMessage("Behold, thy valiant clients:");
        for (int i = 0; i < clients.size(); i++) {
            addMessage((i + 1) + ". " + clients.get(i));
        }
    }

    @Override
    public void showDeleteClient(Client client) {
        addMessage("Alas! " + client + " hath departed from thy fold.");
    }

    @Override
    public void showLeave(int index, Client client) {
        addMessage(client + " hath forsaken the task numbered " + (index + 1));
    }

    @Override
    public void showClientTasks(TaskList tasks, Client client) {
        addMessage("Tasks wherein " + client + " doth partake:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getClient() != null && task.getClient().equals(client)) {
                task.deleteClient();
                addMessage((i + 1) + ". " + task);
                task.addClient(client);
            }
        }
    }
}

