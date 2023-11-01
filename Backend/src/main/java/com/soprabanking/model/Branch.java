package com.soprabanking.model;

/**
 * Represents a branch in the system.
 *
 * @param name   The name of the branch.
 * @param merged A flag indicating whether the branch has been merged.
 * @param commit Details about the associated commit.
 */
public record Branch(
        String name,
        boolean merged,
        Commit commit
) {
}
