package com.soprabanking.model;

import java.util.Date;

/**
 * Represents an issue in the system.
 *
 * @param id                    The unique identifier of the issue.
 * @param title                 The title of the issue.
 * @param description           The description of the issue.
 * @param state                 The current state of the issue (e.g., open, closed).
 * @param created_at            The date when the issue was created.
 * @param updated_at            The date when the issue was last updated.
 * @param closed_by             Information about the entity that closed the issue.
 * @param closed_at             The date when the issue was closed.
 * @param type                  The type of the issue.
 * @param merge_requests_count  The count of related merge requests.
 * @param due_date              The due date for the issue.
 * @param issue_type            The issue type.
 * @param weight                The weight of the issue.
 * @param blocking_issues_count The count of blocking issues.
 * @param severity              The severity level of the issue.
 */
public record Issue(
        int id,
        String title,
        String description,
        String state,
        Date created_at,
        Date updated_at,
        Object closed_by,
        Date closed_at,
        String type,
        int merge_requests_count,
        Date due_date,
        String issue_type,
        int weight,
        int blocking_issues_count,
        String severity
) {
}
