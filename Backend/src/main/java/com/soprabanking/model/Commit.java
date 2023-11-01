package com.soprabanking.model;

import java.util.Date;

/**
 * Represents a commit in the system.
 *
 * @param id              The unique identifier of the commit.
 * @param created_at      The date when the commit was created.
 * @param title           The title or subject of the commit.
 * @param message         The detailed message associated with the commit.
 * @param author_name     The name of the commit author.
 * @param author_email    The email address of the commit author.
 * @param authored_date   The date when the commit was authored.
 * @param committer_name  The name of the committer.
 * @param committer_email The email address of the committer.
 * @param committed_date  The date when the commit was committed.
 */
public record Commit(
        String id,
        Date created_at,
        String title,
        String message,
        String author_name,
        String author_email,
        Date authored_date,
        String committer_name,
        String committer_email,
        Date committed_date
) {
}
