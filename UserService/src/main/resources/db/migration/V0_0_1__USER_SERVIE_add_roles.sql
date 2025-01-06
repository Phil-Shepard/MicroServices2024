DO
$$
    BEGIN
        IF EXISTS(SELECT *
                  FROM information_schema.tables
                  WHERE table_schema = 'public'
                    AND table_name = 'roles')
        THEN
            INSERT INTO roles(name)
            VALUES ('ADMIN'),
                   ('USER');
        END IF;
    END;
$$