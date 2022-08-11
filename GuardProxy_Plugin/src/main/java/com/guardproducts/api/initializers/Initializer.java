package com.guardproducts.api.initializers;

import com.google.inject.Injector;

public interface Initializer {

    void init(final Injector injector);

}
