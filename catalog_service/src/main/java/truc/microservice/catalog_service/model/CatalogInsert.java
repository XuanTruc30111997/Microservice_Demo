package truc.microservice.catalog_service.model;

public class CatalogInsert {
    private String name;

    public CatalogInsert(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
