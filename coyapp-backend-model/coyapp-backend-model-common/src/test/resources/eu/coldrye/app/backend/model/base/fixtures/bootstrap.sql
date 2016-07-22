SET REFERENTIAL_INTEGRITY FALSE;
-- SET foreign_key_checks = 0;

INSERT INTO coy_realm (id, owner_role, entity_state, created_when, name) VALUES ('bf9ef029-bc2e-4256-ae2a-b4b887995fbb', 'SYSTEM', 'ACTIVE', CURRENT_TIMESTAMP, 'System');

INSERT INTO coy_user (id, realm_id, owner_role, entity_state, created_when, user_type, name) VALUES ('e799649b-e9a0-4a49-b3d5-693071f6fc9e', 'bf9ef029-bc2e-4256-ae2a-b4b887995fbb', 'SYSTEM', 'ACTIVE', CURRENT_TIMESTAMP, 'USER', 'System');

UPDATE coy_user SET created_by = 'e799649b-e9a0-4a49-b3d5-693071f6fc9e' WHERE name = 'System';

UPDATE coy_realm SET created_by = 'e799649b-e9a0-4a49-b3d5-693071f6fc9e' WHERE name = 'System';

SET REFERENTIAL_INTEGRITY TRUE;
-- SET foreign_key_checks = 1;
