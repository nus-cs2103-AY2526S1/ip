package nacho.commons;

/**
 * Class to encapsulate the result pair of a query
 * Has the following properties:
 * - reply: String reply to be sent to user
 * - isError: boolean flag to indicate if the reply is an error message
 */
public class QueryResult {
    private String reply;
    private boolean isError;

    /**
     * Constructor for QueryResult
     * @param reply String reply to be sent to user
     * @param isError boolean flag to indicate if the reply is an error message
     */
    public QueryResult(String reply, boolean isError) {
        this.reply = reply;
        this.isError = isError;
    }

    /**
     * @return String reply to be sent to user
     */
    public String getReply() {
        return reply;
    }

    /**
     * @return boolean flag to indicate if the reply is an error message
     */
    public boolean checkIfError() {
        return isError;
    }
}
