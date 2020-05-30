package ru.vtb.mobilebanking.is.nt.coreapigatewaymock;

public class Routes {
    private RouteInfo[] routes;

    public RouteInfo[] getRoutes() {
        return routes;
    }

    public void setRoutes(RouteInfo[] routes) {
        this.routes = routes;
    }

    public static class RouteInfo {
        private String path, file;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }
}
