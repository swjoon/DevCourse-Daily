package config;

import controller.WiseSayingController;
import repository.WiseSayingRepository;
import repository.WiseSayingRepositoryImpl;
import service.WiseSayingService;
import servlet.WiseSayingServlet;

public class AppConfig {
    public WiseSayingServlet wiseSayingServlet() {
        return new WiseSayingServlet(wiseSayingController());
    }

    public WiseSayingController wiseSayingController() {
        return new WiseSayingController(wiseSayingService());
    }

    public WiseSayingService wiseSayingService() {
        return new WiseSayingService(wiseSayingRepository());
    }

    public WiseSayingRepository wiseSayingRepository() {
        return new WiseSayingRepositoryImpl();
    }
}
