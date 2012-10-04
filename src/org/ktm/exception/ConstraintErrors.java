package org.ktm.exception;

import java.util.List;

public class ConstraintErrors extends KTMError {

    private static final long serialVersionUID = 1L;

    public ConstraintErrors() {
        super("ConstraintErrors");
    }

    public ConstraintErrors(String message) {
        super(message);
    }

    @SuppressWarnings("rawtypes")
    public void addLocalizedErrors(List list) {
        // TODO Auto-generated method stub

    }

}
