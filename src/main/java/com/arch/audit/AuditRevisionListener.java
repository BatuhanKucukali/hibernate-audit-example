package com.arch.audit;

import org.hibernate.envers.RevisionListener;

public class AuditRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity audit = (AuditRevisionEntity) revisionEntity;
        audit.setUser("API-USER"); // You can find authenticated user -> SecurityContextHolder.getContext()
    }
}
