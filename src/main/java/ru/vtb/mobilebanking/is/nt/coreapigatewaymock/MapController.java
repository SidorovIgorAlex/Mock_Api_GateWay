package ru.vtb.mobilebanking.is.nt.coreapigatewaymock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController //?
@RequestMapping("/")
public class MapController {
    @Value("${routesDir}")//Папка с ответами
    String routesDir = "";
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, String> responseMap = new HashMap<>(); //Объявляем мапу в которой будут хранится все response
    /**
     * Объявляем мапу в которой будут хранится все response
     */
    private Map<String, Long> delayMap = new HashMap<>();
    protected static String resp = null;
    Logger logger = LoggerFactory.getLogger(MapController.class);
    static boolean delayStatus = true;
    @PostConstruct
    public void postConstruct() throws IOException {
        Routes routes = objectMapper.readValue(Paths.get(routesDir + "/routes/routes.json").toFile(), Routes.class);
        for (Routes.RouteInfo routeInfo : routes.getRoutes()) {
                String content = new String(Files.readAllBytes(Paths.get(routesDir + "/" + routeInfo.getFile())), StandardCharsets.UTF_8);
                responseMap.put(routeInfo.getPath(), content); //путь , содержимое файла
                delayMap.put(routeInfo.getPath(), Long.parseLong(routeInfo.getDelay()));
        }
    }

    @RequestMapping(value = "**", produces = "application/json")
    public String all(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder(req.getMethod());
        sb.append(" ").append(req.getRequestURI());


        if (!StringUtils.isEmpty(req.getQueryString())) {
            sb.append("?").append(req.getQueryString());
        }
        try
        {
            Thread.sleep(delayMap.get(sb.toString()));
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        return responseMap.get(sb.toString());
    }
}
