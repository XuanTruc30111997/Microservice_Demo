package truc.microservice.catalog_service.automation;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

public class BaseCatalogServiceSteps {
    protected static final String LOCALHOST = "http://localhost:";
    protected static final String CATALOG_DETAILS_API = "/api/catalog_details";
    protected static final String SEARCH_API = "/";
    protected static final String ID_CATALOG_KEY = "idCatalog";
    protected static final String PORT = "8088";

    private WebTestClient client;

    public void setClient(WebTestClient client) {
        this.client = client;
    }

    public WebTestClient getClient() {
        return this.client;
    }

}
