package by.voloshchuk.servlet.command;

public class CommandRouter {

    public CommandRouter(RouterType routerType, String path) {
        this.routerType = routerType;
        this.path = path;
    }

    private RouterType routerType;

    private String path;

    public enum RouterType {
        FORWARD,
        REDIRECT
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
