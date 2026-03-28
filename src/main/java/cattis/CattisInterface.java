package cattis;

import cattis.task.TaskList;
import cattis.ui.Ui;

/**
 * Defines the core contract for Cattis application instances.
 * <p>
 * This interface establishes the essential operations that any Cattis application
 * implementation must provide, enabling polymorphic usage across different contexts
 * such as production applications and testing environments.
 * <p>
 * The interface supports the following use cases:
 * <ul>
 *   <li>Production application execution via {@link cattis.Cattis}</li>
 *   <li>Testing scenarios via stub implementations like {@code CattisStub}</li>
 *   <li>Alternative application implementations with different UI or storage backends</li>
 * </ul>
 *
 * <h3>Implementation Requirements:</h3>
 * <p>
 * Implementing classes must ensure that:
 * <ul>
 *   <li>All getter methods return non-null instances</li>
 *   <li>The {@code run()} method handles its own exception management</li>
 *   <li>State consistency is maintained across all operations</li>
 * </ul>
 *
 * @author Kosolpattanadurong Thitiwat
 */
public interface CattisInterface {

    /**
     * Retrieves the task list managed by this application instance.
     * <p>
     * The returned task list contains all tasks currently known to the application
     * and serves as the primary data model for task management operations.
     * Modifications to the returned list should be reflected in the application's
     * persistent storage.
     *
     * <h4>Usage Example:</h4>
     * <pre>{@code
     * CattisInterface app = new Cattis();
     * TaskList tasks = app.getTaskList();
     * int taskCount = tasks.size();
     * }</pre>
     *
     * @return the task list instance, never {@code null}
     * @apiNote The returned TaskList should be the live instance used by the application,
     *          not a copy, to ensure consistency across operations
     */
    TaskList getTaskList();

    /**
     * Retrieves the user interface component for this application instance.
     * <p>
     * The UI component handles all user interactions including input collection,
     * output display, and error reporting. Different implementations may provide
     * different UI implementations (console, GUI, web, etc.).
     *
     * <h4>Usage Example:</h4>
     * <pre>{@code
     * CattisInterface app = new Cattis();
     * Ui userInterface = app.getUi();
     * userInterface.showMessage("Hello, World!");
     * }</pre>
     *
     * @return the user interface instance, never {@code null}
     * @apiNote Implementations should ensure the UI is properly initialized and
     *          ready for use when this method is called
     */
    Ui getUi();

    /**
     * Starts the main application execution loop.
     * <p>
     * This method initiates the primary application workflow, which typically involves:
     * <ol>
     *   <li>Displaying initial welcome messages</li>
     *   <li>Processing user input in a continuous loop</li>
     *   <li>Executing commands and managing application state</li>
     *   <li>Handling errors gracefully without crashing</li>
     *   <li>Displaying farewell messages upon exit</li>
     * </ol>
     *
     * <h4>Execution Characteristics:</h4>
     * <ul>
     *   <li><strong>Blocking:</strong> This method blocks until the application terminates</li>
     *   <li><strong>Exception Safe:</strong> Should handle all exceptions internally</li>
     *   <li><strong>Resource Management:</strong> Should properly clean up resources on exit</li>
     * </ul>
     *
     * <h4>Implementation Notes:</h4>
     * <p>
     * Implementations should:
     * <ul>
     *   <li>Never throw uncaught exceptions to the caller</li>
     *   <li>Provide clear feedback for all user actions</li>
     *   <li>Persist data changes before termination</li>
     *   <li>Handle interrupt signals gracefully</li>
     * </ul>
     *
     * <h4>Testing Considerations:</h4>
     * <p>
     * Test implementations may provide abbreviated execution cycles or
     * non-interactive modes to facilitate automated testing.
     *
     * <h4>Usage Example:</h4>
     * <pre>{@code
     * public static void main(String[] args) {
     *     CattisInterface app = new Cattis();
     *     app.run(); // Starts the interactive session
     *     // Execution continues here only after user exits
     *     System.out.println("Application terminated");
     * }
     * }</pre>
     *
     * @implSpec Implementations must handle their own exception management and
     *           should not propagate exceptions to callers
     * @implNote This method typically runs until the user explicitly requests
     *           application termination through an exit command
     */
    void run();
}
