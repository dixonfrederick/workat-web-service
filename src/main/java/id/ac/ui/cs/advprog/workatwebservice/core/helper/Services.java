package id.ac.ui.cs.advprog.workatwebservice.core.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Services {
    public static String WORD_SERVICE_URL;

    @Autowired
    public Services(@Value("${service.word_service_url}") String wordServiceUrl) {
        Services.WORD_SERVICE_URL = wordServiceUrl;
    }
}
