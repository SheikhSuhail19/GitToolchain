package com.soprabanking.model;

import java.util.Date;

/**
 * Represents a project in the system.
 *
 * @param id                The unique identifier of the project.
 * @param name              The name of the project.
 * @param description       The description of the project.
 * @param created_at        The date when the project was created.
 * @param forks_count       The number of times the project has been forked.
 * @param star_count        The number of stars (likes) the project has received.
 * @param open_issues_count The count of open issues in the project.
 * @param creator_id        The unique identifier of the project's creator.
 */
public record Project(
        int id,
        String name,
        String description,
        Date created_at,
        int forks_count,
        int star_count,
        int open_issues_count,
        int creator_id
) {
}
