package org.navistack.framework.objectstorage;

import java.net.URI;
import java.util.function.Supplier;

@FunctionalInterface
public interface PublicRootUriSupplier extends Supplier<URI> {
}
