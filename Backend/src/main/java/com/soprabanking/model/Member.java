package com.soprabanking.model;

/**
 * Represents a member in the system.
 *
 * @param access_level     The access level of the member.
 * @param id               The unique identifier of the member.
 * @param username         The username of the member.
 * @param name             The name of the member.
 * @param state            The current state of the member (e.g., active, inactive).
 * @param avatar_url       The URL of the member's avatar.
 * @param membership_state The membership state of the member.
 */
public record Member(
        int access_level,
        int id,
        String username,
        String name,
        String state,
        String avatar_url,
        String membership_state
) {
}
