package hello.advanced.app.self;

public interface SelfLogTrace {
    public SelfTraceId begin(String message);
    public SelfTraceId end(String message);
    public SelfTraceId exception(String message);
}
