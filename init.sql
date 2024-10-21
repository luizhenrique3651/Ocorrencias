DO $$
BEGIN
	IF NOT EXISTS (
		SELECT FROM pg_catalog.pg_database
		WHERE datname = 'ocorrencias'
	) THEN
		CREATE DATABASE ocorrencias;
	END IF;
END $$;
