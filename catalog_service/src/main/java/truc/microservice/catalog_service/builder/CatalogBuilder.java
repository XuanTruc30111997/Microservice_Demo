package truc.microservice.catalog_service.builder;

import truc.microservice.catalog_service.model.Catalog;

public class CatalogBuilder {
    private Catalog catalog;

    public CatalogBuilder() {
        catalog = new Catalog();
    }

    public CatalogBuilder withName(String name) {
        catalog.setName(name);
        return this;
    }

    public Catalog build() {
        return catalog;
    }
}
