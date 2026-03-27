package sunday;

//Class generated with the assist of ChatGPT
public class Response {
    private final String text;
    private final boolean isError;

    public Response(String text, boolean isError) {
        this.text = text;
        this.isError = isError;
    }

    public String getText() {
        return text;
    }

    public boolean isError() {
        return isError;
    }

    public static Response ok(String text) { return new Response(text, false); }
    public static Response error(String text) { return new Response(text, true); }
}