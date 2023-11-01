package com.soprabanking.model;

import java.util.Date;

/**
 * Represents a merge request in the system.
 *
 * @param id            The unique identifier of the merge request.
 * @param title         The title of the merge request.
 * @param description   The description of the merge request.
 * @param state         The current state of the merge request (e.g., open, closed).
 * @param created_at    The date when the merge request was created.
 * @param updated_at    The date when the merge request was last updated.
 * @param merged_at     The date when the merge request was merged.
 * @param closed_by     Information about the entity that closed the merge request.
 * @param closed_at     The date when the merge request was closed.
 * @param target_branch The target branch of the merge request.
 * @param source_branch The source branch of the merge request.
 */
public record MergeRequest(
        int id,
        String title,
        String description,
        String state,
        Date created_at,
        Date updated_at,
        Date merged_at,
        Object closed_by,
        Date closed_at,
        String target_branch,
        String source_branch
) {
}
