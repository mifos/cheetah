package org.mifos.core;

public class DuplicatePersistedObjectException extends MifosRepositoryException {

    private static final long serialVersionUID = -291585318874445458L;
    
    private final Object duplicateObject;

    public Object getDuplicateObject() {
        return duplicateObject;
    }

    public DuplicatePersistedObjectException (Object duplicateObject) {
        super();
        this.duplicateObject = duplicateObject;
    }
    
}
