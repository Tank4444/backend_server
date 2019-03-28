insert into oauth_client_details (
	client_id,client_secret,
	resource_ids,
	scope,
	authorized_grant_types,
	web_server_redirect_uri,authorities,
	access_token_validity,refresh_token_validity,
	additional_information,autoapprove)
	values(
    'user_client_app','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi',
	'user_client_resource,user_admin_resource',
	'role_admin,role_user',
	'authorization_code,password,refresh_token,implicit',
	null,null,
	900,3600,
	'{}',null);

INSERT INTO OAUTH_CLIENT_DETAILS(
CLIENT_ID,
RESOURCE_IDS,
CLIENT_SECRET,
SCOPE,
AUTHORIZED_GRANT_TYPES,
AUTHORITIES,
ACCESS_TOKEN_VALIDITY,
REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-client',
 'resource-server-rest-api',
 /*spring-security-oauth2-read-client-password1234*/
 '$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',
 'read',
 'password,authorization_code,refresh_token,implicit',
 'USER',
 10800,
 2592000);
INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
 VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',
 /*spring-security-oauth2-read-write-client-password1234*/'$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',
 'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);